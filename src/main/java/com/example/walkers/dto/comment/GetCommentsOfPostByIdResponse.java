package com.example.walkers.dto.comment;

import com.example.walkers.model.Comment;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Builder
public record GetCommentsOfPostByIdResponse(
        String comment,
        Long dayAgo,
        String username
) {
    public static GetCommentsOfPostByIdResponse convertToGetResponse(Comment comment) {
        return new GetCommentsOfPostByIdResponse(comment.getComment(),
                ChronoUnit.DAYS.between(LocalDateTime.now(), comment.getCreatedAt()),
                comment.getUser().getUsername());
    }
}
