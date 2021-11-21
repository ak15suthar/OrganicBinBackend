package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.ApplicationBean;

@Repository
public class ApplicationDao {

	@Autowired
	JdbcTemplate stmt;

	public void addApplication(ApplicationBean applicationBean) {
		applicationBean.setStatusId(1);
		stmt.update(
				"insert into application(applicationdate,noofbags,collectaddress,sizeofbag,statusid,userid) values(?,?,?,?,?,?)",
				applicationBean.getApplicationDate(), applicationBean.getNoOfBags(), applicationBean.getCollectAddress(),
				applicationBean.getSizeOfBag(), applicationBean.getStatusId(), applicationBean.getUserId());

	}

	public List<ApplicationBean> listAllApplication(List<ApplicationBean> bean) {
		bean = stmt.query("select * from application", BeanPropertyRowMapper.newInstance(ApplicationBean.class));
		return bean;
	}

	public List<ApplicationBean> listAppointment(int userId) {
		List<ApplicationBean> applicationBean = stmt.query("select a.*,s.* from application as a ,signup as s where a.userid=s.userid and a.userid=? ",
				new Object[] { userId }, BeanPropertyRowMapper.newInstance(ApplicationBean.class));
		return applicationBean;
	}

	public void acceptApplication(ApplicationBean applicationBean) {
		stmt.update("update application set statusid = ? where applicationno = ?",
				applicationBean.getStatusId(), applicationBean.getApplicationNo());
	}

}
