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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Store extends BaseTimeEntity
{
	@Id @GeneratedValue
	@Column(name="ST_NO")
	private Long no; // 가게 고유번호 
	
	@Column(name="ST_NAME", length=20, nullable=false)
	private String name; // 가게 이름 
	
	@Column(name="ST_ADDR", length=50, nullable=false)
	private String address; // 가게 주소 
	
	@ColumnDefault("0")
	@Column(name="ST_DELIPAY")
	private Long delipay; // 배달비
	
	@ColumnDefault("15000")
	@Column(name="ST_MINORDER")
	private Long minordpay; // 최소 주문 금액 
	
	@Lob
	@Column(name="ST_PICTURE")
	private Blob picture; // 가게 사진 
	
	@JsonIgnore
	@OneToMany(mappedBy="store")
	private List<Waiting> waiting = new ArrayList<Waiting>(); // 진행중인 주문 정보
	
	@JsonIgnore
	@OneToOne(mappedBy="store")
	private Event event; // 가게의 이벤트 정보 

	@Builder
	public Store(String name, String address, Long delipay, Long minordpay, Blob picture, List<Waiting> waiting,
			Event event) {
		this.name = name;
		this.address = address;
		this.delipay = delipay;
		this.minordpay = minordpay;
		this.picture = picture;
		this.waiting = waiting;
		this.event = event;
	}
	
}	