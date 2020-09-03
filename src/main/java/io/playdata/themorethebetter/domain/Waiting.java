package io.playdata.themorethebetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Waiting 
{
	@Id @GeneratedValue
	@Column(name="WAIT_NO")
	private Long wait_no; // 주문 고유 번호 
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ST_NO")
	private Store wait_store; // 주문하려는 가게 정보 
	
	@OneToOne(optional=false)
	@JoinColumn(name="HOST_NO")
	private Host wait_host; // 주문하려는 호스트 정보
	
	@ColumnDefault("3")
	private Long wait_minlimit; // 주문 가능한 최소 인원
	
	@OneToOne(mappedBy="wait_mem_order")
	private WaitingMems wait_members; // 대기명단 정보 
}	