//package io.playdata.themorethebetter.repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.stereotype.Repository;
//
//import io.playdata.themorethebetter.domain.Member;
//
//// 구현체 부분
//public class MemoryMemberRepository implements MemberRepository
//{
//	private static Map<Long, Member> store = new HashMap<Long, Member>(); // 반환 결과를 담을 Map 객체 = <mem_id, Member>
//	private static long sequence = 0L; // 키 값을 생성해주는 역할을 수행함
//	
//	@Override
//	public Member save(Member member) 
//	{
//		member.setNo(++sequence); // 아이디 값을 세팅해 줌
//		store.put(member.getNo(), member);
//		
//		return member;
//	}
//
//	@Override
//	public Optional<Member> findByNo(Long mem_no) // store에서 꺼내게 됨
//	{
//		return Optional.ofNullable(store.get(mem_no)); // null값을 처리하기 위해 Optional로 감쌈
//	}
//
//	@Override
//	public Optional<Member> findByName(String mem_name) 
//	{
//		return store.values().stream().filter(member -> member.getName().equals(mem_name)).findAny(); // findAny() : 하나라도 찾는 것
//	}
//
//	@Override
//	public Optional<Member> findById(String mem_id) 
//	{
//		return store.values().stream().filter(member -> member.getId().equals(mem_id)).findAny();
//	}
//	
//	@Override
//	public List<Member> findAll() 
//	{
//		return new ArrayList<Member>(store.values()); // store.values() = member
//	}
//	
//	public void clearStore()
//	{
//		store.clear(); // store를 비워줌
//	}
//}