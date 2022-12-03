package com.example.elective.mappers;

import com.example.elective.exceptions.MappingException;
import com.example.elective.models.Entity;

public interface Mapper<U, V> {

  V map(U inpType) throws MappingException;

}
