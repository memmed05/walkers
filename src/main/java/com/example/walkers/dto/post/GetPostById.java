package com.example.walkers.dto.post;

import com.example.walkers.dto.comment.GetCommentsOfPostByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPostById {

    private String postUrl;
    private String description;
    private List<GetCommentsOfPostByIdResponse> comments;
}
