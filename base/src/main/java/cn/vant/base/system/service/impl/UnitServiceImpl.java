package cn.vant.base.system.service.impl;

import org.springframework.stereotype.Service;

import cn.core.framework.common.service.BaseServiceImpl;
import cn.vant.base.system.po.Unit;
import cn.vant.base.system.service.IUnitService;
import cn.vant.base.system.vo.UnitVo;

@Service("sys.unitService")
public class UnitServiceImpl extends BaseServiceImpl<UnitVo, Unit> implements
		IUnitService {}
