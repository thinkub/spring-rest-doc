package com.ming.doc.project.entity;

import com.ming.doc.user.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 21..
 */

@Entity
@Getter
@Table(name = "project")
@NoArgsConstructor
public class ProjectEntity {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    @Column(name = "project_name", nullable = false, length = 30)
    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private UserEntity user;
}
