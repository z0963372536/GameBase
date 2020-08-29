package tw.twcoding.leolee.user.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserProfile {

	@Column(name = "USERID")
	private Integer userId;
	@Id
	@Column(name = "PROFILEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "NICKNAME")
	private String nickName;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "AGE")
	private Integer age;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "IMG")
	private String img;

	public UserProfile() {

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", profileId=" + profileId + ", name=" + name + ", gender=" + gender
				+ ", nickName=" + nickName + ", phone=" + phone + ", age=" + age + ", address=" + address + ", img="
				+ img + "]";
	}

}
