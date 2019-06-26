package com.example.vaadin1.view;

import com.example.vaadin1.component.Menu;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class AbstractView extends VerticalLayout {
    public void initView(){
        add(new Menu());
    }
}
