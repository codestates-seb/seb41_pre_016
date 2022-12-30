package com.stack.stackoverflow.tag.mapper;

import com.stack.stackoverflow.question.dto.QuestionResponseDto;
import com.stack.stackoverflow.question.entity.Question;
import com.stack.stackoverflow.tag.dto.TagResponseDto;
import com.stack.stackoverflow.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    List<TagResponseDto> tagToTagsResponseDto(List<Tag> tagList);
}
