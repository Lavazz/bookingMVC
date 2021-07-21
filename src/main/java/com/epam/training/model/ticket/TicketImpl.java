package com.epam.training.model.ticket;

import lombok.Data;

@Data
public class TicketImpl implements Ticket {

    private long Id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;

    public TicketImpl(long userId, long eventId, int place, Category category) {
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

}
