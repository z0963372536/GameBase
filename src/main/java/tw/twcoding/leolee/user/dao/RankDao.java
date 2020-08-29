package tw.twcoding.leolee.user.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.twcoding.leolee.user.model.Rank;

@Repository
public class RankDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Rank getByRankId(Integer rankId) {
		String sqlString = "From Rank where rankId=:rankId";
		Rank rank = null;
		try {
			Query<Rank> query = sessionFactory.getCurrentSession().createQuery(sqlString, Rank.class);
			query.setParameter("rankId", rankId);
			rank = query.uniqueResult();
		} catch (Exception e) {
			System.out.println("RankDao getByRankId Exception: " + e.getMessage());
			return rank;
		}
		return rank;
	}

	public List<Rank> getAllRanks() {
		String sqlStr = "From Rank";
		List<Rank> ranks = null;
		try {
			Query<Rank> query = sessionFactory.getCurrentSession().createQuery(sqlStr, Rank.class);
			ranks = query.list();
		} catch (Exception e) {
			System.out.println("RankDao getAllRanks Exception: " + e.getMessage());
			return ranks;
		}
		return ranks;
	}
}
