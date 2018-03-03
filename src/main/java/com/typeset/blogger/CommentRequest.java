/**
 * 
 */
package com.typeset.blogger;

import java.util.Date;

/**
 * @author karthik
 *
 */
public class CommentRequest {

	private Long paragraphId;
	private String comment;

	public Long getParagraphId() {
		return paragraphId;
	}

	public void setParagraphId(Long paragraphId) {
		this.paragraphId = paragraphId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
