package com.example.vaadin1.services;
import com.example.vaadin1.entities.Course;
import com.example.vaadin1.entities.Room;
import com.example.vaadin1.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private RoomService roomService;
    @Autowired
    public void setRoomService(RoomService roomService) {this.roomService = roomService;}

    @Override
    public List<Course> listAll() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    @Override
    public Course getById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course saveOrUpdate(Course course) {
        courseRepository.save(course);
        return course;
    }

    @Override
    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> listAllByRoom(Room room){
        List<Course> courses = new ArrayList<>();
        courseRepository.findAllByRoom(room).forEach(courses::add);
        return courses;
    }
}
