package io.playdata.themorethebetter.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.dto.order.OrderCreateRequestDto;
import io.playdata.themorethebetter.dto.order.OrderWaitingSetNewMemDto;
import io.playdata.themorethebetter.service.MemberService;
import io.playdata.themorethebetter.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {
	
	private OrderService orderService;
	private MemberService memberService;

	
	/* 대기인원 많은 순으로 모든 주문 내역 찾기 */ 
	@GetMapping("/order/all")
    public ResponseEntity<Map<String, Object>> SearchAll(HttpServletResponse res) throws IOException {
		log.info("------searchAll 접속완료--------" + "받은 데이터 :");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			List<Waiting> orders = orderService.findAllStandBy();
			resultMap.put("orders", orders);
			resultMap.put("status", true);
			log.info("대기인원 많은 순으로 모든 주문 검색 성공");
			status = HttpStatus.OK; //200
			
		}catch(Exception e) {
			log.info("모든 주문 검색 실패");
			status = HttpStatus.METHOD_NOT_ALLOWED; //405
			res.sendError(405, e.getMessage());
		}
		log.info("resultMap" + resultMap);
		log.info("status" + status); 
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
	
	  @PostMapping("/order/setmem")
	   public ResponseEntity<Map<String, Object>> waitingSetMem(@RequestBody OrderWaitingSetNewMemDto WaitingNum, HttpServletRequest req) { 
	      log.info("주문 생성 시작");
	      Map<String, Object> resultMap = new HashMap<>();
	      HttpStatus status = null;
	      try {
	         Long mem_no = Long.parseLong(req.getHeader("mem_no"));
	         Waiting order = orderService.findOrderByNo(WaitingNum.getWaitingNum());
	         Member mem = memberService.getInfo(mem_no);
	         System.out.println(order.getNo()+mem.getNo());
	         order.addWaitMem(mem);
	         orderService.updateWaiting(mem);
	         status = HttpStatus.ACCEPTED;
	         log.info(mem_no+"주문 등록 완료 - 200");
	      }catch (RuntimeException e) {
	         status = HttpStatus.METHOD_NOT_ALLOWED; 
	         resultMap.put("message", e.getMessage());
	         log.error("주문 등록 실패 - 405");
	      }
	      return new ResponseEntity<Map<String,Object>>(resultMap, status);
	   }
	
	
	/* 특정이름이 포함된 주문 내역 찾기 */
	@GetMapping("/order/search/{keyword}")
    public List<String> SearchAllName(@PathVariable String keyword) {
		log.info("------searchAllName 접속완료--------" + "받은 데이터 :" + keyword);
        return orderService.searchByStoreName(keyword);
    }	
	
	/* 멤버 고유 번호로 주문 찾기 */
	@GetMapping("/order/info/{mem_no}")
	@ApiOperation(value = "멤버번호로 주문 검색", notes = "성공시 멤버고유번호에 대응하는 주문 게시글이 반환됩니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mem_no", value = "주문 검색을 원하는 멤버 고유번호", required = true, dataType = "long", defaultValue = "1") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 요청이 완료되었습니다!"),
			@ApiResponse(code = 405, message = "검색 요청을 처리할 수 없습니다.")})
	public ResponseEntity<Map<String, Object>> getMemberInfo(@PathVariable Long mem_no, HttpServletResponse res) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Waiting order = orderService.findOrderByMem(mem_no);
			resultMap.put("order", order);
			resultMap.put("status", true);
			log.info("주문 검색 성공");
			status = HttpStatus.OK; //200
			
		}catch(RuntimeException e) {
			log.info("주문 검색 실패");
			status = HttpStatus.NO_CONTENT; //204
			res.sendError(405, e.getMessage());
		}
		log.info("resultMap" + resultMap);
		log.info("status" + status);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 주문 생성 
	@PostMapping("/order")
	@ApiOperation(value = "신규 주문 생성", notes = "성공시 작성된 주문 게시글이 DB에 저장됩니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dto", value = "신규 주문 게시글 작성을 위해 장소/최소주문금액/인원수/매장주소/매장이미지/매장이름/게시글내용/시간 데이터를 전달하는 DTO 객체", required = true, dataType = "object", defaultValue = "deliPlace, minCost, people, storeAddress, storeImg, storeName, text, time") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 요청이 완료되었습니다!"),
			@ApiResponse(code = 405, message = "주문생성을 실패했습니다.") })
	public ResponseEntity<Map<String, Object>> makeOrder(@RequestBody OrderCreateRequestDto dto, HttpServletRequest req) throws ParseException { 
		log.info("주문 생성 시작");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			System.out.println(dto.toString());
			//주문 생성할때 멤버 정보 받아와야 하지 않을까...?
			Long mem_no = Long.parseLong(req.getHeader("mem_no"));
			orderService.makeOrder(dto, mem_no);
			status = HttpStatus.ACCEPTED;
			log.info("주문 생성 완료 - 202");
		
		}catch (RuntimeException e) {
			status = HttpStatus.METHOD_NOT_ALLOWED; 
			resultMap.put("message", e.getMessage());
			log.error("주문 생성 실패 - 405", e.getMessage());
		}
		log.info("resultMap : " + resultMap);
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	


	// 주문 삭제 
	@DeleteMapping("/order/{order_no}/{mem_no}")
	@ApiOperation(value = "주문 삭제", notes = "성공시 주문고유번호에 대응하는 주문 게시글이 삭제되거나 주문대기목록에 멤버고유번호에 대응하는 멤버를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "order_no", value = "삭제하고자 하는 주문 게시글의 고유번호", required = true, dataType = "long", defaultValue = "1"),
		@ApiImplicitParam(name = "mem_no", value = "삭제를 원하는 멤버 고유번호", required = true, dataType = "long", defaultValue = "1") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 요청이 완료되었습니다!"),
			@ApiResponse(code = 405, message = "삭제 요청을 처리할 수 없습니다.")})
	public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable Long order_no, @PathVariable Long mem_no, HttpServletResponse res) {
		log.info("주문 삭제 시작");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			orderService.deleteOrder(mem_no, order_no);;
			resultMap.put("status", true);
			status = HttpStatus.OK;
			log.info("주문 삭제 완료 - 200");
		
		}catch (RuntimeException e) {
			status = HttpStatus.METHOD_NOT_ALLOWED; 
			resultMap.put("message", e.getMessage());
			log.error("주문 삭제 실패 - 405", e.getMessage());
		}
		log.info("resultMap : " + resultMap);
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
}