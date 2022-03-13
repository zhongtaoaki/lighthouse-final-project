package com.example.login.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.login.models.Blog;
import com.example.login.models.User;
import com.example.login.services.BlogService;
import com.example.login.services.UserService;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;

	@GetMapping("/blog")
	public String blog(@RequestParam("user_id") Long userId, Map<String, Object> map) { // 作为参数传进来

		map.put("username", userService.findById(userId).get().getUsername()); // 将前端需要的参数放进去
		List<Blog> blogs = blogService.findBlogsByUserId(userId);
		map.put("blogs", blogs);
		return "blog";
	}

	@GetMapping("/editor")
	public String editor(@RequestParam("username") String username, Map<String, Object> map) { // 作为参数传进来
		map.put("username", username);
		return "editor";
	}

	@PostMapping("/editor")
	public ModelAndView addBlog(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("username") String username, @RequestParam("image") String image) {
		User user = userService.findByUsername(username);
		blogService.addBlog(title, content, user, image);
		ModelAndView mv = new ModelAndView("redirect:/blog");
		mv.addObject("user_id", user.getId());
		return mv;

	}

	@GetMapping("/deleteBlog")
	public ModelAndView deleteBlog(@RequestParam("username") String username, @RequestParam("id") Long id) {
		User user = userService.findByUsername(username);
		blogService.deleteBlog(id);
		ModelAndView mv = new ModelAndView("redirect:/blog");
		mv.addObject("user_id", user.getId());
		return mv;
	}

	@GetMapping("/showUpdate")
	public ModelAndView showUpdate(@RequestParam("username") String username, @RequestParam("id") Long id) {
		User user = userService.findByUsername(username);
		Blog blog = blogService.findBlogById(id);
		ModelAndView mv = new ModelAndView("update");
		mv.addObject("user_id", user.getId());
		mv.addObject("username", username);
		mv.addObject("blog", blog);
		return mv;
	}

	@PostMapping("/update")
	public String update(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("username") String username,@RequestParam("image") String image,@RequestParam("blog_id") Long id, Map<String, Object> map) {  
		
		Blog blog = blogService .findBlogById(id);
			blog.setContent(content);
			blog.setImage(image);
			blog.setTitle(title);
			blogService.updateBlog(blog);		
		User user = userService.findByUsername(username);
		map.put("username",username); // 
		List<Blog> blogs = blogService.findBlogsByUserId(user.getId());
		map.put("blogs", blogs);
		return "blog";
	}
}