package com.example.elective.mappers.requestMappers;

import com.example.elective.mappers.Mapper;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface that describes mapping operation between HttpServletRequest and Entity
 * @param <T> output class type parameter, subclass of Entity
 * @author Kirill Biliashov
 */

public interface RequestMapper<T> extends Mapper<HttpServletRequest, T> {

  @Override
  T map(HttpServletRequest inpType);

}
