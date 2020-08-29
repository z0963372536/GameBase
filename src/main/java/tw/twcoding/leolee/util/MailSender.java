package tw.twcoding.leolee.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailSender {
	private final String from = "z0983177929@gmail.com";
	private final String username = "z0983177929@gmail.com";
	private final String password = "leoleetwcoding";
	private final String host = "smtp.gmail.com";
	private Properties props;
	private Session session;

	public MailSender() {
		super();
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public String authMailSender(String mailTo) {
		String to = mailTo;
		int i = (int) (Math.random() * (99999 - 1000 + 1) + 1000);
		String captcha = "" + i;
		InternetAddress internetAddress = null;
		try {
			Message message = new MimeMessage(session);
			internetAddress = new InternetAddress(from);
			internetAddress.setPersonal("PHCTW");
			message.setFrom(internetAddress);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("驗證信箱");
			message.setText("您好,\n" + "您的驗證碼:" + captcha);

			System.out.println("Sent message successfully....");
			Transport.send(message);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			return "fail";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "fail";
		}
		return captcha;
	}

	public boolean pwdMailSender(String mailTo, String newPwd) {
		String to = mailTo;
		InternetAddress internetAddress = null;
		try {
			Message message = new MimeMessage(session);
			internetAddress = new InternetAddress(from);
			internetAddress.setPersonal("PHCTW");
			message.setFrom(internetAddress);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("忘記密碼-系統重設密碼");
			message.setText("您好,\n" + "您的新密碼:" + newPwd);

			System.out.println("Sent message successfully....");
			Transport.send(message);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
