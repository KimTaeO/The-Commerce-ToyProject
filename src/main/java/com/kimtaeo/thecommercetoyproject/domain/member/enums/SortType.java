package com.kimtaeo.thecommercetoyproject.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {
    REGISTERED_DATE("createdAt"),
    NAME("name");

    private final String str;
}
