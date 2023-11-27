package com.swp.ArtQuack.service;

import com.swp.ArtQuack.entity.Chapter;
import com.swp.ArtQuack.entity.Course;
import com.swp.ArtQuack.entity.Enrollment;
import com.swp.ArtQuack.entity.Item;
import com.swp.ArtQuack.repository.CourseRepository;
import com.swp.ArtQuack.repository.EnrollmentRepository;
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
    
    @Autowired
    EnrollmentRepository enrollmentRepository;
    
    public SubmitListResponse getSubmitListOfLearner(int learnerID){
    	List<Enrollment> enrollments = enrollmentRepository.findByLearnerLearnerID(learnerID);
    	SubmitListResponse submitListResponse = new SubmitListResponse();
        List<CourseResponse> courseResponses = new ArrayList<>();
        submitListResponse.setCourses(courseResponses);
        List<Course> courses = enrollments.stream().map(item -> {
        	return item.getCourse();
        }).distinct().toList();
        for(Course course: courses){
            boolean checkCourse = false;
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setCourseName(course.getName());
            courseResponse.setCourseID(course.getCourseID());
            courseResponse.setStatus(course.isStatus());
            List<ChapterResponse> chapterResponses = new ArrayList<>();
            courseResponse.setChapters(chapterResponses);

            for(Chapter chapter: course.getChaptersList()){
                boolean check = false;
                ChapterResponse chapterResponse = new ChapterResponse();
                chapterResponse.setChapterID(chapter.getChapterID());
                chapterResponse.setChapterName(chapter.getChapterName());
                chapterResponse.setStatus(chapter.isStatus());
                List<ItemResponse> itemResponses = new ArrayList<>();
                chapterResponse.setItems(itemResponses);
                for(Item item: chapter.getItemsList()){
                    if(item.getItemName().toLowerCase().contains("peer") && item.isStatus() == true ){
                        check = true;
                        ItemResponse itemResponse = new ItemResponse();
                        itemResponse.setItemID(item.getItemID());
                        itemResponse.setItemName(item.getItemName());
                        itemResponse.setStatus(item.isStatus());
                        itemResponses.add(itemResponse);
                    }
                }

                if(check){
                    chapterResponses.add(chapterResponse);
                    checkCourse = true;
                }
            }
            if (checkCourse){
                courseResponses.add(courseResponse);
            }
        }
        return submitListResponse;
    }

    public SubmitListResponse getSubmitList(int instructorID){
        SubmitListResponse submitListResponse = new SubmitListResponse();
        List<CourseResponse> courseResponses = new ArrayList<>();
        submitListResponse.setCourses(courseResponses);
        List<Course> courses = courseRepository.findByInstructorInstructorID(instructorID);
        for(Course course: courses){
            boolean checkCourse = false;
            CourseResponse courseResponse = new CourseResponse();
            courseResponse.setCourseName(course.getName());
            courseResponse.setCourseID(course.getCourseID());
            courseResponse.setStatus(course.isStatus());
            List<ChapterResponse> chapterResponses = new ArrayList<>();
            courseResponse.setChapters(chapterResponses);

            for(Chapter chapter: course.getChaptersList()){
                boolean check = false;
                ChapterResponse chapterResponse = new ChapterResponse();
                chapterResponse.setChapterID(chapter.getChapterID());
                chapterResponse.setChapterName(chapter.getChapterName());
                chapterResponse.setStatus(chapter.isStatus());
                List<ItemResponse> itemResponses = new ArrayList<>();
                chapterResponse.setItems(itemResponses);
                for(Item item: chapter.getItemsList()){
                    if(item.getItemName().toLowerCase().contains("peer")&& item.isStatus() == true){
                        check = true;
                        ItemResponse itemResponse = new ItemResponse();
                        itemResponse.setItemID(item.getItemID());
                        itemResponse.setItemName(item.getItemName());
                        itemResponse.setStatus(item.isStatus());
                        itemResponses.add(itemResponse);
                    }
                }

                if(check){
                    chapterResponses.add(chapterResponse);
                    checkCourse = true;
                }
            }
            if (checkCourse){
                courseResponses.add(courseResponse);
            }
        }
        return submitListResponse;
    }

}
