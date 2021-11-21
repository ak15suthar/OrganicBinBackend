package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.StatusBean;

@Repository
public class StatusDao {

	@Autowired
	JdbcTemplate stmt;

	public void addStatus(StatusBean statusBean) {
		stmt.update("insert into status(statusname) values(?)", statusBean.getStatusName());

	}
}
