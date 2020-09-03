package repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.repository.MemberRepository;
import io.playdata.themorethebetter.repository.MemoryMemberRepository;

// 필요한 테스트 로직만 주석을 해제해서 JUnit Test로 빌드할 것
class MemoryMemberRepositoryTest 
{
	MemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach // Test를 끝날때마다 깔끔하게 repository를 지워주는 부분
	public void afterEach()
	{
		repository.clearStore();
	}
	
	@Test
	public void save()
	{
		Member member = new Member();
		
		member.setMem_id("test");
		repository.save(member);

		// Test Logic
		Member result = repository.findById(member.getMem_id()).get();
		
		Assertions.assertEquals(member, result);
	}
	
	@Test
	public void findByName()
	{
		Member member1 = new Member();
		member1.setMem_name("test1");
		
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setMem_name("test2");
		
		repository.save(member2);
		
		// Test Logic
		Member result1 = repository.findByName("test1").get();
		Member result2 = repository.findByName("test2").get();
		
		Assertions.assertEquals(member1, result1);
		Assertions.assertEquals(member2, result2);
	}
	
	@Test
	public void findById()
	{
		Member member3 = new Member();
		member3.setMem_id("test3");
		
		repository.save(member3);
		
		// Test Logic
		Member result3 = repository.findById("test3").get();
		
		Assertions.assertEquals(member3, result3);
	}
	
	@Test
	public void findAll()
	{
		Member member4 = new Member();
		member4.setMem_name("test4");
		
		repository.save(member4);
		
		Member member5 = new Member();
		member4.setMem_name("test5");
		
		repository.save(member5);
		
		List<Member> result4 = repository.findAll();
		
		org.assertj.core.api.Assertions.assertThat(result4.size()).isEqualTo(2);
	}
}