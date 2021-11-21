package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.bean.SignupBean;

@Repository
public class SignupDao {

	@Autowired
	JdbcTemplate stmt;

	public void signupUser(SignupBean signupBean) {

		stmt.update(
				"insert into signup(firstname,lastname,email,password,gender,phoneno,dob,address,city,pincode) values(?,?,?,?,?,?,?,?,?,?)",
				signupBean.getFirstName(), signupBean.getLastName(), signupBean.getEmail(), signupBean.getPassword(),
				signupBean.getGender(), signupBean.getPhoneno(), signupBean.getDob(), signupBean.getAddress(),
				signupBean.getCity(), signupBean.getPincode());

	}

	public SignupBean login(String email, String password) {
		SignupBean signupBean = null;

		try {
			signupBean = stmt.queryForObject("select * from signup where email=? and password=?",
					BeanPropertyRowMapper.newInstance(SignupBean.class), new Object[] { email, password });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return signupBean;
	}

	public SignupBean getUserByEmail(String email) {
		SignupBean userBean = null;
		try {
			List<SignupBean> users = stmt.query("select * from signup where email = ?", new Object[] { email },
					BeanPropertyRowMapper.newInstance(SignupBean.class));
			if (users.size() != 0) {
				userBean = users.get(0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		System.out.println("dato ==> " + userBean);

		return userBean;
	}

	public SignupBean getUserById(int userId) {
		SignupBean bean = null;

		try {
			bean = stmt.queryForObject("select * from signup where userid = ?",
					BeanPropertyRowMapper.newInstance(SignupBean.class), new Object[] { userId });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	public void updateUser(SignupBean signupBean) {

		stmt.update(
				"update signup set firstname=?,lastname=?,email=?,password=?,gender=?,dob=?,address=?,city=?,pincode=?,phoneno=? where userid=?",
				signupBean.getFirstName(), signupBean.getLastName(), signupBean.getEmail(), signupBean.getPassword(),
				signupBean.getGender(), signupBean.getDob(), signupBean.getAddress(), signupBean.getCity(),
				signupBean.getPincode(),signupBean.getPhoneno(), signupBean.getUserId());

	}
}
