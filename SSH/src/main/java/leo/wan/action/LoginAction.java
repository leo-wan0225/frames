package leo.wan.action;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	//跳转到登录页面
	public String login(){
		return "login";
	}
	public String doLogin(){
		return "main";
	}
	public String register(){
		return "register";
	}
	public String doRegister(){
		return "login";
	}
}
