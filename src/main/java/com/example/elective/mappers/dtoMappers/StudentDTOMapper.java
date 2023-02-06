package com.example.elective.mappers.dtoMappers;

import com.example.elective.dto.StudentDTO;
import com.example.elective.exceptions.MappingException;
import com.example.elective.mappers.Mapper;
import com.example.elective.models.Account;
import org.springframework.stereotype.Component;

@Component
public class StudentDTOMapper implements Mapper<Account, StudentDTO> {

  @Override
  public StudentDTO map(Account account) {
    return StudentDTO.newBuilder()
        .setId(account.getId())
        .setFullName(account.getFullName())
        .setUsername(account.getUsername())
        .setEmail(account.getEmail())
        .setBlocked(account.getBlock() != null)
        .build();
  }

}
