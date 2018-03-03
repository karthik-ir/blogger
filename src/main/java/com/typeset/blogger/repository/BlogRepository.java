/**
 * 
 */
package com.typeset.blogger.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.typeset.blogger.model.Blog;

/**
 * @author karthik
 *
 */
@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {

//	@Query("select b from Blog b fetch b.paragraphs.comments p where b.id=?1 ")
	Blog findById(Long id);
}
