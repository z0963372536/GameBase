package tw.twcoding.leolee.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "rank")
@Component
public class Rank {
	@Id
	@Column(name = "RANKID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rankId;
	@Column(name = "RANK")
	private String rank;

	public Rank() {

	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}
