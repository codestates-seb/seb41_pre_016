package com.stack.stackoverflow.user.dto;

import com.stack.stackoverflow.question.dto.page.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDto<T> {
    private List<T> users;
    private PageInfo pageInfo;

    public UserDto(List<T> users, Page page) {
        this.users = users;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

}
