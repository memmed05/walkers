package com.example.walkers.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProfileResponse {

    private String name;
    private String surname;
    private String patronymic;
    private String bio;
    private String ppUrl;
}
