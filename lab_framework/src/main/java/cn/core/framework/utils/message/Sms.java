package cn.core.framework.utils.message;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.BooleanUtils;

import cn.core.framework.utils.ApplicationUtils;


	class Sms implements Runnable {
	private String msg;//短信内容
	private String phoneNums;//号码
	
	private static String sn = (String)ApplicationUtils.getValue("config.msg.sms.sn");
	private static String pwd = (String)ApplicationUtils.getValue("config.msg.sms.pwd");
	private static boolean isSend = BooleanUtils.toBoolean((String)ApplicationUtils.getValue("config.msg.sms.isSend"));
	private static String sign = (String)ApplicationUtils.getValue("config.msg.sms.sign");
	
	public Sms(){
		
	}
	
	public Sms(String msg,String phoneNums){
		this.msg=msg;
		this.phoneNums=phoneNums;
	} 
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPhoneNums() {
		return phoneNums;
	}
	public void setPhoneNums(String phoneNums) {
		this.phoneNums = phoneNums;
	}
	public  String sendSms(String msg, String phoneNums) {
		if(!isSend)
			return "发送失败,未开启发送功能";
		if(null==msg||"".equals(msg))
			return "短信内容为空";
		if(null==phoneNums||"".equals(phoneNums))
			return "电话号码为空";
		//输入软件序列号和密码
		String content=null;
		try {
			content = URLEncoder.encode(msg+sign, "utf8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Client client=null;
		try {
			client = new Client(sn,pwd);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result_mt = client.mdSmsSend_u(phoneNums, content, "", "", "");
		String result="";
		if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
		{
			//result = "发送失败！返回值为："+result_mt+"请查看webservice返回值对照表";
			result = "短信【"+phoneNums+"】发送失败！返回值为："+result_mt;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		else
		{
			result = "短信【"+phoneNums+"】发送成功！返回值为："+result_mt;
		}
		return result;
		
	}
	@Override
	public void run() {
		sendSms(msg,phoneNums);
	}
		
}
