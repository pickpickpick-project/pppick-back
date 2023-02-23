package com.pickx3.service;

import com.pickx3.domain.entity.Follow;
import com.pickx3.domain.entity.FollowForm;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.FollowRepository;
import com.pickx3.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service @Transactional
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public Follow follow(FollowForm followForm) throws IllegalAccessException {

        User follower = userRepository.findById(followForm.getFollowerNum()).orElseThrow(() -> new IllegalAccessException(" 팔로워 유저 Id 없음 "));
        User following = userRepository.findById(followForm.getFollowingNum()).orElseThrow(() -> new IllegalAccessException(" 팔로우 유저 Id 없음"));

        Follow follow = Follow.builder()
                .followerNum(follower.getId())
                .followingNum(following.getId())
                .build();

        followRepository.save(follow);

        return follow;
    }

    public Follow followCancel(FollowForm followForm) throws IllegalAccessException {

        User follower = userRepository.findById(followForm.getFollowerNum()).orElseThrow(() -> new IllegalAccessException(" 팔로워 유저 Id 없음 "));
        User following = userRepository.findById(followForm.getFollowingNum()).orElseThrow(() -> new IllegalAccessException(" 팔로우 유저 Id 없음"));


        Follow follow = followRepository.findFollowByFollowerNumAndFollowingNum(follower.getId(), following.getId());

        followRepository.delete(follow);

        return follow;
    }

    public List<User> followingList(Long id) throws IllegalAccessException {

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalAccessException("user id x "));

        List<Follow> follow = followRepository.findByFollowerNum(id);

        // list add
        List<User> followList = new ArrayList<>();
        for(Follow f : follow){
            followList.add(userRepository.findById(f.getFollowingNum()).get());
        }


        return followList;
    }

    public List<User> followerList(Long id) throws IllegalAccessException {

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalAccessException("user id x "));

        List<Follow> follow = followRepository.findByFollowingNum(id);

        // list add
        List<User> followList = new ArrayList<>();
        for(Follow f : follow){
            followList.add(userRepository.findById(f.getFollowerNum()).get());
        }


        return followList;
    }
}
