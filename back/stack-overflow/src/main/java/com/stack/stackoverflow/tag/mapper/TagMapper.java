package com.stack.stackoverflow.tag.mapper;

import com.stack.stackoverflow.tag.dto.TagResponseDto;
import com.stack.stackoverflow.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    List<TagResponseDto> tagToTagResponseDto(List<Tag> tagList);

//    default List<TagResponseDto> tagToTagResponseDto(List<Tag> tagList) {
//        List<TagResponseDto> tagResponseDtos = new TagResponseDto.OnlyTags(tagList);
//
//        return tagResponseDtos;
//    }
}
