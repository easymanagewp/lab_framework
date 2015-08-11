package cn.core.framework.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.core.framework.common.po.Po;
import cn.core.framework.utils.code.ActionType;
import cn.core.framework.utils.code.CreateCodeUtils;
import cn.core.framework.utils.code.annotation.Module;

@Entity(name="test_user")
@Table(name="user")
@Module("sys.user")
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
	
	public static void main(String[] args) {
		CreateCodeUtils.autoCreateCode(User.class, false, ActionType.JSP);
	}
}
