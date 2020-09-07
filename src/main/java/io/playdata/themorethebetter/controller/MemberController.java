package io.playdata.themorethebetter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.dto.member.MemberCreateRequestDto;
import io.playdata.themorethebetter.dto.member.MemberLogInRequestDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.playdata.themorethebetter.service.JwtService;
import io.playdata.themorethebetter.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {
	
	private MemberService memberService;
	private JwtService jwtService;
	
	//회원가입 
	@PostMapping("/member")
	public Member createMember(@RequestBody MemberCreateRequestDto dto) {
		Member member = null;
		HttpStatus status = null;
		
		try {
			member = memberService.join(dto);
			status = HttpStatus.OK; //200
			log.info("회원가입 성공");
			
		}catch(RuntimeException e) {
			log.error("회원가입 실패");
			status = HttpStatus.METHOD_NOT_ALLOWED; //405
		}
		return member;
	}
	
	//로그인 
	@GetMapping("/member/login")
	public ResponseEntity<Map<String, Object>> loginMember(@RequestBody MemberLogInRequestDto dto, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Member member = memberService.logIn(dto);
			String token = jwtService.create(member);
			res.setHeader("log-in-token", token);
			
			resultMap.put("log_in_token", token);
			resultMap.put("status", true);
			resultMap.put("user", member);
			
			status = HttpStatus.ACCEPTED;
			
		}catch(NotFoundException e) {
			log.error("로그인 실패", e);
			resultMap.put("error_message", e.getMessage());
		}
		
		log.info("로그인 정보" + resultMap);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
