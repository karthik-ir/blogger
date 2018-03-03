/**
 * 
 */
package com.typeset.blogger.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.typeset.blogger.BlogRequest;
import com.typeset.blogger.CommentRequest;
import com.typeset.blogger.NotFoundException;
import com.typeset.blogger.model.Blog;
import com.typeset.blogger.model.Comment;
import com.typeset.blogger.model.Paragraph;
import com.typeset.blogger.repository.BlogRepository;
import com.typeset.blogger.repository.CommentRepository;
import com.typeset.blogger.repository.ParagraphRepository;

/**
 * @author karthik
 *
 */
@Component
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	ParagraphRepository paragraphRepository;

	@Override
	public Blog createBlog(BlogRequest request) {

		Blog blog = new Blog(request.getTitle());
		Blog result = blogRepository.save(blog);

		String blogText = request.getParagraphs();
		if (blogText != null) {
			String[] paragraphs = blogText.split("\\r?\\n\\r?\\n");
			List<Paragraph> paras = Arrays.asList(paragraphs).stream().map(para -> {
				return new Paragraph(para, result, null);
			}).collect(Collectors.toList());

			paragraphRepository.save(paras);
		}
		return result;
	}

	@Override
	public Comment createComment(CommentRequest request) throws NotFoundException {
		Paragraph paragraph = paragraphRepository.findOne(request.getParagraphId());
		if (paragraph == null) {
			throw new NotFoundException("Invalid Paragraph");
		}
		Comment comment = new Comment(request.getComment(), paragraph);
		Comment result = commentRepository.save(comment);
		return result;
	}

	@Override
	public Page<Blog> getAllBlogs(Pageable pageable) {

		return blogRepository.findAll(pageable);
	}

}
