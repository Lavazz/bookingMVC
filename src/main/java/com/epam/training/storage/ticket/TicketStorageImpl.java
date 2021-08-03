package com.epam.training.storage.ticket;

import com.epam.training.model.ticket.Ticket;
import com.epam.training.model.ticket.TicketImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TicketStorageImpl implements TicketStorage {

    private final Map<Long, Ticket> ticketMap;

    private TicketStorageImpl() {
        ticketMap = new HashMap<>();
    }

    @Override
    public Ticket bookTicket(Ticket ticket) {
        return ticketMap.put(ticket.getId(), ticket);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketMap.remove(ticketId) != null;
    }

    @Override
    public List<Ticket> findAll() {
       // return new ArrayList<>(ticketMap.values());
        List<Ticket> tickets=new ArrayList<>();
        tickets.add(new TicketImpl(1L, 1L, 1, Ticket.Category.STANDARD));
        tickets.add(new TicketImpl(2L, 2L, 2, Ticket.Category.BAR));
        return tickets;
    }

    @Override
    public Ticket getTicketById(long ticketId) {
        return ticketMap.get(ticketId);
    }

    @Override
    public Map<Long, Ticket> getTickets() {
        return ticketMap;
    }
}
