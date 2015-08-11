package cn.vant.base.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.exception.GlobalException;
import cn.core.framework.utils.StrUtils;
import cn.vant.base.system.dao.ICodeDao;
import cn.vant.base.system.po.Code;

@Repository("sys.codeDao")
public class CodeDaoImpl extends BaseDaoImpl<Code> implements ICodeDao {

	@Override
	public Code find(String busInfo, String code) throws GlobalException {
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("busInfo",QueryCondition.EQ,busInfo));
		queryList.add(new QueryCondition("code",QueryCondition.EQ,code));
		List<?> list =  super.query(queryList, null, -1, -1);
		if(null==list)
			throw new GlobalException("未获取到有效数据！！");	
		if(1!=list.size())
			throw new GlobalException("代码参数不唯！！");
		return (Code)list.get(0);
	}
	
	@Override
	public List<String> list(String busInfo, String code) throws GlobalException {
		Code codePo =  find(busInfo, code);
		return toList(codePo.getContent());
	}
	
	private List<String> toList(String content) {
		content = content.replace("，", ",");
		return StrUtils.splitList(content, ',');
	}

	@Override
	public boolean update(String busInfo, String code, String appendContent)
			throws GlobalException {
		Code codePo =  find(busInfo, code);
		//已存在
		if(toList(codePo.getContent()).contains(appendContent))
			return false;
		//追加
		codePo.setContent(codePo.getContent()+","+appendContent);
		super.update(codePo);
		
		return true;
	}

	
}
	