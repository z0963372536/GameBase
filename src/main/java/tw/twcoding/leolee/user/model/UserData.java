package tw.twcoding.leolee.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "userData")
@Component
public class UserData {

	@Id
	@Column(name = "USERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "ACCOUNT")
	private String account;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "RANKID")
	private Integer rankId;
	@Column(name = "REGIESTDATE")
	private String regiestdate;
	
	public UserData() {
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public String getRegiestdate() {
		return regiestdate;
	}

	public void setRegiestdate(String regiestdate) {
		this.regiestdate = regiestdate;
	}

	@Override
	public String toString() {
		return "UserData [userId=" + userId + ", account=" + account + ", password=" + password + ", email=" + email
				+ ", rankId=" + rankId + ", regiestdate=" + regiestdate + "]";
	}
	
}
