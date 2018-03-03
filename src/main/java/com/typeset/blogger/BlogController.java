/**
 * 
 */
package com.typeset.blogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.typeset.blogger.api.model.BlogResponse;
import com.typeset.blogger.api.model.CommentResponse;
import com.typeset.blogger.api.model.PagedBlogResponse;
import com.typeset.blogger.service.BlogService;

/**
 * @author karthik
 *
 */
@RestController
public class BlogController {

	@Autowired
	BlogService blogService;

	@RequestMapping(value = "/blog", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<BlogResponse> postBlog(@RequestBody BlogRequest request) {
		if (!validateBlog(request)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		BlogResponse createBlog = blogService.createBlog(request);
		return new ResponseEntity<BlogResponse>(createBlog, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CommentResponse> postComment(@RequestBody CommentRequest request) throws NotFoundException {
		if (!validateComment(request)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		CommentResponse createBlog = blogService.createComment(request);
		return new ResponseEntity<CommentResponse>(createBlog, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/blog", method = RequestMethod.GET, produces = "application/json")
	public PagedBlogResponse getBlogs(Pageable pageable) {
		return blogService.getAllBlogs(pageable);
	}

	@RequestMapping(value = "/blog/{id}", method = RequestMethod.GET, produces = "application/json")
	public BlogResponse getBlogById(@PathVariable(name = "id") Long id) throws NotFoundException {
		return blogService.getBlogById(id);
	}

	private boolean validateComment(CommentRequest request) {
		if (request.getParagraphId() == null || request.getComment() == null || request.getComment().isEmpty())
			return false;
		return true;
	}

	private boolean validateBlog(BlogRequest request) {
		if (request.getTitle() == null || request.getTitle().isEmpty())
			return false;
		return true;

	}
}
