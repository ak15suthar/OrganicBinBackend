package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ApplicationBean;
import com.bean.ResponseBean;
import com.dao.ApplicationDao;

@CrossOrigin
@RestController
public class ApplicationController {

	@Autowired
	ApplicationDao applicationDao;

	@PostMapping("application")
	public ResponseBean<ApplicationBean> application(@RequestBody ApplicationBean applicationBean) {

		
		ResponseBean<ApplicationBean> responseBean = new ResponseBean<>();

		applicationDao.addApplication(applicationBean);

		responseBean.setData(applicationBean);
		responseBean.setMsg("Successfully Application Registered!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("listAllApplication")
	public ResponseBean<List<ApplicationBean>> listAllApplication() {

		ResponseBean<List<ApplicationBean>> responseBean = new ResponseBean<>();

		List<ApplicationBean> bean = null;
		bean = applicationDao.listAllApplication(bean);
		
		responseBean.setData(bean);
		responseBean.setMsg("Application list!!");
		responseBean.setStatus(200);
		
		return responseBean;

	}
	
	@GetMapping("/listApplication/{userId}")
	public ResponseBean<List<ApplicationBean>> listAppointment(@PathVariable("userId") int userId) {

		List<ApplicationBean> applicationBean = applicationDao.listAppointment(userId);

		ResponseBean<List<ApplicationBean>> responseBean = new ResponseBean<>();

		responseBean.setData(applicationBean);
		responseBean.setMsg("Appointment Listed!!");
		responseBean.setStatus(202);

		return responseBean;
	}
	
	@PutMapping("/acceptApplication")
	public ResponseBean<ApplicationBean> acceptApplication(@RequestBody ApplicationBean applicationBean) {

		applicationDao.acceptApplication(applicationBean);

		ResponseBean<ApplicationBean> responseBean = new ResponseBean<>();

		responseBean.setData(applicationBean);
		responseBean.setMsg("Application Status Updated Successfully!!");
		responseBean.setStatus(200);

		return responseBean;
	}
}
