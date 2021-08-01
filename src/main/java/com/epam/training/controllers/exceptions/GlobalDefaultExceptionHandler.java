package com.epam.training.controllers.exceptions;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
  public static final String DEFAULT_ERROR_VIEW = "error";

  @ExceptionHandler(value = Exception.class)
  public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
    if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
      throw ex;
    }
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("message", "REST Controller Error");
    mav.addObject("url", req.getRequestURL());
    mav.setViewName(DEFAULT_ERROR_VIEW);
    return mav;
  }
}

/*
 * Sources:
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 * https://www.baeldung.com/exception-handling-for-rest-with-spring
 */
