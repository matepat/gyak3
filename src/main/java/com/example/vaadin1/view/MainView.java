package com.example.vaadin1.view;

import com.example.vaadin1.component.CourseForm;
import com.example.vaadin1.component.RoomForm;
import com.example.vaadin1.entities.Course;
import com.example.vaadin1.entities.Room;
import com.example.vaadin1.services.CourseService;
import com.example.vaadin1.services.RoomService;
import com.example.vaadin1.util.RefreshAware;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@Route
public class MainView extends AbstractView implements RefreshAware {
    private CourseService courseService;
    private RoomService roomService;

    @Autowired
    public void setCourseService(CourseService courseService) {this.courseService = courseService;}
    @Autowired
    public void setRoomService(RoomService roomService) {this.roomService = roomService;}

    @Autowired
    private RoomForm roomForm;
    @Autowired
    private CourseForm courseForm;

    private Grid<Course> courseGrid;
    private Grid<Room> roomGrid;

    @PostConstruct
    public void init(){
        initView();
        List<Course> courses = courseService.listAll();
        List<Room> rooms = roomService.listAll();

        courseGrid = new Grid<>();
        courseGrid.setItems(courses);
        courseGrid.addColumn(course -> course.getId()).setHeader("Azonosító");
        courseGrid.addColumn(course -> course.getName()).setHeader("Név");
        courseGrid.addColumn(course -> course.getRoom().getName()).setHeader("Terem");
        courseGrid.asSingleSelect().addValueChangeListener(e -> {
            if (e.getValue() != null) {
                courseForm.initEdit(e.getValue().getId());
            }
        });
        Button newBtnC = new Button("Új kurzus", VaadinIcon.PLUS.create());
        newBtnC.addClickListener(event -> courseForm.initSave());
        courseForm.setRefreshAware(this);

        roomGrid = new Grid<>();
        roomGrid.setItems(rooms);
        roomGrid.addColumn(room -> room.getId()).setHeader("Azonosító");
        roomGrid.addColumn(room -> room.getName()).setHeader("Név");
        roomGrid.addColumn(room -> room.getLevel()).setHeader("Szint");
        roomGrid.asSingleSelect().addValueChangeListener(e -> {
            if (e.getValue() != null) {
                roomForm.initEdit(e.getValue().getId());
            }
        });
        Button newBtn= new Button("Új terem", VaadinIcon.PLUS.create());
        newBtn.addClickListener(event -> roomForm.initSave());
        roomForm.setRefreshAware(this);

        add(newBtnC);
        add(newBtn);
        add(courseForm);
        add(roomForm);
        add(courseGrid);
        add(roomGrid);

    }

    public MainView(){
    }

    @Override
    public void processRefresh() {
        courseGrid.setItems(courseService.listAll());
        roomGrid.setItems(roomService.listAll());
    }
}
