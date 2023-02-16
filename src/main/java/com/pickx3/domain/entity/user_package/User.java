package com.pickx3.domain.entity.user_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pickx3.domain.entity.portfolio_package.Portfolio;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "UserInfo")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userNum")
    private Long id;
    @Column(name="userName")
    private String name;

    @Column(name = "userId")
    private String email;
    @JsonIgnore
    @Column(name="userPwd")
    private String password;

    @Column(name = "userNick")
    private String nickName;

    @Column(name = "userPhone")
    private String phone;

    @Column(name = "userIntro")
    private String intro;

    @Column(name = "userImg")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole")
    private Role role;

    @Column(name = "userEmailVerified")
    private Boolean emailVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name="userSnsType")
    private AuthProvider provider;

    @Column(name = "userProviderId")
    private String providerId;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Portfolio> portfolio = new ArrayList<>();



    @Builder
    private User(long id, String name, String email, String imageUrl, Role role,
                 Boolean emailVerified, String password, AuthProvider provider,
                 String providerId, String nickName, String phone, String intro) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
        this.emailVerified = emailVerified;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.nickName = nickName;
        this.phone = phone;
        this.intro = intro;
    }

    public User update(String name, String imageUrl) {
        this.name = name;
//        this.phone = phone;
//        this.intro = userIntro;
//        this.nickName = userNick;
        this.imageUrl = imageUrl;
        return this;
    }





}
