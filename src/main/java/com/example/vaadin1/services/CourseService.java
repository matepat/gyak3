package com.example.vaadin1.services;

import com.example.vaadin1.entities.Course;
import com.example.vaadin1.entities.Room;

import java.util.List;

public interface CourseService {
    List<Course> listAll();
    Course getById(int id);
    Course saveOrUpdate(Course course);
    void delete(int id);
    List<Course> listAllByRoom(Room room);
}
