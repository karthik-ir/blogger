/**
 * 
 */
package com.typeset.blogger;

import java.util.List;

/**
 * @author karthik
 *
 */
public class ParagraphResponse {

	private Long id;
	private Long data;
	private List<CommentResponse> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}

	public List<CommentResponse> getComments() {
		return comments;
	}

	public void setComments(List<CommentResponse> comments) {
		this.comments = comments;
	}

}
