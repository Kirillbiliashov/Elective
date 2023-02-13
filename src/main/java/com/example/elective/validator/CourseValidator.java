package com.example.elective.validator;

import com.example.elective.models.Course;
import com.example.elective.services.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Component
public class CourseValidator implements Validator {

  @Autowired
  private CourseService courseService;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(Course.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Course course = (Course) target;
    if (course.getEndDate().before(course.getStartDate())) {
      errors.rejectValue("endDate", "", "End date should be after start date");
      return;
    }
    boolean isNameTaken = courseService.findByName(course.getName()).isPresent();
    Optional<Course> optCourse = courseService.findById(course.getId());
    optCourse.ifPresentOrElse(c -> {
      if (!Objects.equals(c.getName(), course.getName()) && isNameTaken) {
        errors.rejectValue("name", "", "Course name is taken");
      }
    }, () -> {
      if (isNameTaken) {
        errors.rejectValue("name", "", "Course name is taken");
      }
    });
  }
}
