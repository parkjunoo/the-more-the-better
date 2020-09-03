package io.playdata.themorethebetter.domain;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
public class Store 
{
	@Id @GeneratedValue
	@Column(name="ST_NO")
	private Long st_no; // 가게 고유번호 
	
	@Column(length=20, nullable=false)
	private String st_name; // 가게 이름 
	
	@Column(length=50, nullable=false)
	private String st_addr; // 가게 주소 
	
	@ColumnDefault("0")
	private Long st_delipay; // 배달비
	
	@ColumnDefault("15000")
	private Long st_minordpay; // 최소 주문 금액 
	
	@Lob
	private Blob st_picture; // 가게 사진 
	
	@OneToMany(mappedBy="wait_store")
	private List<Waiting> st_waiting = new ArrayList<Waiting>(); // 진행중인 주문 정보
	
	@OneToOne(mappedBy="evt_store")
	private Event st_event; // 가게의 이벤트 정보 
}	