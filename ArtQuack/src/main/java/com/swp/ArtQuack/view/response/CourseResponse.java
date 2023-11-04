package com.swp.ArtQuack.view.response;

import lombok.Data;

import java.util.List;
@Data
public class CourseResponse {
    int courseID;
    String courseName;
    List<ChapterResponse> chapters;
}
