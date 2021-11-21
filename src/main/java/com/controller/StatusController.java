package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.StatusBean;
import com.dao.StatusDao;

@CrossOrigin
@RestController
public class StatusController {

	@Autowired
	StatusDao statusDao;

	@PostMapping("addStatus")
	public ResponseBean<StatusBean> addStatus(@RequestBody StatusBean statusBean) {

		statusDao.addStatus(statusBean);
		
		ResponseBean<StatusBean> responseBean = new ResponseBean<>();

		responseBean.setData(statusBean);
		responseBean.setMsg("Role Inserted!!");
		responseBean.setStatus(200);

		return responseBean;

	}
}
