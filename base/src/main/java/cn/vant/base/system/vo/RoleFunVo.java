package cn.vant.base.system.vo;

import cn.core.framework.common.vo.Vo;

public class RoleFunVo extends Vo<RoleFunVo> {
	
	private static final long serialVersionUID = 1L;
	private RoleVo roleVo;
	private FunctionVo functionVo;
	public RoleVo getRoleVo() {
		return roleVo;
	}
	public void setRoleVo(RoleVo roleVo) {
		this.roleVo = roleVo;
	}
	public FunctionVo getFunctionVo() {
		return functionVo;
	}
	public void setFunctionVo(FunctionVo functionVo) {
		this.functionVo = functionVo;
	}
	
	
	
}