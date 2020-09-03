package io.playdata.themorethebetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class Host 
{
	@Id @GeneratedValue
	@Column(name="HOST_NO")
	private Long host_no; // 호스트 고유번호
	
	@OneToOne(mappedBy="wait_host", optional=false)
	private Waiting host_order; // 맡은 주문 정보 
	
	@OneToOne(optional=false) // 멤버정보가 꼭 있어야함 
	@JoinColumn(name="MEM_NO")
	private Member host_member; // 호스트의 사용자 정보 
	
}	