package cn.core.framework.spring;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.View;

import cn.core.framework.common.action.RestResp;
import cn.core.framework.exception.EntityExistedException;
import cn.core.framework.exception.EntityNotFindException;
import cn.core.framework.exception.InvalidIdException;
import cn.core.framework.utils.json.JSONTools;

public class RestView implements View {
	private Logger log = Logger.getLogger(RestView.class);

	private Object responseValue;
	private Exception exception;
	
	public RestView(Object responseValue){
		this(responseValue,null);
	}
	
	public RestView(Object responseValue,Exception e){
		this.responseValue = responseValue;
		this.exception = e;
	}
	
	@Override
	public String getContentType() {
		return "application/json;charset=utf-8";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RestResp resp = null;
		if( null == responseValue && null == this.exception){
			resp = new RestResp();
			resp.setStatus(RestResp.SUCCESS);
			resp.setMessage("操作成功");
		}else if( null == responseValue && null != this.exception){
			resp = handlerException();
		}else if(RestResp.class.isAssignableFrom(responseValue.getClass())){
			resp = (RestResp) responseValue;
		}else if( null != responseValue && null == this.exception){
			resp = new RestResp();
			resp.setStatus(RestResp.SUCCESS);
			resp.setMessage("操作成功");
			resp.setResult(this.responseValue);
		}
		String value = JSONTools.toJSON(resp);
		response.setContentType(getContentType());
		PrintWriter pw = response.getWriter();
		pw.write(value);
	}

	private RestResp handlerException() {
		RestResp resp = new RestResp();
		if(EntityExistedException.class.isAssignableFrom(this.exception.getClass())){
			log.error("操作失败，愿意是请求操作数据的已经存在，而要求是数据不存在的",this.exception);
			resp.setMessage("操作失败，愿意是请求操作数据的已经存在，而要求是数据不存在的");
		}else if(InvalidIdException.class.isAssignableFrom(this.exception.getClass())){
			log.error("操作失败，原因是请求操作的数据的id无法验证通过",this.exception);
			resp.setMessage("操作失败，原因是请求操作的数据的id无法验证通过");
		}else if(EntityNotFindException.class.isAssignableFrom(this.exception.getClass())){
			log.error("操作失败，没有查找人任何可用的数据",this.exception);
			resp.setStatus(RestResp.EMPTY);
			resp.setMessage("操作失败，没有查找人任何可用的数据");
		}else{
			log.error("不可预知的异常",this.exception);
			resp.setMessage("操作失败");
		}
		return resp;
	}

}
