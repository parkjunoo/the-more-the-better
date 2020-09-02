package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = false)
public class Member 
{
	private @Id @GeneratedValue Long mem_no; // 사용자 고유번호	
	private String mem_id; // 사용자 아이디
	private String mem_name; // 사용자 이름
	private String mem_pw; // 사용자 비밀번호
	private String mem_class; // 수강 중인 수업 정보가 담긴 코드
	private int mem_ishost; // 파티 주최자 여부
	
	// 생성자
	public Member()
	{
		super();
	}
	
	public Member(String mem_name, String mem_id, String mem_pw, String mem_class, int mem_ishost) 
	{
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_class = mem_class;
		this.mem_ishost = mem_ishost;
	}

	// Getter & Setter
	public Long getMem_no() 
	{
		return mem_no;
	}

	public void setMem_no(Long mem_no) 
	{
		this.mem_no = mem_no;
	}

	public String getMem_id() 
	{
		return mem_id;
	}

	public void setMem_id(String mem_id) 
	{
		this.mem_id = mem_id;
	}

	public String getMem_pw() 
	{
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) 
	{
		this.mem_pw = mem_pw;
	}

	public String getMem_class() 
	{
		return mem_class;
	}

	public void setMem_class(String mem_class) 
	{
		this.mem_class = mem_class;
	}

	public int getMem_ishost() 
	{
		return mem_ishost;
	}

	public void setMem_ishost(int mem_ishost) 
	{
		this.mem_ishost = mem_ishost;
	}

	public String getMem_name() 
	{
		return mem_name;
	}

	public void setMem_name(String mem_name) 
	{
		this.mem_name = mem_name;
	}	
}	