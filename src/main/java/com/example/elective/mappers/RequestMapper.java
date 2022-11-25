package com.example.elective.mappers;

import javax.servlet.http.HttpServletRequest;

public interface RequestMapper<T> {
  T map(HttpServletRequest req);
}
