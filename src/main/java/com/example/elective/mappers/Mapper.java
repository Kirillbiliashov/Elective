package com.example.elective.mappers;

import com.example.elective.models.Entity;

public interface Mapper<U, V extends Entity> {

  V map(U inpType);

}
