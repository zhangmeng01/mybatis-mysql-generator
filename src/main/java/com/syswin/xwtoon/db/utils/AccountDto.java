package com.syswin.xwtoon.db.utils;

import java.sql.Timestamp;
import java.util.Date;

public class AccountDto {
	private Integer id;
	private Timestamp createTime;
	private Long balance;
	private String name;
	private Date ActTime;
	private Long userId;

	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getActTime() {
		return ActTime;
	}

	public void setActTime(Date actTime) {
		ActTime = actTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
