package com.stack.stackoverflow.user.dto.page;


import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;


@Getter
public class UsersPageResponseDto<T> {
    private List<T> users;
    private PageInfo pageInfo;

    public UsersPageResponseDto(List<T> users, Page page) {
        this.users = users;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
