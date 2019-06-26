package com.example.vaadin1.component;

import com.example.vaadin1.services.CourseService;
import com.example.vaadin1.services.RoomService;
import com.example.vaadin1.view.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseForm extends AbstractView {
    private CourseService courseService;
    private RoomService roomService;

    @Autowired
    public void setCourseService(CourseService courseService) {this.courseService = courseService;}
    @Autowired
    public void setRoomService(RoomService roomService) {this.roomService = roomService;}
}
