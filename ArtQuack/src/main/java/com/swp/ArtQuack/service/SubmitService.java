package com.swp.ArtQuack.service;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.view.response.ChapterResponse;
import com.swp.ArtQuack.view.response.CourseResponse;
import com.swp.ArtQuack.view.response.ItemResponse;
import com.swp.ArtQuack.view.response.SubmitListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmitService {

    @Autowired
    CourseRepository courseRepository;

    public SubmitListResponse getSubmitList(int instructorID){
        SubmitListResponse submitListResponse = new SubmitListResponse();
        List<CourseResponse> courseResponses = new ArrayList<>();
        submitListResponse.setCourses(courseResponses);
        List<Course> courses = courseRepository.findByInstructorInstructorID(instructorID);
        for(Course course: courses){
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setCourseName(course.getName());
            courseResponse.setCourseID(course.getCourseID());
            List<ChapterResponse> chapterResponses = new ArrayList<>();
            courseResponse.setChapters(chapterResponses);
            courseResponses.add(courseResponse);
            for(Chapter chapter: course.getChaptersList()){
                ChapterResponse chapterResponse = new ChapterResponse();
                chapterResponse.setChapterID(chapter.getChapterID());
                chapterResponse.setChapterName(chapter.getChapterName());
                List<ItemResponse> itemResponses = new ArrayList<>();
                chapterResponse.setItems(itemResponses);
                if(chapter.getItemsList().size() > 0){
                    chapterResponses.add(chapterResponse);
                    for(Item item: chapter.getItemsList()){
                        ItemResponse itemResponse = new ItemResponse();
                        itemResponse.setItemID(item.getItemID());
                        itemResponse.setItemName(item.getItemName());
                        itemResponses.add(itemResponse);
                    }
                }
            }
        }
        return submitListResponse;
    }

}
