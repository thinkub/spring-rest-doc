package com.ming.doc.project.model;

import com.ming.doc.project.entity.ProjectEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 22..
 */

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Project {
    private Long projectId;
    private String projectName;
    private Long registerUserSeq;
    private String registerUserId;

    public static Project ofEntity(ProjectEntity entity) {
        return Project.builder()
                .projectId(entity.getProjectId())
                .projectName(entity.getProjectName())
                .registerUserSeq(entity.getUser().getUserSeq())
                .registerUserId(entity.getUser().getUserId())
                .build();
    }

    public static Project of(Long projectId, String projectName, Long registerUserSeq, String registerUserId) {
        return Project.builder()
                .projectId(projectId)
                .projectName(projectName)
                .registerUserSeq(registerUserSeq)
                .registerUserId(registerUserId)
                .build();
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    public static class Create {
        private String projectName;
        private Long registerUserSeq;

        public static Create of(String projectName, Long registerUserSeq) {
            return Create.builder()
                    .projectName(projectName)
                    .registerUserSeq(registerUserSeq)
                    .build();
        }
    }
}
