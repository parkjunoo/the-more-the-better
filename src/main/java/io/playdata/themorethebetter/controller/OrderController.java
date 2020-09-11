package io.playdata.themorethebetter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.dto.order.OrderCreateRequestDto;
import io.playdata.themorethebetter.service.OrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
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

	// 모든 주문 내역 찾기
	@GetMapping("/")
	@ApiOperation(value = "모든 주문 내역 찾기", notes = "성공시 모든 주문 내역을 반환합니다.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 모든 주문 내역이 반환되었습니다!"),
			@ApiResponse(code = 401, message = "해당 리소스에 유효한 인증 자격 증명이 없기 때문에 요청이 적용되지 않았습니다!"),
			@ApiResponse(code = 403, message = "웹 페이지를 볼 수 있는 권한이 없습니다!"),
			@ApiResponse(code = 404, message = "클라이언트가 서버와 통신할 수 있지만 서버가 요청한 내용을 응답 받을 수 없습니다!") })
	public String main(Model model) {
		model.addAttribute("orders", orderService.findAll());
		return "main";
	}

	// 주문 생성
	@PostMapping("/order")
	@ApiOperation(value = "신규 주문 생성", notes = "성공시 작성된 주문 게시글이 DB에 저장됩니다.")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dto", value = "신규 주문 게시글 작성을 위해 장소/최소주문금액/인원수/매장주소/매장이미지/매장이름/게시글내용/시간 데이터를 전달하는 DTO 객체", required = true, dataType = "object", paramType = "query", defaultValue = "deliPlace, minCost, people, storeAddress, storeImg, storeName, text, time") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 요청이 완료되었습니다!"),
			@ApiResponse(code = 201, message = "정상적으로 신규 주문 게시글 생성이 완료되었습니다!"),
			@ApiResponse(code = 401, message = "해당 리소스에 유효한 인증 자격 증명이 없기 때문에 요청이 적용되지 않았습니다!"),
			@ApiResponse(code = 403, message = "웹 페이지를 볼 수 있는 권한이 없습니다!"),
			@ApiResponse(code = 404, message = "클라이언트가 서버와 통신할 수 있지만 서버가 요청한 내용을 응답 받을 수 없습니다!") })
	public ResponseEntity<Map<String, Object>> makeOrder(@RequestBody OrderCreateRequestDto dto,
			HttpServletRequest req) {
		log.info("주문 생성 시작");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			System.out.println(dto.toString());
			orderService.makeOrder(dto);
			status = HttpStatus.ACCEPTED;
			log.info("주문 생성 완료 - 200");

		} catch (RuntimeException e) {
			status = HttpStatus.METHOD_NOT_ALLOWED;
			resultMap.put("message", e.getMessage());
			log.error("주문 생성 실패 - 405");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 주문 삭제
	@DeleteMapping("/order/{orderNo}")
	@ApiOperation(value = "주문 삭제", notes = "성공시 주문고유번호에 대응하는 주문 게시글이 삭제됩니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderNo", value = "삭제하고자 하는 주문 게시글의 고유번호", required = true, dataType = "int", paramType = "query", defaultValue = "1") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 요청이 완료되었습니다!"),
			@ApiResponse(code = 204, message = "게시글 삭제가 완료되었기 때문에 컨텐츠를 제공하지 않습니다."),
			@ApiResponse(code = 401, message = "해당 리소스에 유효한 인증 자격 증명이 없기 때문에 요청이 적용되지 않았습니다!"),
			@ApiResponse(code = 403, message = "해당 요청을 서버가 거부하고 있습니다.")})
	public void deleteOrder(@PathVariable Long orderNo) {

	}
}