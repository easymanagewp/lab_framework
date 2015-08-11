package cn.vant.base.system.service.impl;

import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.vant.base.system.po.Duty;
import cn.vant.base.system.service.IDutyService;
import cn.vant.base.system.vo.DutyVo;

@Service("sys.dutyService")
public class DutyServiceImpl extends BaseServiceImpl<DutyVo, Duty> implements
		IDutyService {}
