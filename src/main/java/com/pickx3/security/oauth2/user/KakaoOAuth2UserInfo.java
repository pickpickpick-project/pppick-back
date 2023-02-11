package com.pickx3.security.oauth2.user;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    public Map<String, Object> kakaoProperties = (Map<String, Object>) attributes.get("properties");
    public Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
    public Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) kakaoProfile.get("nickname");
    }

    @Override
    public String getEmail() {
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) kakaoProperties.get("profile_image");
    }

    @Override
    public String getPhone() {
//        return (String) kakaoProperties.get("phone_number");
        return null;
    }
}

