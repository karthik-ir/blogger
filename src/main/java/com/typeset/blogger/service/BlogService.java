/**
 * 
 */
package com.typeset.blogger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.typeset.blogger.BlogRequest;
import com.typeset.blogger.BlogResponse;
import com.typeset.blogger.CommentRequest;
import com.typeset.blogger.NotFoundException;
import com.typeset.blogger.model.Blog;
import com.typeset.blogger.model.Comment;

/**
 * @author karthik
 *
 */
@Service
public interface BlogService {

	public Blog createBlog(BlogRequest request);

	public Comment createComment(CommentRequest request) throws NotFoundException;

	public Page<Blog> getAllBlogs(Pageable pageable);
}
