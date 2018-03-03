/**
 * 
 */
package com.typeset.blogger.api.model;

import java.util.Date;

/**
 * @author karthik
 *
 */
public class CommentResponse {

	private Long id;
	private String data;
	private Date date;
	private Long paragraphId;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getParagraphId() {
		return paragraphId;
	}

	public void setParagraphId(Long paragraphId) {
		this.paragraphId = paragraphId;
	}

}
