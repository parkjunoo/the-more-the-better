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
	
	@Lob
	@Column(name="ST_PICTURE")
	private String picture; // 가게 사진 


	@Builder
	public Store(String name, String address, String picture) {
		this.name = name;
		this.address = address;
		this.picture = picture;
	}
	
}	