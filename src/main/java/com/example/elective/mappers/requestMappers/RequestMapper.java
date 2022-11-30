package com.example.elective.mappers.requestMappers;

import com.example.elective.mappers.Mapper;
import com.example.elective.models.Entity;

import javax.servlet.http.HttpServletRequest;

public interface RequestMapper<T extends Entity> extends Mapper<HttpServletRequest, T> {
}
