package com.example.vaadin1.repositories;

import com.example.vaadin1.entities.Course;
import com.example.vaadin1.entities.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    List<Course> findAllByRoom(Room room);
}
