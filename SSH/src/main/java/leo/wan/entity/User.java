package leo.wan.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer userId;
	private String userName;
	private String pwd;
	private String email;
	private String tel;
	private Integer isActive;
	private Date createTime;
	private Set<LoginInfo> loginInfos = new HashSet<LoginInfo>();
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Set<LoginInfo> getLoginInfos() {
		return loginInfos;
	}
	public void setLoginInfos(Set<LoginInfo> loginInfos) {
		this.loginInfos = loginInfos;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", pwd="
				+ pwd + ", email=" + email + ", tel=" + tel + ", isActive="
				+ isActive + ", createTime=" + createTime + ", loginInfos="
				+ loginInfos + "]";
	}
	
}
