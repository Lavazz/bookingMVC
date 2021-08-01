package com.epam.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Event {

  private Long id;
  private String title;
  private LocalDateTime date;

  public Event(String title, LocalDateTime date) {
    this.title = title;
    this.date = date;
  }

  public Event(Long id, String title, LocalDateTime date) {
    this.id = id;
    this.title = title;
    this.date = date;
  }

  public Event(Long id, String title, String dateText) {
    this.id = id;
    this.title = title;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    this.date = LocalDateTime.parse(dateText, formatter);
  }


  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return "Event : { " +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", date='" + formatter.format(date) +
            "' }";
  }
}
