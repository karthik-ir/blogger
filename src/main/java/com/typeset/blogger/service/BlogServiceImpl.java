/**
 * 
 */
package com.typeset.blogger.service;

import java.util.ArrayList;
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
import com.typeset.blogger.api.model.BlogResponse;
import com.typeset.blogger.api.model.CommentResponse;
import com.typeset.blogger.api.model.PagedBlogResponse;
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

		// Build Response
		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setId(result.getId());
		commentResponse.setData(request.getComment());
		commentResponse.setDate(result.getDate());
		commentResponse.setParagraphId(request.getParagraphId());
		return commentResponse;
	}

	@Override
	public PagedBlogResponse getAllBlogs(Pageable pageable) {

		Page<Blog> blogs = blogRepository.findAll(pageable);

		// Build response
		PagedBlogResponse pagedBlogs = new PagedBlogResponse();
		pagedBlogs.setBlogs(blogs.getContent().stream().map(x -> {
			BlogResponse br = new BlogResponse();
			br.setId(x.getId());
			br.setTitle(x.getTitle());
			return br;
		}).collect(Collectors.toList()));
		pagedBlogs.setTotalElements(blogs.getTotalElements());
		pagedBlogs.setTotalPages(blogs.getTotalPages());

		return pagedBlogs;
	}

	@Override
	public BlogResponse getBlogById(Long id) throws NotFoundException {
		Blog result = blogRepository.findById(id);
		if (result == null) {
			throw new NotFoundException("Blog with id : " + id + " not found");
		}
		BlogResponse response = new BlogResponse();
		response.setId(result.getId());
		response.setTitle(result.getTitle());
		response.setCreatedDate(result.getDateTime());
		List<ParagraphResponse> paragraphs = result.getParagraphs().stream().map(x -> {
			ParagraphResponse paragraphResponse = new ParagraphResponse();
			paragraphResponse.setData(x.getData());
			paragraphResponse.setId(x.getId());
			List<CommentResponse> comments = x.getComments().stream().map(y -> {
				CommentResponse cr = new CommentResponse();
				cr.setData(y.getData());
				cr.setId(y.getId());
				cr.setDate(y.getDate());
				return cr;
			}).collect(Collectors.toList());
			paragraphResponse.setComments(comments);
			return paragraphResponse;
		}).collect(Collectors.toList());
		response.setParagraphs(paragraphs);
		return response;
	}

}
