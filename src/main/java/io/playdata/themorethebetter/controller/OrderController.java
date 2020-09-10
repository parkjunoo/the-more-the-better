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

import io.playdata.themorethebetter.dto.order.OrderCreateRequestDto;
import io.playdata.themorethebetter.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class OrderController {
	
	private OrderService orderService;
	
	// 모든 주문 내역 찾기 
	@GetMapping("/")
    public String main(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "main";
    }
	
	// 주문 생성 
	@PostMapping("/order")
	public ResponseEntity<Map<String, Object>> makeOrder(@RequestBody OrderCreateRequestDto dto, HttpServletRequest req) { 
		log.info("주문 생성 시작");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			System.out.println(dto.toString());
			//주문 생성할때 멤버 정보 받아와야 하지 않을까...?
			Long mem_no = Long.parseLong(req.getHeader("mem_no"));
			orderService.makeOrder(dto, mem_no);
			status = HttpStatus.ACCEPTED;
			log.info("주문 생성 완료 - 200");
		
		}catch (RuntimeException e) {
			status = HttpStatus.METHOD_NOT_ALLOWED; 
			resultMap.put("message", e.getMessage());
			log.error("주문 생성 실패 - 405", e.getMessage());
		}
		log.info("resultMap : " + resultMap);
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}

	// 주문 삭제 
	@DeleteMapping("/order/{orderNo}")
	public void deleteOrder(@PathVariable Long orderNo) {
		
	}
}