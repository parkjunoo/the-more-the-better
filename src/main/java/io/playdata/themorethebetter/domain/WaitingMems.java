package io.playdata.themorethebetter.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="WAITING_MEMS")
@Entity
public class WaitingMems extends BaseTimeEntity
{
	@Id @GeneratedValue
	@Column(name="WAIT_MEM_NO")
	private Long no; // 대기명단 고유번호
	
	@JsonIgnore
	@OneToOne(mappedBy="waitingmems")
	private Waiting order; // 대기하고 있는 주문 정보 
	
	@JsonIgnore
	@OneToMany(mappedBy="waitingmems", fetch=FetchType.EAGER)
	private List<Member> members = new ArrayList<Member>(); // 대기하고 있는 멤버 정보 

	@Builder
	public WaitingMems(Waiting order, List<Member> members) {
		this.order = order;
		this.members = members;
	}

}	