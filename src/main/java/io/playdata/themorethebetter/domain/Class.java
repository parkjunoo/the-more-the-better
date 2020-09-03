package io.playdata.themorethebetter.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Class 
{
	@Id @GeneratedValue
	@Column(name="CLASS_NO")
	private Long class_no; // 수업 고유번호	
	
	@Column(length=20)
	private String class_name; // 수업 이름
	
	@Column(length=4, nullable=false)
	private String class_code; // 수업 코드 
	
	@OneToMany(mappedBy="mem_class")
	private List<Member> class_members = new ArrayList<Member>(); // 수강중인 사용자 정보 
}	