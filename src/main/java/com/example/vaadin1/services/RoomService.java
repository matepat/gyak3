package com.example.vaadin1.services;

import com.example.vaadin1.entities.Room;

import java.util.List;

public interface RoomService {
    List<Room> listAll();
    Room getById(int id);
    Room saveOrUpdate(Room room);
    void delete(int id);
}
