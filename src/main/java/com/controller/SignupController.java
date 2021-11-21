package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.SignupBean;
import com.dao.SignupDao;

@CrossOrigin
@RestController
public class SignupController {

	@Autowired
	SignupDao signupDao;

	
	@PostMapping("signup")
	public ResponseBean<SignupBean> signup(@RequestBody SignupBean signupBean) {

		ResponseBean<SignupBean> responseBean = new ResponseBean<SignupBean>();

		if (signupDao.getUserByEmail(signupBean.getEmail()) != null) {
			responseBean.setMsg("Email Already Exist!!");
			responseBean.setStatus(201);
		} else {
			signupDao.signupUser(signupBean);

			responseBean.setData(signupBean);
			responseBean.setMsg("Successfully signup!!");
			responseBean.setStatus(200);
		}
		return responseBean;
	}

	@GetMapping("/getUserById/{userId}")
	public ResponseBean<SignupBean> getUser(@PathVariable("userId") int userId, SignupBean signupBean) {
		signupBean = signupDao.getUserById(userId);

		ResponseBean<SignupBean> responseBean = new ResponseBean<>();

		responseBean.setData(signupBean);
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PutMapping("updateUser")
	public ResponseBean<SignupBean> updateUser(@RequestBody SignupBean signupBean) {

		ResponseBean<SignupBean> responseBean = new ResponseBean<>();

		signupDao.updateUser(signupBean);

		responseBean.setData(signupBean);
		responseBean.setMsg("User Updated");
		responseBean.setStatus(200);

		return responseBean;
	}

	@PostMapping("login")
	public ResponseBean<SignupBean> login(@RequestBody LoginBean loginBean) {
		SignupBean signupBean = null;

		ResponseBean<SignupBean> responseBean = new ResponseBean<>();

		signupBean = signupDao.login(loginBean.getEmail(), loginBean.getPassword());

		if (signupBean != null) {
			responseBean.setData(signupBean);
			responseBean.setMsg("User found!!");
			responseBean.setStatus(200);

		} else {
			responseBean.setMsg("User not found!!");
			responseBean.setStatus(202);
		}

		return responseBean;
	}
}
