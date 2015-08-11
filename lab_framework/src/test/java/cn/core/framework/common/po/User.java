package cn.core.framework.common.po;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="test_user")
@Table(name="user")
public class User extends Po<User> {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
