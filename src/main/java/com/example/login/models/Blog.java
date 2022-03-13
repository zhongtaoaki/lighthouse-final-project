package com.example.login.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity // 注释以下类会被用来创建实例
@Table(name = "blogs") // 告诉Spring这个类是blogs表的映射

public class Blog {

	public Blog(@Size(min = 1, max = 255) String title, String content, User user,String image) {
		super();
		this.title = title;
		this.content = content;
		this.image = image;
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Blog(@Size(min = 1, max = 255) String title, String content, User user) {
		this(title, content, user,null);
	}

	public Blog() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // 自动增加生成
	private Long id;

	@Size(min = 1, max = 255)
	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "created_at", insertable = false, updatable = false)
	private Date createdAt;

	@Column(name = "image")
	private String image;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
