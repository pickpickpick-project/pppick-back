package com.pickx3.service.post_package;

import com.pickx3.domain.dto.post_package.CommentCreateRequestDto;
import com.pickx3.domain.dto.post_package.CommentResponseDto;
import com.pickx3.domain.dto.post_package.CommentUpdateRequestDto;
import com.pickx3.domain.entity.post_package.Comment;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.post_package.CommentRepository;
import com.pickx3.domain.repository.post_package.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    /* CREATE */
    @Transactional
    public Long commentSave(CommentCreateRequestDto dto, Long postNum) {
        String commentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        dto.setPost(post);

        Comment comment = new Comment(
                dto.getUser(),
                dto.getPost(),
                dto.getCommentContent(),
                commentDate

        );

        Comment comment1 = commentRepository.save(comment);

        return comment1.getCommentNum();
    }

    /* UPDATE */
    @Transactional
    public Long commentUpdate(Long commentNum, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentNum)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        comment.update(requestDto.getCommentContent());

        return commentNum;
    }

    @Transactional
    public void delete(Long commentNum) {
        Comment comment = commentRepository.findById(commentNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto searchById(Long commentNum) {
        Comment comment = commentRepository.findById(commentNum).orElseThrow(()
                -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        return new CommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> searchByPost(Long postNum) {
        return commentRepository.findByPost_PostNum(postNum).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> searchAll() {
        return commentRepository.findAll().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

}