package io.playdata.themorethebetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Host extends BaseTimeEntity
{
	@Id @GeneratedValue
	@Column(name="HOST_NO")
	private Long no; // 호스트 고유번호

}	