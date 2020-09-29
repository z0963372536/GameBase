package tw.twcoding.leolee.user.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.twcoding.leolee.user.dao.RankDao;
import tw.twcoding.leolee.user.dao.UserDataDao;
import tw.twcoding.leolee.user.dao.UserProfileDao;
import tw.twcoding.leolee.user.dao.UsersInfoDao;
import tw.twcoding.leolee.user.model.Rank;
import tw.twcoding.leolee.user.model.UserData;
import tw.twcoding.leolee.user.model.UserProfile;
import tw.twcoding.leolee.user.model.UsersInfo;
import tw.twcoding.leolee.util.MailSender;

@Service
public class UsersService {

	@Autowired
	private UserDataDao userDataDao;
	@Autowired
	private UsersInfoDao userInfoDao;
	@Autowired
	private UserProfileDao userprofileDao;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private RankDao rankDao;

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

	public void saveUserProfile(UserProfile userProfile) {
		try {
			userprofileDao.saveUserProfile(userProfile);
		} catch (Exception e) {
			System.out.println("UsersService saveUserProfile Exception: " + e.getMessage());
		}
	}

	public Integer getProfileIdByUserId(UserData userData) {
		Integer userId = userData.getUserId();
		Integer profileId = null;
		try {
			profileId = userprofileDao.getProfileIdByUserId(userId);
		} catch (Exception e) {
			System.out.println("UsersService getProfileIdByUserId Exception: " + e.getMessage());
			return profileId;
		}
		return profileId;
	}

	public UserProfile getProfileByUserId(UserData userData) {
		Integer userId = userData.getUserId();
		UserProfile userProfile = null;
		try {
			userProfile = userprofileDao.getByUserId(userId);
		} catch (Exception e) {
			System.out.println("UsersService getProfileByUserId Exception: " + e.getMessage());
			return userProfile;
		}
		return userProfile;
	}

	public List<Rank> getAllRank() {
		return rankDao.getAllRanks();
	}

	public Rank getRankById(Integer rankId) {
		Rank rank = null;
		if (rankId != 0) {
			try {
				rank = rankDao.getByRankId(rankId);
			} catch (Exception e) {
				System.out.println("UsersService getRankById Exception: " + e.getMessage());
				return rank;
			}
		}
		return rank;
	}

	public boolean processForgetPwd(UserData userData) {
		UserData resetUserData = null;
		boolean status = false;
		try {
			resetUserData = resetPwdCheck(userData);
			String pwdString = resetPwd(resetUserData);
			if (!pwdString.isEmpty()) {
				status = sendNewPwd(resetUserData, pwdString);
			}
		} catch (Exception e) {
			System.out.println("UsersService processForgetPwd Exception: " + e.getMessage());
			return status;
		}
		return status;
	}

	public List<UserData> getAllUserDatas(UserData userData) {
		List<UserData> userDatas = null;
		try {
			if (userData.getRankId() == 4) {
				userDatas = userDataDao.getAllUserData();
			}
		} catch (Exception e) {
			System.out.println("UsersService getAllUserDatas Exception: " + e.getMessage());
			return userDatas;
		}
		return userDatas;
	}

	public boolean checkAcct(String acct) {
		boolean result = false;
		UserData userData = null;
		try {
			if (!acct.isEmpty()) {
				userData = userDataDao.checkAcct(acct);
			}
			if (userData != null) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("UsersService checkAcct Exception: " + e.getMessage());
			return result;
		}
		return result;
	}
	
	public void deleteUserData(UserData userData) {
		//TODO 
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

	private UserData resetPwdCheck(UserData userData) {
		String acct = userData.getAccount();
		String email = userData.getEmail();
		UserData findData = null;
		try {
			findData = userDataDao.getByAcctAndEmail(acct, email);
		} catch (Exception e) {
			System.out.println("UsersService resetPwdCheck Exception: " + e.getMessage());
			return findData;
		}
		return findData;
	}

	private String resetPwd(UserData userData) {
		String newPwd = "";
		String encryptPwd = "";
		try {
			newPwd = genRandomPwd(8);
			encryptPwd = getMD5Endocing(newPwd);
			userData.setPassword(encryptPwd);
			userDataDao.addUserData(userData);
		} catch (Exception e) {
			System.out.println("UsersService resetPwd Exception: " + e.getMessage());
			return newPwd;
		}
		return newPwd;
	}

	private boolean sendNewPwd(UserData userData, String newPwd) {
		boolean status = false;
		String mailTo = userData.getEmail();
		try {
			status = mailSender.pwdMailSender(mailTo, newPwd);
		} catch (Exception e) {
			System.out.println("UsersService sendNewPwd Exception: " + e.getMessage());
			return status;
		}
		return status;
	}

	private String genRandomPwd(Integer pwd_len) {
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
