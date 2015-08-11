package cn.vant.base.system.vo;

import java.io.File;

import cn.core.framework.common.vo.Vo;
public class AccountVo extends Vo<AccountVo> {
	public static final String SYS_SIGNATURE = "sys-signature"; 
	private static final long serialVersionUID = 1L;
	private UserVo userVo;//用户信息Id
	private OrgVo orgVo;//组织
	private String loginName;//登陆名称
	private String password;//登陆的密码
	private String status;//状态
	private String isUsed;//是否可用
	private String roleIds;//选中角色
	private String signature;//签章
	private File file;//签章
	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrgVo getOrgVo() {
		return orgVo;
	}

	public void setOrgVo(OrgVo orgVo) {
		this.orgVo = orgVo;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
