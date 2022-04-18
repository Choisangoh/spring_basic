package com.ict.domain;

//import java.util.Objects;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
import lombok.Data;

@Data //순환참조 문제가 있음
public class TestVO {
	
	private Integer mno;
	private String name;
	private Integer age;
	
	
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "TestVO [mno=" + mno + ", name=" + name + ", age=" + age + "]";
	}
	public TestVO(Integer mno, String name, Integer age) {
		super();
		this.mno = mno;
		this.name = name;
		this.age = age;
	}
	public TestVO() {
		
	}	
}
