package leo.wan.entity;

import java.util.Date;

public class LoginInfo {
	private Integer userId;
	private String loginIp;
	private String loginArea;
	private Date loginTime;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginArea() {
		return loginArea;
	}
	public void setLoginArea(String loginArea) {
		this.loginArea = loginArea;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	@Override
	public String toString() {
		return "LoginInfo [userId=" + userId + ", loginIp=" + loginIp
				+ ", loginArea=" + loginArea + ", loginTime=" + loginTime + "]";
	}
	
	
}
