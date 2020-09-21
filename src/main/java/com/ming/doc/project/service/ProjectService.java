package com.ming.doc.project.service;

import com.ming.doc.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;


}
