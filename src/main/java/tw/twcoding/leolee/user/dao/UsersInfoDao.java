package tw.twcoding.leolee.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.twcoding.leolee.user.model.UsersInfo;

@Repository
public class UsersInfoDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UsersInfo getByAcct(String acct) {
		String sqlStr = "From UsersInfo where account=:acct";
		UsersInfo usersInfo = null;
		try {
			Query<UsersInfo> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UsersInfo.class);
			query.setParameter("acct", acct);
			usersInfo = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UsersInfoDao getByAcct Exception: " + e.getMessage());
			return usersInfo;
		}
		return usersInfo;
	}

	public UsersInfo getByUserId(Integer userId) {
		String sqlStr = "From UsersInfo where userId=:userId";
		UsersInfo usersInfo = null;
		try {
			Query<UsersInfo> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UsersInfo.class);
			query.setParameter("userId", userId);
			usersInfo = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UsersInfoDao getByUserId Exception: " + e.getMessage());
			return usersInfo;
		}
		return usersInfo;
	}

	public List<UsersInfo> getFriendListByUserId(Integer userId) {
		String sqlStr = "Select b.* From Friends a inner join UsersInfoView b on a.friendId = b.userId where a.userId=:userId";
		List<UsersInfo> friendList = null;
		try {
			Query<UsersInfo> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UsersInfo.class);
			query.setParameter("userId", userId);
			friendList = query.list();
		} catch (Exception e) {
			System.out.println("UsersInfoDao getFriendListByUserId Exception: " + e.getMessage());
			return friendList;
		}
		return friendList;
	}
}
