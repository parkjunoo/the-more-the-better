package io.playdata.themorethebetter.controller;

import java.io.IOException;
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
import io.playdata.themorethebetter.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {
	
	private MemberService memberService;
	
	//회원가입
	@PostMapping("/members/new")
	public ResponseEntity<Map<String, Object>> createMember(@RequestBody MemberCreateRequestDto dto, HttpServletResponse res) throws IOException {
		Member member = null;
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			member = memberService.join(dto);
			resultMap.put("member", member);
			resultMap.put("status", true);
			log.info("회원가입 성공");
			status = HttpStatus.OK; //200
			
		}catch(RuntimeException e) {
			log.error("회원가입 실패");
			status = HttpStatus.METHOD_NOT_ALLOWED; //405
			//vue로 에러 메세지 보내기 (e.response.data.message)
			res.sendError(405, e.getMessage());
		}
		log.info("resultMap" + resultMap);
		log.info("status" + status);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//로그인
	@PostMapping("/members/login")
	public ResponseEntity<Map<String, Object>> loginMember(@RequestBody MemberLogInRequestDto dto, HttpServletResponse res) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Member member = memberService.logIn(dto);
			
			resultMap.put("member", member);
			resultMap.put("status", true);
			log.info("로그인 성공");
			status = HttpStatus.ACCEPTED; //202
			
		}catch(RuntimeException e) {
			log.error("로그인 실패");
			status = HttpStatus.METHOD_NOT_ALLOWED; //405
			res.sendError(405, e.getMessage());
		}
		log.info("resultMap" + resultMap);
		log.info("status" + status);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
