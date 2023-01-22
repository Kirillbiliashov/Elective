package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.StudentDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Account;

public class StudentDTOMapper implements Mapper<Account, StudentDTO> {

  private final boolean isBlocked;

  public StudentDTOMapper(boolean isBlocked) {
    this.isBlocked = isBlocked;
  }

  @Override
  public StudentDTO map(Account account) throws MappingException {
    return StudentDTO.newBuilder()
        .setId(account.getId())
        .setFullName(account.getFullName())
        .setUsername(account.getUsername())
        .setEmail(account.getEmail())
        .setBlocked(isBlocked)
        .build();
  }

}
