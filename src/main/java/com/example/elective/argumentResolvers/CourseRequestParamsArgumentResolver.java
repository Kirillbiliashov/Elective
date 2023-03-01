package com.example.elective.argumentResolvers;

import com.example.elective.annotations.Selection;
import com.example.elective.utils.CourseSelection;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.example.elective.utils.Constants.*;

public class CourseRequestParamsArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterAnnotation(Selection.class) != null;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
    String sort = req.getParameter(SORT_PARAM);
    String teacher = req.getParameter(TEACHER_PARAM);
    String topic = req.getParameter(TOPIC_PARAM);
    return new CourseSelection(sort, teacher, topic);
  }

}
