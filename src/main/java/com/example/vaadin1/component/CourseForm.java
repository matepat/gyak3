package com.example.vaadin1.component;

import com.example.vaadin1.entities.Course;
import com.example.vaadin1.entities.Room;
import com.example.vaadin1.services.CourseService;
import com.example.vaadin1.services.RoomService;
import com.example.vaadin1.util.RefreshAware;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Objects;

@SpringComponent
@UIScope
public class CourseForm extends VerticalLayout {
    private CourseService courseService;
    private RoomService roomService;

    @Autowired
    public void setCourseService(CourseService courseService) {this.courseService = courseService;}
    @Autowired
    public void setRoomService(RoomService roomService) {this.roomService = roomService;}

    private Course course;
    private Binder<Course> binder;
    private TextField name;
    private Select<Room> room;

    private RefreshAware refreshAware;

    @PostConstruct
    public void init(){
        binder = new Binder<>(Course.class);

        name = new TextField("Name");

        room = new Select<>();
        room.setRequiredIndicatorVisible(true);
        room.setLabel("Required");
        room.setItems(roomService.listAll());
        room.setTextRenderer(Room::getName);
        room.setPlaceholder("Select an option");
        room.setEmptySelectionCaption("Select an option");
        room.setEmptySelectionAllowed(true);
        room.setItemEnabledProvider(Objects::nonNull);
        room.addComponents(null, new Hr());

        add(name, room);

        HorizontalLayout buttonBar = new HorizontalLayout();

        Button saveBtn = new Button("Save", VaadinIcon.PENCIL.create());
        saveBtn.addClickListener(event -> {
            courseService.saveOrUpdate(course);
            setVisible(false);
            refreshAware.processRefresh();
            Notification.show("Success!");
        });

        Button deleteBtn = new Button("Delete", VaadinIcon.TRASH.create());
        deleteBtn.addClickListener(event -> {
            courseService.delete(course.getId());
            setVisible(false);
            refreshAware.processRefresh();
            Notification.show("Success!");
        });

        Button cancelBtn = new Button("Cancel", VaadinIcon.CLOSE.create());
        cancelBtn.addClickListener(event -> {
            course = null;
            setVisible(false);
        });

        buttonBar.add(saveBtn, deleteBtn, cancelBtn);
        add(buttonBar);
        setVisible(false);
        binder.bindInstanceFields(this);
        name.focus();
    }

    public void initSave() {
        course = new Course();
        binder.setBean(course);
        setVisible(true);
        name.focus();
    }

    public void initEdit(int id) {
        this.course = courseService.getById(id);
        binder.setBean(course);
        setVisible(true);
        name.focus();
    }

    public void setRefreshAware(RefreshAware refreshAware) {
        this.refreshAware = refreshAware;
    }
}
