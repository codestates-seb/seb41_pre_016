package com.stack.stackoverflow.restdocs;

import com.google.gson.Gson;
import com.stack.stackoverflow.user.controller.UserController;
import com.stack.stackoverflow.user.dto.UserDto;
import com.stack.stackoverflow.user.entity.User;
import com.stack.stackoverflow.user.mapper.UserMapper;
import com.stack.stackoverflow.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.time.LocalDateTime;
import java.util.List;

import static com.stack.stackoverflow.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.stack.stackoverflow.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class UserControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void postUserTest() throws Exception{
        UserDto.Post post = new UserDto.Post("김코딩", "kimcoding@gmail.com", "1234aAbc!");
        String content = gson.toJson(post);

        UserDto.Response responseDto =
                new UserDto.Response(1L,
                        "김코딩",
                        "kimcoding@gmail.com",
                        "1234aAbc!",
                        LocalDateTime.now(),
                        LocalDateTime.now()
                        );

        given(mapper.userPostToUser(Mockito.any(UserDto.Post.class))).willReturn(new User());

        User mockResultUser = new User();
        mockResultUser.setUserId(1L);
        given(userService.createUser(Mockito.any(User.class))).willReturn(mockResultUser);

        given(mapper.userToUserResponseDto(Mockito.any(User.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        post("/user")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(post.getName()))
                .andExpect(jsonPath("$.email").value(post.getEmail()))
                .andExpect(jsonPath("$.password").value(post.getPassword()))
                .andDo(document("post-user",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 아이디"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간")
                        ))));
    }

    @Test
    public void patchUserTest() throws Exception{
        long userId = 1L;
        UserDto.Patch patch = new UserDto.Patch(userId, "김코딩", "1234aAbc!");
        String content = gson.toJson(patch);

        UserDto.Response responseDto =
                new UserDto.Response(1L,
                        "김코딩",
                        "kimcoding@gmail.com",
                        "1234aAbc!",
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );

        given(mapper.userPatchToUser(Mockito.any(UserDto.Patch.class))).willReturn(new User());

        given(userService.updateUser(Mockito.any(User.class))).willReturn(new User());

        given(mapper.userToUserResponseDto(Mockito.any(User.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        patch("/user/{user-id}", userId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(patch.getUserId()))
                .andExpect(jsonPath("$.name").value(patch.getName()))
                .andExpect(jsonPath("$.password").value(patch.getPassword()))
                .andDo(document("patch-user",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("user-id").description("회원 식별")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원 식별").ignored(),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름").optional(),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional()
                                )
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원 식별"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간").optional(),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간").optional()
                                )
                        )));
    }
}
