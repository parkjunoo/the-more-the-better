package io.playdata.themorethebetter.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.playdata.themorethebetter.domain.Host;
import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Store;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.repository.ClassRepository;
import io.playdata.themorethebetter.repository.HostRepository;
import io.playdata.themorethebetter.repository.MemberRepository;
import io.playdata.themorethebetter.repository.StoreRepository;
import io.playdata.themorethebetter.repository.WaitingMemsRepository;
import io.playdata.themorethebetter.repository.WaitingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	@Autowired private OrderService orderService;
	@Autowired private WaitingRepository waitingRepository;
	@Autowired private ClassRepository classRepository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private StoreRepository storeRepository;
	@Autowired private WaitingMemsRepository waitingMemsRepository;
	@Autowired private HostRepository hostRepository;	
	
//	@After
//	public void cleanup () {
//		waitingRepository.deleteAll();
//		waitingMemsRepository.deleteAll();
//        hostRepository.deleteAll();
//        memberRepository.deleteAll();
//        classRepository.deleteAll();
//        storeRepository.deleteAll();
//    }
	
	@Test
	public void 주문생성 () {
		//given 
		Member member = memberRepository.findByName("aa").get();
		Store store = storeRepository.findByName("bana").get();
		
		//when
		orderService.makeOrder(member.getNo(), store.getNo());
		
		//then
		Waiting order = waitingRepository.findAll().get(0);
		assertThat(order.getHost().getMember().getNo()).isEqualTo(member.getNo());
		assertThat(order.getStore().getNo()).isEqualTo(store.getNo());
		assertThat(order.getStandby()).isEqualTo(1);
		assertThat(order.getMinlimit()).isEqualTo(3);
		assertThat(order.getWaitingmems().getMembers().get(0).getNo()).isEqualTo(member.getNo());
		
	}
	
	@Test(expected=ForbiddenException.class)
	public void 호스트주문생성에러 () {
		//given 
		Member member = memberRepository.findByName("aa").get();
		Store store1 = storeRepository.findByName("bana").get();
		Store store2 = storeRepository.findByName("starbucks").get();
		
		orderService.makeOrder(member.getNo(), store1.getNo());
		
		//when
		orderService.makeOrder(member.getNo(), store2.getNo());
		
	}
	
	@Test(expected=ForbiddenException.class)
	public void 대기자주문생성에러 () {
		//given 
		Member member1 = memberRepository.findByName("aa").get();
		Member member2 = memberRepository.findByName("bb").get();
		Store store1 = storeRepository.findByName("bana").get();
		Store store2 = storeRepository.findByName("starbucks").get();
		
		orderService.makeOrder(member1.getNo(), store1.getNo());
		Waiting order = waitingRepository.findAll().get(0);

		orderService.addMemberToOrder(member2.getNo(), order.getNo());
		
		//when
		orderService.makeOrder(member2.getNo(), store2.getNo());
		
	}
	
	@Test
	public void 대기인원추가 () {
		//given
		Member member1 = memberRepository.findByName("aa").get();
		Member member2 = memberRepository.findByName("bb").get();
		Store store = storeRepository.findByName("bana").get();

		orderService.makeOrder(member1.getNo(), store.getNo());
		
		Waiting order = waitingRepository.findAll().get(0);
		
		//when
		orderService.addMemberToOrder(member2.getNo(), order.getNo());
		
		//then
		Waiting addedOrder = waitingRepository.findAll().get(0);
		assertThat(addedOrder.getStandby()).isEqualTo(2);
		assertThat(addedOrder.getWaitingmems().getMembers().size()).isEqualTo(2);
	}
	
	@Test(expected=ForbiddenException.class)
	public void 호스트주문참여에러 () {
		//given
		Member member1 = memberRepository.findByName("aa").get();
		Member member2 = memberRepository.findByName("bb").get();
		Store store1 = storeRepository.findByName("bana").get();
		Store store2 = storeRepository.findByName("starbucks").get();
		
		orderService.makeOrder(member1.getNo(), store1.getNo());
		orderService.makeOrder(member2.getNo(), store2.getNo());
		
		Waiting order1 = waitingRepository.findAll().get(0);
		Waiting order2 = waitingRepository.findAll().get(1);
		
		//when
		orderService.addMemberToOrder(member2.getNo(), order1.getNo());
	
	}
	
	@Test(expected=ForbiddenException.class)
	public void 주문중복참여에러 () {
		//given
		Member member1 = memberRepository.findByName("aa").get();
		Member member2 = memberRepository.findByName("bb").get();
		Member member3 = memberRepository.findByName("cc").get();
		Store store1 = storeRepository.findByName("bana").get();
		Store store2 = storeRepository.findByName("starbucks").get();
		
		orderService.makeOrder(member1.getNo(), store1.getNo());
		orderService.makeOrder(member2.getNo(), store2.getNo());
		
		Waiting order1 = waitingRepository.findAll().get(0);
		Waiting order2 = waitingRepository.findAll().get(1);
		
		orderService.addMemberToOrder(member3.getNo(), order1.getNo());
		
		//when
		orderService.addMemberToOrder(member3.getNo(), order2.getNo());
	
	}
	
	@Test
	public void 대기인원삭제 () {
		//given
		Member member1 = memberRepository.findByName("aa").get();
		Member member2 = memberRepository.findByName("bb").get();
		Store store = storeRepository.findByName("bana").get();

		orderService.makeOrder(member1.getNo(), store.getNo());
		Waiting order = waitingRepository.findAll().get(0);
		orderService.addMemberToOrder(member2.getNo(), order.getNo());
		
		//when
		orderService.deleteMemberToOrder(member2.getNo(), order.getNo());
		
		//then
		Waiting deletedOrder = waitingRepository.findAll().get(0);
		assertThat(deletedOrder.getStandby()).isEqualTo(1);
		assertThat(deletedOrder.getWaitingmems().getMembers().size()).isEqualTo(1);
	}
	
	@Test
	public void 주문삭제 () {
		//given
		Member member1 = memberRepository.findByName("aa").get();
		Member member2 = memberRepository.findByName("bb").get();
		Store store1 = storeRepository.findByName("bana").get();
		Store store2 = storeRepository.findByName("starbucks").get();

		orderService.makeOrder(member1.getNo(), store1.getNo());
		orderService.makeOrder(member2.getNo(), store2.getNo());
		Host host1 = hostRepository.findAll().get(0);
		Host host2 = hostRepository.findAll().get(1);
		Waiting order = waitingRepository.findAll().get(0);
		
		//when
		orderService.cancelOrder(host1.getNo(), order.getNo());
		
		//then
		List<Waiting> orderlist = waitingRepository.findAll();
		assertThat(orderlist.size()).isEqualTo(1);
		assertThat(orderlist.get(0).getHost().getNo()).isEqualTo(host2.getNo());
	}
	
	@Test(expected=ForbiddenException.class)
	public void 일반멤버주문삭제오류 () {
		//given
		Member member1 = memberRepository.findByName("aa").get();
		Member member2 = memberRepository.findByName("bb").get();
		Store store1 = storeRepository.findByName("bana").get();
		Store store2 = storeRepository.findByName("starbucks").get();

		orderService.makeOrder(member1.getNo(), store1.getNo());
		orderService.makeOrder(member2.getNo(), store2.getNo());
		Host host1 = hostRepository.findAll().get(0);
		Host host2 = hostRepository.findAll().get(1);
		Waiting order = waitingRepository.findAll().get(0);
		
		//when
		orderService.cancelOrder(host2.getNo(), order.getNo());
		
	}
}
