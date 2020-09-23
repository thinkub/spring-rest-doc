package com.ming.doc.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.doc.user.model.User;
import com.ming.doc.user.service.UserService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 9. 22..
 */

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(UserController.class)
@AutoConfigureRestDocs
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findUser() throws Exception {
        // given
        given(userService.findUserByUserSeq(1L)).willReturn(User.of(1L, "thinkub", "Ming"));

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/user/{userSeq}", 1L)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(
                        "findUser",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("userSeq").description("사용자 시퀀스")),
                        responseFields(
                                fieldWithPath("userSeq").type(Long.class).description("사용자 시퀀스"),
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름")
                        )
                ));
    }

    @Test
    void createUser() throws Exception {
        // given
        User.Create create = User.Create.of("thinkub", "Ming");
        given(userService.createUser(any())).willReturn(User.of(1L, "thinkub", "Ming"));

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/user")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(create)))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(
                        "createUser",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디").optional(),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름").optional()
                        ),
                        responseFields(
                                fieldWithPath("userSeq").type(Long.class).description("사용자 시퀀스"),
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름")
                        )
                ));
    }

    @Test
    void modifyUser() throws Exception {
        // given
        User.Modify modify = User.Modify.of("thinkub-new", "Ming-new");
        User user = User.of(1L, "thinkub-new", "Ming-new");
        given(userService.modifyUser(eq(1L), any())).willReturn(user);

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.put("/user/{userSeq}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modify)))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(
                        "modifyUser",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("userSeq").description("사용자 시퀀스")),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름")
                        ),
                        responseFields(
                                fieldWithPath("userSeq").type(JsonFieldType.NUMBER).description("사용자 시퀀스"),
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("userName").type(JsonFieldType.STRING).description("이름")
                        )
                ));

    }

    @Test
    void deleteUser() throws Exception {
        // given
        doNothing().when(userService).deleteUser(1L);

        // when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/user/{userSeq}", 1L)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print());

        // then
        resultActions.andExpect(status().isOk())
                .andDo(document(
                        "deleteUser",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("userSeq").description("사용자 시퀀스"))
                ));
    }
}