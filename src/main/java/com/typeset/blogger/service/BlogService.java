/**
 * 
 */
package com.typeset.blogger.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.typeset.blogger.BlogRequest;
import com.typeset.blogger.CommentRequest;
import com.typeset.blogger.NotFoundException;
import com.typeset.blogger.api.model.BlogResponse;
import com.typeset.blogger.api.model.CommentResponse;
import com.typeset.blogger.api.model.PagedBlogResponse;

/**
 * @author karthik
 *
 */
@Service
public interface BlogService {

	public BlogResponse createBlog(BlogRequest request);

	public CommentResponse createComment(CommentRequest request) throws NotFoundException;

	// No comments
	public PagedBlogResponse getAllBlogs(Pageable pageable);

	// With comments
	public BlogResponse getBlogById(Long id) throws NotFoundException;
}
