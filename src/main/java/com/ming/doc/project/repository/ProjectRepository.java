package com.ming.doc.project.repository;

import com.ming.doc.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
