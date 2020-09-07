package io.playdata.themorethebetter.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.themorethebetter.service.OrderService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderController {
	
	private OrderService orderService;
	// 모든 주문 내역 찾기 
	@GetMapping("/")
    public String main(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "main";
    }
	// 주문 생성 
	@PostMapping("/order/{mem_no}/{st_no}")
	public Long makeOrder(@PathVariable("mem_no") Long mem_no, @PathVariable("st_no") Long st_no) {
		return orderService.makeOrder(mem_no, st_no);
	}
	// 주문 삭제 
	@DeleteMapping("/order/{orderNo}")
	public void deleteOrder(@PathVariable Long orderNo) {
		
	}
}
