package kr.ac.mjc.sungbin.springmvc.user;

import lombok.Data;

@Data
public class User {

	private String id;
	private String email;
	private String password;
	private String name;

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
}