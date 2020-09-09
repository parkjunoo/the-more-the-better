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
@Entity
public class Event extends BaseTimeEntity
{
	@Id @GeneratedValue
	@Column(name="EVT_NO")
	private Long no; // 이벤트 고유번호
	
	@OneToOne(optional=false)
	@JoinColumn(name="ST_NO")
	private Store store; // 이벤트하는 가게 정보
	
	@Column(name="EVT_CONTENTS", length=100)
	private String contents; // 이벤트 내용 

	@Builder
	public Event(Store store, String contents) {
		this.store = store;
		this.contents = contents;
	}
	
}	