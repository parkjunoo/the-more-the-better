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

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Event 
{
	@Id @GeneratedValue
	@Column(name="EVT_NO")
	private Long evt_no; // 이벤트 고유번호
	
	@OneToOne(optional=false)
	@JoinColumn(name="ST_NO")
	private Store evt_store; // 이벤트하는 가게 정보
	
	@Column(length=100)
	private String evt_contents; // 이벤트 내용 
	
	
}	