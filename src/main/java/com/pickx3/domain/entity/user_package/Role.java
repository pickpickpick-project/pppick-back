package com.pickx3.domain.entity.user_package;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반 사용자"),
    ADMIN("ROLE_ADMIN","관리자");

    public final String key;
    public final String title;

}
