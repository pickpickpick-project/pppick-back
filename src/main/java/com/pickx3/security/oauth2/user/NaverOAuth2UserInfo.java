package com.pickx3.security.oauth2.user;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    public Map<String, Object> response = (Map<String, Object>) attributes.get("response");
    @Override
    public String getId() {
        return (String) response.get("sub");
    }

    @Override
    public String getName() {
        return (String) response.get("name");
    }

    @Override
    public String getEmail() {
        return (String) response.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) response.get("profile_image");
    }

    @Override
    public String getPhone() {
        return (String) response.get("mobile");
    }
}

