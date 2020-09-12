package io.playdata.themorethebetter.controller;

import java.io.IOException;
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
import io.playdata.themorethebetter.domain.Store;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.dto.order.OrderCreateRequestDto;
import io.playdata.themorethebetter.dto.order.OrderSearchResponseDto;
import io.playdata.themorethebetter.dto.order.OrderWaitingSetNewMemDto;
import io.playdata.themorethebetter.service.MemberService;
import io.playdata.themorethebetter.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {
	
	private OrderService orderService;
	private MemberService memberService;
	
	// 모든 주문 내역 찾기 
	@GetMapping("/order/AllStanBy")
    public List<OrderSearchResponseDto> SearchAll() {
		log.info("------searchAll 접속완료--------" + "받은 데이터 :");
        return orderService.findAllStandBy();
    }
	
	// 특정이름이 포함된 주문 내역 찾기
	@GetMapping("/order/search/{keyword}")
    public List<String> SearchAllName(@PathVariable String keyword) {
		log.info("------searchAllName 접속완료--------" + "받은 데이터 :" + keyword);
        return orderService.searchByStoreName(keyword);
    }
	
	/* 멤버 고유 번호로 주문 찾기 */
	@GetMapping("/order/info/{mem_no}")
	public ResponseEntity<Map<String, Object>> getMemberInfo(@PathVariable Long mem_no, HttpServletResponse res) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Waiting order = orderService.findOrderByMem(mem_no);
			Store store = orderService.findStoreByOrder(order);
			resultMap.put("order", order);
			resultMap.put("store", store);
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
	public ResponseEntity<Map<String, Object>> makeOrder(@RequestBody OrderCreateRequestDto dto, HttpServletRequest req) { 
		log.info("주문 생성 시작");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			System.out.println(dto.toString());
			Long mem_no = Long.parseLong(req.getHeader("mem_no"));
			orderService.makeOrder(dto, mem_no);
			status = HttpStatus.ACCEPTED;
			log.info("주문 생성 완료 - 200");
		
		}catch (RuntimeException e) {
			status = HttpStatus.METHOD_NOT_ALLOWED; 
			resultMap.put("message", e.getMessage());
			log.error("주문 생성 실패 - 405");
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	// 주문 대기자 등록
	@PostMapping("/order/setmem")
	public ResponseEntity<Map<String, Object>> waitingSetMem(@RequestBody OrderWaitingSetNewMemDto WaitingNum, HttpServletRequest req) { 
		log.info("주문 생성 시작");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Long mem_no = Long.parseLong(req.getHeader("mem_no"));
			Waiting order = orderService.findOrderByNo(WaitingNum.getWaitingNum());
			Member mem = memberService.getInfo(mem_no);
			System.out.println(order.toString());
			System.out.println(mem.toString());
			order.addWaitMem(mem);
			status = HttpStatus.ACCEPTED;
			log.info(mem_no+"주문 등록 완료 - 200");
			System.out.println(order.getWaitingmems());
		}catch (RuntimeException e) {
			status = HttpStatus.METHOD_NOT_ALLOWED; 
			resultMap.put("message", e.getMessage());
			log.error("주문 등록 실패 - 405");
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	
	

	// 주문 삭제 
	@DeleteMapping("/order/{orderNo}")
	public void deleteOrder(@PathVariable Long orderNo) {
		
	}
}
