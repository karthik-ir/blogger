/**
 * 
 */
package com.typeset.blogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.typeset.blogger.model.Blog;
import com.typeset.blogger.model.Paragraph;
import com.typeset.blogger.repository.BlogRepository;
import com.typeset.blogger.repository.CommentRepository;
import com.typeset.blogger.repository.ParagraphRepository;
import com.typeset.blogger.service.BlogService;

/**
 * @author karthik
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogserviceTest {

	@Autowired
	BlogService blogService;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	ParagraphRepository paragraphRepository;

	@Autowired
	CommentRepository commentRepository;

	/**
	 * Test method for
	 * {@link com.typeset.blogger.service.BlogServiceImpl#createBlog(com.typeset.blogger.BlogRequest)}.
	 */
	@Test
	public void testCreateBlogWithTwoParagraph() {
		BlogRequest blogRequest = new BlogRequest();
		blogRequest.setTitle("First");
		blogRequest.setParagraphs("hello\n\n world");
		Blog result = blogService.createBlog(blogRequest);

		Blog getOne = blogRepository.findOne(result.getId());
		assertEquals(getOne.getParagraphs().size(), 2);
		assertTrue(getOne.getTitle().equals("First"));
	}

	@Test
	public void testCreateBlogWithOneParagraph() {
		BlogRequest blogRequest = new BlogRequest();
		blogRequest.setTitle("First");
		blogRequest.setParagraphs("hello\nworld");
		Blog result = blogService.createBlog(blogRequest);

		Blog getOne = blogRepository.findOne(result.getId());
		assertEquals(getOne.getParagraphs().size(), 1);
		assertTrue(getOne.getTitle().equals("First"));
	}

	@Test
	public void testCreateBlogWithNoParagraph() {
		BlogRequest blogRequest = new BlogRequest();
		blogRequest.setTitle("First");
		Blog result = blogService.createBlog(blogRequest);

		Blog getOne = blogRepository.findOne(result.getId());
		assertEquals(getOne.getParagraphs().size(), 0);
		assertTrue(getOne.getTitle().equals("First"));
	}

	/**
	 * Test method for
	 * {@link com.typeset.blogger.service.BlogServiceImpl#createComment(com.typeset.blogger.CommentRequest)}.
	 * 
	 * @throws NotFoundException
	 */
	@Test
	public void testCreateComment() throws NotFoundException {
		BlogRequest blogRequest = new BlogRequest();
		blogRequest.setTitle("First");
		blogRequest.setParagraphs("hello\n\n world");
		Blog result = blogService.createBlog(blogRequest);

		Blog getOne = blogRepository.findOne(result.getId());
		List<Paragraph> findAll = paragraphRepository.findAll();
		assertEquals(getOne.getParagraphs().size(), 2);
		assertTrue(getOne.getTitle().equals("First"));

		CommentRequest request = new CommentRequest();
		request.setComment("MYFIRST COMMENT");
		request.setParagraphId(findAll.get(0).getId());
		blogService.createComment(request);
		assertEquals(paragraphRepository.findOne(findAll.get(0).getId()).getComments().size(), 1);
		request.setComment("MYSECOND COMMENT");
		request.setParagraphId(findAll.get(1).getId());
		blogService.createComment(request);
		assertEquals(paragraphRepository.findOne(findAll.get(1).getId()).getComments().size(), 1);

		request.setComment("MYTHIRDCOMMENT");
		request.setParagraphId(findAll.get(0).getId());
		blogService.createComment(request);
		assertEquals(paragraphRepository.findOne(findAll.get(0).getId()).getComments().size(), 2);
	}

	@Test(expected = NotFoundException.class)
	public void testCreateCommentWithBadParaGraphID() throws NotFoundException {
		CommentRequest request = new CommentRequest();
		request.setComment("MYFIRST COMMENT");
		request.setParagraphId(1322122L);
		blogService.createComment(request);
	}
}
