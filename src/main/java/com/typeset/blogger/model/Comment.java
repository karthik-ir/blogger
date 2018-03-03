/**
 * 
 */
package com.typeset.blogger.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author karthik
 *
 */
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String data;
	private Date date;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paragraph_id")
	private Paragraph paragraph;

	public Comment(String data, Paragraph paragraph) {
		super();
		this.data = data;
		this.paragraph = paragraph;
		this.date = new Date();
	}

	public Comment() {
		// TODO Auto-generated constructor stub
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Paragraph getParagraph() {
		return paragraph;
	}

	public void setParagraph(Paragraph paragraph) {
		this.paragraph = paragraph;
	}

}
