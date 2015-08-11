package cn.vant.base.system.service.impl;

import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.vant.base.system.po.Log;
import cn.vant.base.system.service.ILogService;
import cn.vant.base.system.vo.LogVo;

@Service("sys.logService")
public class LogServiceImpl extends BaseServiceImpl<LogVo, Log> implements
		ILogService {}
