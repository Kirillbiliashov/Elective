package com.example.elective.argumentResolvers;

import com.example.elective.annotations.PageParam;
import com.example.elective.utils.Pagination;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.elective.utils.Constants.PAGE_PARAM;
import static com.example.elective.utils.Constants.SIZE_PARAM;

public class PaginationRequestParamsArgumentResolver implements HandlerMethodArgumentResolver {

  private final String intPattern = "\\d+";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterAnnotation(PageParam.class) != null;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) {
    HttpServletRequest req = (HttpServletRequest) webRequest.getNativeRequest();
    String pageStr = req.getParameter(PAGE_PARAM);
    String sizeStr = req.getParameter(SIZE_PARAM);
    Integer page = pageStr != null ? Integer.getInteger(pageStr, 0) : null;
    Integer size = sizeStr != null ? Integer.valueOf(sizeStr) : null;
    return new Pagination(size, page);
  }
}
