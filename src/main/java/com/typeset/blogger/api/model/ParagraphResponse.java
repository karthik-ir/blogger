/**
 * 
 */
package com.typeset.blogger.api.model;

import java.util.List;

/**
 * @author karthik
 *
 */
public class ParagraphResponse {

	private Long id;
	private String data;
	private List<CommentResponse> comments;

	public ParagraphResponse() {
		// TODO Auto-generated constructor stub
	}

	public ParagraphResponse(Long id, String data) {
		super();
		this.id = id;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<CommentResponse> getComments() {
		return comments;
	}

	public void setComments(List<CommentResponse> comments) {
		this.comments = comments;
	}

}
