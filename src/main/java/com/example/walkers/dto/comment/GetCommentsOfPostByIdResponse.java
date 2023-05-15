package com.example.walkers.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCommentsOfPostByIdResponse {

    private String comment;
    private Long dayAgo;
}
