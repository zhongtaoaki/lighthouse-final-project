package com.example.login.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.models.Blog;
import com.example.login.models.User;
import com.example.login.repositories.BlogRepository;

@Service
public class BlogService {
	@Autowired // 自动实例化以下类
	private BlogRepository blogRepository;

	public List<Blog> findBlogsByUserId(Long userId) {
		return blogRepository.findByUserId(userId);
	}

	public Blog addBlog(String title, String content, User user,String image) {
		return blogRepository.save(new Blog(title, content, user, image));
	}
	public void deleteBlog(Long id) {		
		blogRepository.deleteById(id);
	}
	public void updateBlog(Blog blog) {
		blogRepository.save(blog);
	}
	public Blog findBlogById(Long id) {
		return blogRepository.findById(id).get();
	}

}
