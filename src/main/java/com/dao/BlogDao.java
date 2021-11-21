package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.BlogBean;

@Repository
public class BlogDao {

	@Autowired
	JdbcTemplate stmt;

	public void addBlog(BlogBean blogBean) {
		stmt.update("insert into blog(blogtitle,about) values(?,?)", blogBean.getBlogTitle(), blogBean.getAbout());

	}

	public List<BlogBean> listBlog(List<BlogBean> bean) {
		bean = stmt.query("select * from blog", BeanPropertyRowMapper.newInstance(BlogBean.class));
		return bean;
	}

	public void updateBlog(BlogBean blogBean) {
		stmt.update("update blog set blogtitle=?,about=? where blogid=?", blogBean.getBlogTitle(), blogBean.getAbout(),
				blogBean.getBlogId());

	}

	public void deleteRole(int blogId) {
		stmt.update("delete from blog where blogid = ?", blogId);

	}

}
