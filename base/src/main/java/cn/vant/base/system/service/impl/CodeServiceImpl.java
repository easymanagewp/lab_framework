package cn.vant.base.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.dao.ICodeDao;
import cn.vant.base.system.po.Code;
import cn.vant.base.system.service.ICodeService;
import cn.vant.base.system.vo.CodeVo;

@Service("sys.codeService")
public class CodeServiceImpl extends BaseServiceImpl<CodeVo, Code> implements
		ICodeService {
	
	@Autowired ICodeDao codeDao;
	
	@Override
	public List<String> list(String busInfo, String code) throws GlobalException {
		return codeDao.list(busInfo, code);
	}

	@Override
	public boolean update(String busInfo, String code, String appendContent)
			throws GlobalException {
		return codeDao.update(busInfo,code,appendContent);
	}
}
