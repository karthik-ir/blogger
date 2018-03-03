/**
 * 
 */
package com.typeset.blogger.api.model;

import java.util.List;

/**
 * @author karthik
 *
 */
public class PagedBlogResponse {

	List<BlogResponse> blogs;
	Long totalElements;
	int totalPages;

	public List<BlogResponse> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<BlogResponse> blogs) {
		this.blogs = blogs;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
