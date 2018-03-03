/**
 * 
 */
package com.typeset.blogger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.typeset.blogger.model.Paragraph;

/**
 * @author karthik
 *
 */
@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {
	List<Paragraph> findByBlogId(Long id);
}
