package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Member;
import exception.MemberNotFoundException;
import repository.MemberRepository;
import service.MemberService;

@Controller
public class MemberController 
{
	private final MemberService memberService;
	
	@Autowired // 스프링 컨테이너에서 MemberService 객체를 가져옴
	public MemberController(MemberService memberService) 
	{
		this.memberService = memberService;
	}
}