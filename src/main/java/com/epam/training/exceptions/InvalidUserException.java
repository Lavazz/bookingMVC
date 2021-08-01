package com.epam.training.exceptions;

public class InvalidUserException extends Exception {

  public InvalidUserException(String errorMessage) {
    super(errorMessage);
  }
}
