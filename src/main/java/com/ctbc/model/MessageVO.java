package com.ctbc.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MessageVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public MessageVO() {
	}

	private String name;
	private String sendName;
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		boolean outputTransients = false;
		boolean outputStatics = false;
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE, outputTransients, outputStatics);
	}
}
