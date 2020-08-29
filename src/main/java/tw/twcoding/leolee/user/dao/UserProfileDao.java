package tw.twcoding.leolee.user.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.twcoding.leolee.user.model.UserProfile;

@Repository
public class UserProfileDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserProfileDao() {

	}

	public void saveUserProfile(UserProfile userProfile) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(userProfile);
		} catch (Exception e) {
			System.out.println("UserProfileDao saveUserProfile Exception: " + e.getMessage());
		}
	}

	public Integer getProfileIdByUserId(Integer userId) {
		String sqlStr = "Select profileId from UserProfile where userId=:userId";
		Integer profileId = null;
		try {
			Query<Integer> query = sessionFactory.getCurrentSession().createQuery(sqlStr, Integer.class);
			query.setParameter("userId", userId);
			profileId = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UserProfileDao getProfileIdByUserId Exception: " + e.getMessage());
			return profileId;
		}
		return profileId;
	}

	public UserProfile getByUserId(Integer userId) {
		String sqlStr = "From UsreProfile where userId=:userId";
		UserProfile userProfile = null;
		try {
			Query<UserProfile> query = sessionFactory.getCurrentSession().createQuery(sqlStr, UserProfile.class);
			query.setParameter("userId", userId);
			userProfile = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("UserProfileDao getByUserId Exception: " + e.getMessage());
			return userProfile;
		}
		return userProfile;
	}
}
