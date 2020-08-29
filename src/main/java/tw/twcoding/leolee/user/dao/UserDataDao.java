package tw.twcoding.leolee.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.twcoding.leolee.user.model.UserData;

@Repository
public class UserDataDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUserData(UserData userData) {
		try {
			sessionFactory.getCurrentSession().save(userData);
		} catch (Exception e) {
			System.out.println("UserDataDao addUserData Exception: " + e.getMessage());
		}
	}

	public void deleteUserData(UserData userData) {
		try {
			sessionFactory.getCurrentSession().delete(userData);
		} catch (Exception e) {
			System.out.println("UserDataDao addUserData Exception: " + e.getMessage());
		}
	}

	public UserData getByAcctPwd(String acct, String pwd) {
		String sqlStr = "From UserData Where account=:acct and password=:pwd";
		UserData userData = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			query.setParameter("acct", acct);
			query.setParameter("pwd", pwd);
			userData = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UserDataDao getByAcctPwd Exception: " + e.getMessage());
			return userData;
		}
		return userData;
	}

	public List<UserData> getAllUserData() {
		String sqlStr = "From UserData";
		List<UserData> userDatas = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			userDatas = query.list();
		} catch (Exception e) {
			System.out.println("UserDataDao getAllUserData Exception: " + e.getMessage());
			return userDatas;
		}
		return userDatas;
	}

	public UserData checkAcct(String acct) {
		String sqlStr = "From UserData Where account=:acct";
		UserData userData = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			query.setParameter("acct", acct);
			userData = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UserDataDao checkAcct Exception: " + e.getMessage());
			return userData;
		}
		return userData;
	}

	public UserData getByAcctAndEmail(String acct, String email) {
		String sqlStr = "From UserData Where account=:acct and email=:email";
		UserData userData = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			query.setParameter("acct", acct);
			query.setParameter("email", email);
			userData = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UserDataDao getByAcctAndEmail Exception: " + e.getMessage());
			return userData;
		}
		return userData;
	}

	public UserData getByUserId(Integer uid) {
		String sqlStr = "From UserData Where userId=:uid";
		UserData userData = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			query.setParameter("uid", uid);
			userData = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UserDataDao getByUserId Exception: " + e.getMessage());
			return userData;
		}
		return userData;
	}

	public List<UserData> getByAcctInAllRank(String acct) {
		String sqlStr = "From UserData Where account like:acct";
		List<UserData> userDatas = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			query.setParameter("acct", "%" + acct + "%");
			userDatas = query.list();
		} catch (Exception e) {
			System.out.println("UserDataDao getByAcctInAllRank Exception: " + e.getMessage());
			return userDatas;
		}
		return userDatas;
	}

	public List<UserData> getByAcctInOneRank(String acct, Integer rankid) {
		String sqlStr = "From UserData Where RankId=:rankid and account like:acct";
		List<UserData> userDatas = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			query.setParameter("rankid", rankid);
			query.setParameter("acct", "%" + acct + "%");
			userDatas = query.list();
		} catch (Exception e) {
			System.out.println("UserDataDao getByAcctInOneRank Exception: " + e.getMessage());
			return userDatas;
		}
		return userDatas;
	}

	public List<UserData> getWithoutAdmin() {
		String sqlStr = "From UserData Where RankId<>1";
		List<UserData> userDatas = null;
		try {
			Query<UserData> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserData.class);
			userDatas = query.list();
		} catch (Exception e) {
			System.out.println("UserDataDao getWithoutAdmin Exception: " + e.getMessage());
			return userDatas;
		}
		return userDatas;
	}
}
