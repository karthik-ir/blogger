/**
 * 
 */
package com.typeset.blogger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.typeset.blogger.model.Comment;

/**
 * @author karthik
 *
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
