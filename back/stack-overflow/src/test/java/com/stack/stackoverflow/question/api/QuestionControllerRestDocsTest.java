package com.stack.stackoverflow.question.api;


import com.google.gson.Gson;
import com.stack.stackoverflow.question.controller.QuestionController;
import com.stack.stackoverflow.question.dto.QuestionRequestDto;
import com.stack.stackoverflow.question.dto.QuestionResponseDto;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.question.mapper.QuestionMapper;
import com.stack.stackoverflow.question.service.QuestionService;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QuestionControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper questionMapper;

    @Autowired
    private Gson gson;

    @Test
    public void postQuestionTest() throws Exception {
//        List<String> list = new ArrayList<>();
        List<String> list = Arrays.asList("difficult", "java");
//        list.add("difficult");
//        list.add("java");

        // given
        QuestionRequestDto.Post postDto = new QuestionRequestDto.Post(
                "How to make project?",
                "I don't know how to make project...",
//                Arrays.asList("difficult", "java"));
                list);
        String content = gson.toJson(postDto);

        QuestionResponseDto.NoAnswer responseDto = new QuestionResponseDto.NoAnswer(
                1L,
                "How to make project?",
                "I don't know how to make project...",
//                Arrays.asList("difficult", "java"),
                0,
                0,
                0,
                list,
                1L,
                "Chris",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        given(questionMapper.qusetionPostDtoToQuestion(Mockito.any(QuestionRequestDto.Post.class))).willReturn(new Question());

        Question mockResultQuestion = new Question();
        mockResultQuestion.setQuestionId(1L);
        given(questionService.createQuestion(Mockito.any(Question.class), Mockito.anyList(), 1L)).willReturn(mockResultQuestion);

        given(questionMapper.questionToQuestionNoAnswerDto(Mockito.any(Question.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/question")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );


//        System.out.println("jjjjjjjjjjjjjjjjjjjj" + postDto.getTags() + "jjjjjjjjjjjjjjj");

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(postDto.getTitle()))
                .andExpect(jsonPath("$.content").value(postDto.getContent()))
                .andExpect(jsonPath("$.tags[0:]").value(postDto.getTags()))
                .andDo(document(
                        "post-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그들")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("태그들"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간")
                                )
                        )
                ));
    }
}
