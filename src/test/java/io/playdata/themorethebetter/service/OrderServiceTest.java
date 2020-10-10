
package io.playdata.themorethebetter.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.List;

import org.junit.Test; 
import org.junit.runner.RunWith; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.test.context.junit4.SpringRunner;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.dto.order.OrderCreateRequestDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.playdata.themorethebetter.repository.MemberRepository; 
import io.playdata.themorethebetter.repository.WaitingRepository;

@RunWith(SpringRunner.class)

@SpringBootTest public class OrderServiceTest {

	@Autowired private OrderService orderService;
	@Autowired private WaitingRepository waitingRepository;
	@Autowired private MemberRepository memberRepository;
	
	// @After
	// public void cleanup () { 
	// waitingRepository.deleteAll(); 
	// waitingMemsRepository.deleteAll(); 
	// hostRepository.deleteAll(); 
	// memberRepository.deleteAll(); 
	// classRepository.deleteAll(); 
	// storeRepository.deleteAll(); 
	// }

	@Test public void 주문생성 () throws NotFoundException, ForbiddenException, ParseException { 
		//given 
		Member member = memberRepository.findByName("aa").get(); 
		OrderCreateRequestDto dto = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		//when 
		orderService.makeOrder(dto, member.getNo());

		//then 
		Waiting order = waitingRepository.findAll().get(0);
		assertThat(order.getHost().getNo()).isEqualTo(member.getNo());
		assertThat(order.getStore().getName()).isEqualTo(dto.getStoreName());
		assertThat(order.getStandby()).isEqualTo(1);
		assertThat(order.getMinperson()).isEqualTo(3);

	}

	@Test(expected=ForbiddenException.class) 
	public void 호스트주문생성에러 () throws NotFoundException, ForbiddenException, ParseException { 
		//given
		Member member = memberRepository.findByName("aa").get(); 
		OrderCreateRequestDto dto1 = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();
		OrderCreateRequestDto dto2 = OrderCreateRequestDto.builder()
				.storeName("starbucks")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto1, member.getNo());

		//when 
		orderService.makeOrder(dto2, member.getNo());

	}

	@Test(expected=ForbiddenException.class) 
	public void 대기자주문생성에러 () throws NotFoundException, ForbiddenException, ParseException { 
		//given
		Member member1 = memberRepository.findByName("aa").get(); 
		Member member2 = memberRepository.findByName("bb").get(); 
		OrderCreateRequestDto dto1 = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();
		OrderCreateRequestDto dto2 = OrderCreateRequestDto.builder()
				.storeName("starbucks")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto1, member1.getNo()); 
		Waiting order = waitingRepository.findAll().get(0);

		orderService.updateWaiting(member2.getNo(), order.getNo());

		//when 
		orderService.makeOrder(dto2, member2.getNo());

	}

	@Test 
	public void 대기인원추가 () throws NotFoundException, ForbiddenException, ParseException { 
		//given 
		Member member1 = memberRepository.findByName("aa").get(); 
		Member member2 = memberRepository.findByName("bb").get(); 
		OrderCreateRequestDto dto = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto, member1.getNo());

		Waiting order = waitingRepository.findAll().get(0);

		//when 
		orderService.updateWaiting(member2.getNo(), order.getNo());

		//then 
		Waiting addedOrder = waitingRepository.findAll().get(0);
		assertThat(addedOrder.getStandby()).isEqualTo(2);
		
	}

	@Test(expected=ForbiddenException.class) 
	public void 호스트주문참여에러 () throws NotFoundException, ForbiddenException, ParseException { 
		//given
		Member member1 = memberRepository.findByName("aa").get(); 
		Member member2 = memberRepository.findByName("bb").get(); 
		OrderCreateRequestDto dto1 = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();
		OrderCreateRequestDto dto2 = OrderCreateRequestDto.builder()
				.storeName("starbucks")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto1, member1.getNo());
		orderService.makeOrder(dto2, member2.getNo());

		Waiting order1 = waitingRepository.findAll().get(0); 

		//when 
		orderService.updateWaiting(member2.getNo(), order1.getNo());

	}

	@Test(expected=ForbiddenException.class) 
	public void 주문중복참여에러 () throws NotFoundException, ForbiddenException, ParseException { 
		//given
		Member member1 = memberRepository.findByName("aa").get(); 
		Member member2 = memberRepository.findByName("bb").get(); 
		Member member3 = memberRepository.findByName("cc").get(); 
		OrderCreateRequestDto dto1 = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();
		OrderCreateRequestDto dto2 = OrderCreateRequestDto.builder()
				.storeName("starbucks")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto1, member1.getNo());
		orderService.makeOrder(dto2, member2.getNo());

		Waiting order1 = waitingRepository.findAll().get(0); 
		Waiting order2 =waitingRepository.findAll().get(1);

		orderService.updateWaiting(member3.getNo(), order1.getNo());

		//when 
		orderService.updateWaiting(member3.getNo(), order2.getNo());

	}

	@Test 
	public void 대기인원삭제 () throws NotFoundException, ForbiddenException, ParseException { 
		//given 
		Member member1 = memberRepository.findByName("aa").get(); 
		Member member2 = memberRepository.findByName("bb").get(); 
		OrderCreateRequestDto dto = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto, member1.getNo()); 
		Waiting order = waitingRepository.findAll().get(0);
		orderService.updateWaiting(member2.getNo(), order.getNo());

		//when 
		orderService.deleteMemberToOrder(member2, order);

		//then 
		Waiting deletedOrder = waitingRepository.findAll().get(0);
		assertThat(deletedOrder.getStandby()).isEqualTo(1);
		
	}

	@Test 
	public void 주문삭제 () throws NotFoundException, ForbiddenException, ParseException { 
		//given 
		Member member1 = memberRepository.findByName("aa").get(); 
		Member member2 = memberRepository.findByName("bb").get();
		OrderCreateRequestDto dto1 = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();
		OrderCreateRequestDto dto2 = OrderCreateRequestDto.builder()
				.storeName("starbucks")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto1, member1.getNo());
		orderService.makeOrder(dto2, member2.getNo());
		
		Waiting order1 = waitingRepository.findAll().get(0); 
		Waiting order2 =waitingRepository.findAll().get(1);
		 
		Member host1 = order1.getHost();
		Member host2 = order2.getHost();
		
		//when 
		orderService.deleteOrder(host1.getNo(), order1.getNo());

		//then 
		List<Waiting> orderlist = waitingRepository.findAll();
		assertThat(orderlist.size()).isEqualTo(1);
		assertThat(orderlist.get(0).getHost().getNo()).isEqualTo(host2.getNo()); }

	@Test(expected=ForbiddenException.class) 
	public void 일반멤버주문삭제오류 () throws NotFoundException, ForbiddenException, ParseException { 
		//given
		Member member1 = memberRepository.findByName("aa").get(); 
		Member member2 =memberRepository.findByName("bb").get(); 
		OrderCreateRequestDto dto1 = OrderCreateRequestDto.builder()
				.storeName("dada")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();
		OrderCreateRequestDto dto2 = OrderCreateRequestDto.builder()
				.storeName("starbucks")
				.people(3)
				.time("18:30")
				.deliPlace("학원 정문")
				.minCost(10000)
				.storeAddress("서울시 서초구 ~")
				.build();

		orderService.makeOrder(dto1, member1.getNo());
		orderService.makeOrder(dto2, member2.getNo());

		Waiting order1 = waitingRepository.findAll().get(0); 
		Waiting order2 =waitingRepository.findAll().get(1);
		 
		Member host2 = order2.getHost();
		
		//when 
		orderService.deleteOrder(host2.getNo(), order1.getNo());

	} }

