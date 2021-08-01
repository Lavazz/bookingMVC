package com.epam.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

  private Long id;
  private String name;
  private String email;

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public User(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }


  @Override
  public String toString() {
    return "User: { " +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' + " }";
  }
}
