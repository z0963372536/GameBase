package tw.twcoding.leolee.user.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.twcoding.leolee.user.dao.UserDataDao;
import tw.twcoding.leolee.user.dao.UsersInfoDao;
import tw.twcoding.leolee.user.model.UserData;
import tw.twcoding.leolee.user.model.UsersInfo;
import tw.twcoding.leolee.util.MailSender;

@Service
public class UsersService {

	@Autowired
	private UserDataDao userDataDao;
	@Autowired
	private UsersInfoDao userInfoDao;
	@Autowired
	private MailSender mailSender;

	public UserData checkAcctAndEmail(UserData userData) {
		String acct = userData.getAccount();
		String email = userData.getEmail();
		UserData finUserData = null;
		try {
			finUserData = userDataDao.getByAcctAndEmail(acct, email);
		} catch (Exception e) {
			System.out.println("checkAcctAndEmail Exception: " + e.getMessage());
			return finUserData;
		}
		return finUserData;
	}

	public Map<String, Object> getByLogin(UserData userData) {
		String acct = userData.getAccount();
		String pwd = userData.getPassword();
		String encryptPwd = getMD5Endocing(pwd);
		UserData loginUserData = null;
		UsersInfo loginUsersInfo = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			loginUserData = userDataDao.getByAcctPwd(acct, encryptPwd);
			if (loginUserData != null) {
				loginUsersInfo = showUsersInfo(acct);
			}
			result.put("loginUser", loginUsersInfo);
			result.put("status", true);
		} catch (Exception e) {
			System.out.println("UsersService getByLogin Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

	private String getMD5Endocing(String message) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = Byte.toUnsignedInt(b);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	private String genRandomPwd(int pwd_len) {
		final int maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		try {
			Random r = new Random();
			while (count < pwd_len) {
				i = Math.abs(r.nextInt(maxNum));
				if (i >= 0 && i < str.length) {
					pwd.append(str[i]);
					count++;
				}
			}
		} catch (Exception e) {
			System.out.println("genRandomPwd Exception:" + e.getMessage());
		}
		return pwd.toString();
	}

	private UsersInfo showUsersInfo(String acct) {
		UsersInfo usersInfo = null;
		List<UsersInfo> friends = null;
		try {
			usersInfo = userInfoDao.getByAcct(acct);
			friends = userInfoDao.getFriendListByUserId(usersInfo.getUserId());
			usersInfo.setFriendsList(friends);
		} catch (Exception e) {
			System.out.println("UsersService showUsersInfo Exception: " + e.getMessage());
			return usersInfo;
		}
		return usersInfo;
	}
}
