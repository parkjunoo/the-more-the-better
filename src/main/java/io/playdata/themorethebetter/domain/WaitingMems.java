package io.playdata.themorethebetter.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@Table(name="WAITING_MEMS")
@Entity
@EqualsAndHashCode(callSuper = false)
public class WaitingMems 
{
	@Id @GeneratedValue
	@Column(name="WAIT_MEM_NO")
	private Long wait_mem_no; // 대기명단 고유번호
	
	@OneToOne(optional=false)
	@JoinColumn(name="WAIT_NO")
	private Waiting wait_mem_order; // 대기하고 있는 주문 정보 
	
	@OneToMany(mappedBy="mem_wait")
	private List<Member> wait_mem_mems = new ArrayList<Member>(); // 대기하고 있는 멤버 정보 
	
	
}	