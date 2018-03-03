/**
 * 
 */
package com.typeset.blogger;

import java.util.Date;
import java.util.List;

import com.typeset.blogger.model.Paragraph;

/**
 * @author karthik
 *
 */
public class BlogResponse {

	private Long id;
	private String title;
	private Date createdDate;
	private List<ParagraphResponse> paragraphs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<ParagraphResponse> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<ParagraphResponse> paragraphs) {
		this.paragraphs = paragraphs;
	}

}
