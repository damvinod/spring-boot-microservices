package com.damvinod.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Description about user")
public class User {

	private Integer id;
	
	@ApiModelProperty(notes="Minimum size of name is 2 charcters")
	@Size(min=2, message="Minimum size of name is 2 charcters")
	private String name;
	
	@ApiModelProperty(notes="Please enter currtent or future date")
	@Past(message="Please enter currtent or future date")
	private Date bithdate;
	
	protected User(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBithdate() {
		return bithdate;
	}
	public void setBithdate(Date bithdate) {
		this.bithdate = bithdate;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", bithdate=" + bithdate + "]";
	}
	
	public User(Integer id, String name, Date bithdate) {
		super();
		this.id = id;
		this.name = name;
		this.bithdate = bithdate;
	}
}
