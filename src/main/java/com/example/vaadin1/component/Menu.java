package com.example.vaadin1.component;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Menu extends HorizontalLayout {

    public Menu() {
        buildAnchor("Kezdőlap", "/");
        buildAnchor("Új kurzus", "/course");
        buildAnchor("Új terem", "/room");
    }

    private void buildAnchor(String text, String href) {
        Anchor anchor = new Anchor();
        anchor.setText(text);
        anchor.setHref(href);
        add(anchor);
    }
}
