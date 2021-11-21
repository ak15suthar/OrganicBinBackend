package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.BlogBean;
import com.bean.ResponseBean;
import com.dao.BlogDao;

@CrossOrigin
@RestController
public class BlogController {

	@Autowired
	BlogDao blogDao;

	@PostMapping("addBlog")
	public ResponseBean<BlogBean> addBlog(@RequestBody BlogBean blogBean) {

		ResponseBean<BlogBean> responseBean = new ResponseBean<>();

		blogDao.addBlog(blogBean);

		responseBean.setData(blogBean);
		responseBean.setMsg("Blog Added..!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@GetMapping("listBlog")
	public ResponseBean<List<BlogBean>> listBlog() {
		ResponseBean<List<BlogBean>> responseBean = new ResponseBean<>();

		List<BlogBean> bean = null;
		bean = blogDao.listBlog(bean);

		responseBean.setData(bean);
		responseBean.setMsg("Blog List..!!");
		responseBean.setStatus(201);

		return responseBean;
	}

	@PutMapping("updateBlog")
	public ResponseBean<BlogBean> updateBlog(@RequestBody BlogBean blogBean) {

		ResponseBean<BlogBean> responseBean = new ResponseBean<>();

		blogDao.updateBlog(blogBean);

		responseBean.setData(blogBean);
		responseBean.setMsg("Blog Updated..!!");
		responseBean.setStatus(200);

		return responseBean;
	}

	@DeleteMapping("deleteBlog/{blogId}")
	public ResponseBean<BlogBean> deleteBlog(@PathVariable("blogId") int blogId) {
		blogDao.deleteRole(blogId);

		ResponseBean<BlogBean> responseBean = new ResponseBean<>();
		responseBean.setMsg("Blog Deleted..!!");
		responseBean.setStatus(200);
		
		return responseBean;
	}
}
