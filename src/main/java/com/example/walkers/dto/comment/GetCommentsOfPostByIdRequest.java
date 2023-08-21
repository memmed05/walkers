package com.example.walkers.dto.comment;

import java.util.UUID;

public record GetCommentsOfPostByIdRequest(
        UUID id,
        UUID postId) {
}
