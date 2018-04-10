package com.ctbc.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public UserVO() {
	}

	private String userName;
	private String userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		boolean outputTransients = false;
		boolean outputStatics = false;
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE, outputTransients, outputStatics);
	}
}
