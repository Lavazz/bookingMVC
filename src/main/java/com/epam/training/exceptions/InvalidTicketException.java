package com.epam.training.exceptions;

public class InvalidTicketException extends Exception {

  public InvalidTicketException(String errorMessage) {
    super(errorMessage);
  }
}
