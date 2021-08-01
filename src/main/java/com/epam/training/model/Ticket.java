package com.epam.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ticket {

  public enum Category {STANDARD, PREMIUM, BAR}

  private Long id;
  private Long eventId;
  private Long userId;
  private int place;
  private Category category;


  public Ticket(Long eventId, Long userId, int place, Category category) {
    this.eventId = eventId;
    this.userId = userId;
    this.place = place;
    this.category = category;
  }

  public Ticket(Long id, Long eventId, Long userId, int place, Category category) {
    this.id = id;
    this.eventId = eventId;
    this.userId = userId;
    this.place = place;
    this.category = category;
  }


  @Override
  public String toString() {
    return "Ticket: { " +
        "id=" + id +
        ", eventId='" + eventId + '\'' +
        ", userId='" + userId + '\'' +
        ", place='" + place + '\'' +
        ", category='" + category + '\'' +
        " }";
  }
}
