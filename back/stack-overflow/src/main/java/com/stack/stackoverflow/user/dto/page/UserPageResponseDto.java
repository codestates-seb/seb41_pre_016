package com.stack.stackoverflow.user.dto.page;

import com.stack.stackoverflow.question.dto.page.PageInfo;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class UserPageResponseDto<T> {
    private List<T> users;
    private PageInfo pageInfo;

    public UserPageResponseDto(List<T> users, Page page){
        this.users = users;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
