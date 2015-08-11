package cn.vant.base.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.core.framework.common.dao.BaseDaoImpl;
import cn.core.framework.common.page.QueryCondition;
import cn.core.framework.exception.GlobalException;
import cn.vant.base.system.dao.IUploadDao;
import cn.vant.base.system.po.Upload;

@Repository("sys.uploadDao")
public class UploadDaoImpl extends BaseDaoImpl<Upload> implements IUploadDao {

	@Override
	public Upload findByPath(String path) throws GlobalException {
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
		queryList.add(new QueryCondition("path",QueryCondition.EQ,path));
		List<Upload> uploadList = (List<Upload>)query(queryList, null, -1, -1);
		return uploadList.get(0);
	}

	@Override
	public List<Upload> find(String busInfo,String busId) throws GlobalException {
		List<QueryCondition> queryList = new ArrayList<QueryCondition>();
 		queryList.add(new QueryCondition("busInfo",QueryCondition.EQ,busInfo));
		queryList.add(new QueryCondition("busId",QueryCondition.EQ,busId));
		return (List<Upload>)query(queryList, null, -1, -1);
	}
}
