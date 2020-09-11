package io.playdata.themorethebetter.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Store;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.dto.member.MemberCreateRequestDto;
import io.playdata.themorethebetter.dto.member.MemberLogInRequestDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
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
public class MemberController {
	
	private MemberService memberService;
	
	//회원가입
	@PostMapping("/members/new")
	@ApiOperation(value = "신규 회원 가입", notes = "성공시 입력받은 개인정보가 member DB에 저장됩니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dto", value = "회원가입을 위해 수업코드/아이디/이름/핸드폰번호/비밀번호 데이터를 전달하는 DTO 객체", required = true, dataType = "object", defaultValue = "ClassCode, id, name, phone, pw") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 요청이 완료되었습니다!"),
			@ApiResponse(code = 405, message = "회원가입에 실패했습니다.")})
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
	@ApiOperation(value = "로그인", notes = "성공시 해당 개인정보와 일치하는 회원 정보를 불러옵니다.")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "정상적으로 로그인 완료되었습니다."),
			@ApiResponse(code = 405, message = "로그인을 실패하였습니다.")})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dto", value = "로그인을 위해 아이디/비밀번호 데이터를 전달하는 DTO 객체", required = true, dataType = "object", defaultValue = "id, pw") })
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
	
	//멤버 정보 가져오기 
	@GetMapping("members/info/{no}")
	@ApiOperation(value = "멤버정보 불러오기", notes = "성공시 해당 개인정보와 일치하는 회원 정보를 불러옵니다.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 멤버 정보가 불러와졌습니다."),
			@ApiResponse(code = 405, message = "멤버정보를 불러오는데 실패하였습니다.")})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "no", value = "찾고자하는 멤버의 고유 번호", required = true, dataType = "long", defaultValue = "1") })
	public ResponseEntity<Map<String, Object>> getMemberInfo(@PathVariable Long no, HttpServletResponse res) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			Member member = memberService.getInfo(no);
			resultMap.put("member", member);
			resultMap.put("status", true);
			log.info("회원 검색 성공");
			status = HttpStatus.OK; //200
			
		}catch(RuntimeException e) {
			log.info("회원 검색 실패");
			status = HttpStatus.METHOD_NOT_ALLOWED; //405
			res.sendError(405, e.getMessage());
		}
		log.info("resultMap" + resultMap);
		log.info("status" + status);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
