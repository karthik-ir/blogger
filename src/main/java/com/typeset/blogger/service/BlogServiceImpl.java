/**
 * 
 */
package com.typeset.blogger.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.typeset.blogger.BlogRequest;
import com.typeset.blogger.CommentRequest;
import com.typeset.blogger.NotFoundException;
import com.typeset.blogger.api.model.BlogResponse;
import com.typeset.blogger.api.model.CommentResponse;
import com.typeset.blogger.api.model.ParagraphResponse;
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
	public BlogResponse createBlog(BlogRequest request) {

		Blog blog = new Blog(request.getTitle());
		Blog result = blogRepository.save(blog);
		List<Paragraph> savedParagraphs = new ArrayList<>();
		String blogText = request.getParagraphs();
		if (blogText != null) {
			String[] paragraphs = blogText.split("\\r?\\n\\r?\\n");
			List<Paragraph> paras = Arrays.asList(paragraphs).stream().map(para -> {
				return new Paragraph(para, result, null);
			}).collect(Collectors.toList());

			savedParagraphs = paragraphRepository.save(paras);
		}

		// Build response
		BlogResponse br = new BlogResponse();
		br.setId(result.getId());
		br.setTitle(request.getTitle());
		br.setParagraphs(savedParagraphs.stream().map(x -> {
			return new ParagraphResponse(x.getId(), x.getData());
		}).collect(Collectors.toList()));

		return br;
	}

	@Override
	public CommentResponse createComment(CommentRequest request) throws NotFoundException {
		Paragraph paragraph = paragraphRepository.findOne(request.getParagraphId());
		if (paragraph == null) {
			throw new NotFoundException("Invalid Paragraph");
		}
		Comment comment = new Comment(request.getComment(), paragraph);
		Comment result = commentRepository.save(comment);

		//Build Response
		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setId(result.getId());
		commentResponse.setData(request.getComment());
		commentResponse.setDate(result.getDate());
		commentResponse.setParagraphId(request.getParagraphId());
		return commentResponse;
	}

	@Override
	public BlogResponse getAllBlogs(Pageable pageable) {

		return null;
	}

	@Override
	public BlogResponse getBlogById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
