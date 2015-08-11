package cn.vant.base.system.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.core.framework.common.action.BaseAction;
import cn.core.framework.log.annotation.Logger;
import cn.vant.base.system.service.IUploadService;
import cn.vant.base.system.vo.UploadVo;
/**
 * <strong>创建信息: 2015年7月8日 上午10:28:57 </strong> <br>
 * <strong>概要 : 附件action</strong> <br>
 */
@Controller("sys.uploadAction")
@RequestMapping("sys/upload")
@Logger(busInfo="系统管理",content="文件上传")
public class UploadAction extends BaseAction<UploadVo>{
	
	final String VIEW_PATH = "/sys/upload/upload";
	@Autowired IUploadService uploadService;
	public String getViewPath() {
		return VIEW_PATH;
	}
}
