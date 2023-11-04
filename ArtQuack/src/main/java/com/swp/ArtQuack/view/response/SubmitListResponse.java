package com.swp.ArtQuack.view.response;

import lombok.Data;

import java.util.List;

@Data
public class SubmitListResponse {
    List<CourseResponse> courses;
}
