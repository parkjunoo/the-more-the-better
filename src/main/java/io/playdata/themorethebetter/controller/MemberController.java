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
import io.playdata.themorethebetter.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {

	private MemberService memberService;

	// 회원가입
	@PostMapping("/members/new")
	@ApiOperation(value = "신규 회원 가입", notes = "성공시 입력받은 개인정보가 member DB에 저장됩니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "dto", value = "회원가입을 위해 수업코드/아이디/이름/핸드폰번호/비밀번호 데이터를 전달하는 DTO 객체", required = true, dataType = "object", paramType = "query", defaultValue = "ClassCode, id, name, phone, pw") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 요청이 완료되었습니다!"),
			@ApiResponse(code = 201, message = "정상적으로 신규 계정 생성이 완료되었습니다!"),
			@ApiResponse(code = 401, message = "해당 리소스에 유효한 인증 자격 증명이 없기 때문에 요청이 적용되지 않았습니다!"),
			@ApiResponse(code = 403, message = "웹 페이지를 볼 수 있는 권한이 없습니다!"),
			@ApiResponse(code = 404, message = "클라이언트가 서버와 통신할 수 있지만 서버가 요청한 내용을 응답 받을 수 없습니다!")})
	public ResponseEntity<Map<String, Object>> createMember(@RequestBody MemberCreateRequestDto dto,
			HttpServletResponse res) {
		Member member = null;
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			member = memberService.join(dto);
			resultMap.put("member", member);
			resultMap.put("status", true);
			log.info("회원가입 성공");
			status = HttpStatus.OK; // 200

		} catch (RuntimeException e) {
			log.error("회원가입 실패");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.METHOD_NOT_ALLOWED; // 405
		}
		log.info("resultMap" + resultMap);
		log.info("status" + status);

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 로그인 - 미완성
	@GetMapping("/member/login")
	@ApiOperation(value = "로그인", notes = "성공시 해당 개인정보와 일치하는 회원 정보를 불러옵니다.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "정상적으로 로그인 완료"),
			@ApiResponse(code = 401, message = "해당 리소스에 유효한 인증 자격 증명이 없기 때문에 요청이 적용되지 않았습니다!"),
			@ApiResponse(code = 403, message = "웹 페이지를 볼 수 있는 권한이 없습니다!"),
			@ApiResponse(code = 404, message = "클라이언트가 서버와 통신할 수 있지만 서버가 요청한 내용을 응답 받을 수 없습니다!") })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dto", value = "로그인을 위해 아이디/비밀번호 데이터를 전달하는 DTO 객체", required = true, dataType = "object", paramType = "query", defaultValue = "id, pw") })
	public ResponseEntity<Map<String, Object>> loginMember(@RequestBody MemberLogInRequestDto dto,
			HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		try {
			Member member = memberService.logIn(dto);

			resultMap.put("status", true);
			resultMap.put("member", member);
			log.info("로그인 성공");
			status = HttpStatus.ACCEPTED;

		} catch (RuntimeException e) {
			log.error("로그인 실패");
			resultMap.put("error_message", e.getMessage());
			status = HttpStatus.METHOD_NOT_ALLOWED; // 405
		}
		log.info("resultMap" + resultMap);
		log.info("status" + status);

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}