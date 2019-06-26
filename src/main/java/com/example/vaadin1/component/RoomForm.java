package com.example.vaadin1.component;

import com.example.vaadin1.entities.Room;
import com.example.vaadin1.services.CourseService;
import com.example.vaadin1.services.RoomService;
import com.example.vaadin1.util.RefreshAware;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class RoomForm extends VerticalLayout {
    private CourseService courseService;
    private RoomService roomService;

    @Autowired
    public void setCourseService(CourseService courseService) {this.courseService = courseService;}
    @Autowired
    public void setRoomService(RoomService roomService) {this.roomService = roomService;}

    private Room room;
    private Binder<Room> binder;
    private TextField name;
    private TextField level;
    private RefreshAware refreshAware;

    @PostConstruct
    public void init(){
        binder = new Binder<>(Room.class);

        name = new TextField("Name");
        level = new TextField("Level");

        binder.forField ( this.level )
                .withNullRepresentation ( " " )
                .withConverter ( new StringToIntegerConverter( Integer.valueOf ( 0 ), "integers only" ) )
                .bind ( Room:: getLevel, Room:: setLevel );

        add(name, level);

        HorizontalLayout buttonBar = new HorizontalLayout();

        Button saveBtn = new Button("Save", VaadinIcon.PENCIL.create());
        saveBtn.addClickListener(event -> {
            roomService.saveOrUpdate(room);
            setVisible(false);
            refreshAware.processRefresh();
            Notification.show("Success!");
        });

        Button deleteBtn = new Button("Delete", VaadinIcon.TRASH.create());
        deleteBtn.addClickListener(event -> {
            roomService.delete(room.getId());
            setVisible(false);
            refreshAware.processRefresh();
            Notification.show("Success!");
        });

        Button cancelBtn = new Button("Cancel", VaadinIcon.CLOSE.create());
        cancelBtn.addClickListener(event -> {
            room = null;
            setVisible(false);
        });

        buttonBar.add(saveBtn, deleteBtn, cancelBtn);
        add(buttonBar);
        setVisible(false);
        binder.bindInstanceFields(this);
        name.focus();
    }

    public void initSave() {
        room = new Room();
        binder.setBean(room);
        setVisible(true);
        name.focus();
    }

    public void initEdit(int id) {
        this.room = roomService.getById(id);
        binder.setBean(room);
        setVisible(true);
        name.focus();
    }

    public void setRefreshAware(RefreshAware refreshAware) {
        this.refreshAware = refreshAware;
    }

}
