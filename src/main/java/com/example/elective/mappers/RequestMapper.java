package com.example.elective.mappers;

import com.example.elective.models.Entity;

import javax.servlet.http.HttpServletRequest;

public interface RequestMapper<T extends Entity> {

  T map(HttpServletRequest req);

}
