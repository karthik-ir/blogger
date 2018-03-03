/**
 * 
 */
package com.typeset.blogger.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author karthik
 *
 */
@Entity
public class Paragraph {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String data;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blog_id", nullable = false)
	private Blog blog;

	@OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	public Paragraph() {
		// TODO Auto-generated constructor stub
	}

	public Paragraph(String data, Blog blog, List<Comment> comments) {
		super();
		this.data = data;
		this.blog = blog;
		this.comments = comments;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

}
