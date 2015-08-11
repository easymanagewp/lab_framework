package cn.core.framework.utils.message;

public class MessageUtils {
	/**
	 * 发送短信
	 * @Title:  
	 * @param @param msg 短信内容
	 * @param @param toTels 多个以“,”分隔 
	 * @param @param isMultithreading 是否多线程 
	 * @return 返回类型 
	 * @throws
	 */
	public static String sendSms(String toTels,String msg,boolean isMultithreading) {
		String result = null;
		if(isMultithreading){
			Thread exportThread = new Thread(new Sms(msg, toTels));
			exportThread.run();
		}else{
			result = new Sms().sendSms(msg, toTels);
		}
		return result;
	}
	/**
	 * 发送 email 
	 * @Title:  
	 * @param @param msg 短信内容
	 * @param @param subject 主题
	 * @param @param toMails 多个以“,”分隔 
	 * @param @param isMultithreading 是否多线程
	 * @return 返回类型 
	 * @throws
	 */
	public static String sendMail(String toMails, String subject, String msg,boolean isMultithreading) {
		String result = null;
		if(isMultithreading){
			Thread exportThread = new Thread(new Mail());
			exportThread.run();
		}else{
			result = new Mail().sendMail(toMails, subject,msg);
		}
		return result;
	}
	
	public static void main(String[] args) {
		// System.out.println(MessageUtils.sendMail("1021006960@qq.com","111","1111111111111", false));
		// System.out.println(MessageUtils.sendSms("13468763003", "验证码--111111", false));
	}
	
	
}
