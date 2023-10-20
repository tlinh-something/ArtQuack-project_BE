package com.example.demo.controller;

import com.example.demo.REPO.CourseRepo;
import com.example.demo.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/mycourse")
public class ApiController {

    @Autowired
    private CourseRepo courseRepo;

    @GetMapping(value = "/")
    public String getpage(){
        return "Welcome";
    }

    @GetMapping(value = "/course")
    public List<Course> getCourse(){
        return courseRepo.findAll();
    }

    @PostMapping(value = "/savecourse")
    public String saveCourse(@RequestBody Course course){
        courseRepo.save(course);
        return "saved...";
    }

    @PutMapping(value = "/updatecourse/{courseID}")
    public String updateCourse(@PathVariable("courseID") String courseID, @RequestBody Course course){
        Course updatedCourse = courseRepo.findById(courseID).get();
        updatedCourse.setCourse_code(course.getCourse_code());
        updatedCourse.setCateID(course.getCateID());
        updatedCourse.setLevelID(course.getLevelID());
        updatedCourse.setName(course.getName());
        updatedCourse.setDescription(course.getDescription());
        updatedCourse.setPrice(course.getPrice());
        updatedCourse.setViewer(course.getViewer());
        updatedCourse.setBuyer(course.getBuyer());
        updatedCourse.setRate(course.getRate());
        courseRepo.save(updatedCourse);
        return "updated....";
    }

    @DeleteMapping(value = "/deletecourse/{courseID}")
    public String deleteCourse(@PathVariable("courseID") String courseID){
        Course deleteCourse = courseRepo.findById(courseID).get();
        courseRepo.delete(deleteCourse);

        return "Delete course with the id:" + courseID;
    }
}
