package com.damvinod.springboot.basics.springbootin10steps;

public class Book {

  private long id;
  private String authour;
  private String name;

  public Book(long id, String authour, String name) {
    super();
    this.id = id;
    this.authour = authour;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", authour=" + authour + ", name=" + name + "]";
  }

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getAuthour() {
		return authour;
	}
	
	public void setAuthour(String authour) {
		this.authour = authour;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
