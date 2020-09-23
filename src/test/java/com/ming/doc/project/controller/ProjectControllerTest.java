package com.ming.doc.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.doc.project.model.Project;
import com.ming.doc.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.ming.doc.ApiDocumentUtils.getDocumentRequest;
import static com.ming.doc.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 23..
 */

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(ProjectController.class)
@AutoConfigureRestDocs
class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjectService projectService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findProject() throws Exception {
        // given
        Project project = Project.of(1L, "프로젝트", 1L, "thinkub");
        given(projectService.findProjectByProjectId(1L)).willReturn(project);

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/project/{projectId}", 1L)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(
                        "findProject",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("projectId").description("프로젝트 아이디")),
                        responseFields(
                                fieldWithPath("projectId").type(JsonFieldType.NUMBER).description("프로젝트 아이디"),
                                fieldWithPath("projectName").type(JsonFieldType.STRING).description("프로젝트 명칭"),
                                fieldWithPath("registerUserSeq").type(JsonFieldType.NUMBER).description("프로젝트 등록 사용자 시퀀스"),
                                fieldWithPath("registerUserId").type(JsonFieldType.STRING).description("프로젝트 등록 사용자 아이디")
                        )
                ));
    }

    @Test
    void createProject() throws Exception {
        // given
        Project.Create create = Project.Create.of("프로젝트", 1L);
        given(projectService.createProject(any())).willReturn(Project.of(1L, "프로젝트", 1L, "thinkub"));

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/project")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(create)))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(
                        "createProject",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("projectName").type(JsonFieldType.STRING).description("프로젝트 명칭"),
                                fieldWithPath("registerUserSeq").type(JsonFieldType.NUMBER).description("프로젝트 등록 사용자 시퀀스")
                        ),
                        responseFields(
                                fieldWithPath("projectId").type(JsonFieldType.NUMBER).description("프로젝트 아이디"),
                                fieldWithPath("projectName").type(JsonFieldType.STRING).description("프로젝트 명칭"),
                                fieldWithPath("registerUserSeq").type(JsonFieldType.NUMBER).description("프로젝트 등록 사용자 시퀀스"),
                                fieldWithPath("registerUserId").type(JsonFieldType.STRING).description("프로젝트 등록 사용자 아이디")
                        )
                ));

    }

    @Test
    void deleteProject() throws Exception {
        // given
        doNothing().when(projectService).deleteProject(1L);

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/project/{projectId}", 1L)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(
                        "deleteProject",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("projectId").description("프로젝트 아이디"))
                ));
    }
}