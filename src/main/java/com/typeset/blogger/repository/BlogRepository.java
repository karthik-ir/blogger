/**
 * 
 */
package com.typeset.blogger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.typeset.blogger.model.Blog;

/**
 * @author karthik
 *
 */
@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {

	
}
