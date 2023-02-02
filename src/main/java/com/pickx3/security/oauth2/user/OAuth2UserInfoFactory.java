package com.pickx3.security.oauth2.user;

import com.pickx3.domain.entity.user_package.AuthProvider;
import com.pickx3.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
            return new NaverOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
            return new KakaoOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException(registrationId + " 로그인은 지원하지 않습니다.");
        }
    }
}
