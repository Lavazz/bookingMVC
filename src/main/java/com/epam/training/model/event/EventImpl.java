package com.epam.training.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EventImpl implements Event {

    private long id;
    private String title;
    private Date date;

    public EventImpl(String title, Date date) {
        this.title = title;
        this.date = date;
    }
}
