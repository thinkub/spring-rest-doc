package com.ming.doc.project.service;

import com.ming.doc.project.entity.ProjectEntity;
import com.ming.doc.project.model.Project;
import com.ming.doc.project.repository.ProjectRepository;
import com.ming.doc.user.entity.UserEntity;
import com.ming.doc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createProject(Project project) {
        Optional<UserEntity> userOptional = userRepository.findById(project.getRegisterUserSeq());
        if (!userOptional.isPresent()) {
            return null;
        }
        UserEntity userEntity = userOptional.get();
        ProjectEntity projectEntity = ProjectEntity.createProject(project.getProjectName(), userEntity);
        return projectRepository.save(projectEntity).getProjectId();
    }

    @Transactional
    public void deleteProject(Long projectId) {
        Optional<ProjectEntity> projectEntity = projectRepository.findById(projectId);
        projectEntity.ifPresent(projectRepository::delete);
    }

    public Project findProjectByProjectId(Long projectId) {
        Optional<ProjectEntity> projectEntity = projectRepository.findById(projectId);
        return projectEntity.map(Project::ofEntity).orElse(null);
    }
}
