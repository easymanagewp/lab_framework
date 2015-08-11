package cn.core.framework.utils.message;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.BooleanUtils;

import cn.core.framework.utils.ApplicationUtils;
import cn.core.framework.utils.StrUtils;


class Mail implements Runnable {
	private static String sends;
	private static String subject;
	private static String content;
	private static String host = (String)ApplicationUtils.getValue("config.msg.mail.host");
	private static String fromMail  = (String)ApplicationUtils.getValue("config.msg.mail.fromMail");
	private static String pwd = (String)ApplicationUtils.getValue("config.msg.mail.pwd");
	private static boolean isSend =  BooleanUtils.toBoolean((String)ApplicationUtils.getValue("config.msg.mail.isSend"));
	
	Mail() {}

	String sendMail(String sends, String subject, String msg) {
		if(!isSend)
			return "发送失败,未开启发送功能";
		if(null==msg||"".equals(msg))
			return "内容为空";
		if(null==sends||"".equals(sends))
			return "收件人为空";
		
		String result = "";
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", true);
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(false);

			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(fromMail));
			sends = sends.replace("，", ",");
			String[] tomails = StrUtils.split(sends, ',');
			InternetAddress[] internetAddresses = new InternetAddress[tomails.length];
			if (null != tomails && tomails.length> 0) {
				for (int i = 0; i < tomails.length; i++) {
					internetAddresses[i] = new InternetAddress(tomails[i].trim());
				}
			}

			mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
			mimeMessage.setSentDate(new Date());
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(msg, "text/html;charset=UTF-8");
			mimeMessage.saveChanges();// 存储邮件信息

			Transport transport = session.getTransport("smtp");
			transport.connect(host, fromMail, pwd);// 以smtp方式登录邮箱
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());// 发送邮件,其中第二个参数是所有
			transport.close();
			result = "邮件发送成功:"+sends;
		} catch (Exception e) {
			e.printStackTrace();
			result = "邮件发送失败:"+sends;
		}
		return result;
	
	}
	
	@Override
	public void run() {
		sendMail(sends, subject, content);
	}

}
