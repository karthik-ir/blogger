/**
 * 
 */
package com.typeset.blogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.typeset.blogger.model.Blog;
import com.typeset.blogger.model.Comment;
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
	public ResponseEntity<Blog> postBlog(BlogRequest request) {
		if (!validateBlog(request)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Blog createBlog = blogService.createBlog(request);
		return new ResponseEntity<Blog>(createBlog, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Comment> postComment(CommentRequest request) throws NotFoundException {
		if (!validateComment(request)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Comment createBlog = blogService.createComment(request);
		return new ResponseEntity<Comment>(createBlog, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/blog", method = RequestMethod.GET, produces = "application/json")
	public Page<Blog> getBlogs(Pageable pageable) {
		return blogService.getAllBlogs(pageable);

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
