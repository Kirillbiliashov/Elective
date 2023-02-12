package com.example.elective.services.impl;

import com.example.elective.models.Account;
import com.example.elective.models.Blocklist;
import com.example.elective.models.Role;
import com.example.elective.repository.AccountRepository;
import com.example.elective.repository.BlocklistRepository;
import com.example.elective.services.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class containing business logic method regarding students
 * @author Kirill Biliashov
 */

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private BlocklistRepository blocklistRepository;

  @Override
  public void changeBlockStatus(int id) {
    Account student = accountRepository.getReferenceById(id);
    blocklistRepository.findByStudent(student)
        .ifPresentOrElse((blocklist ->
                blocklistRepository.deleteById(blocklist.getId())),
            () -> {
              Blocklist blocklist = new Blocklist();
              blocklist.setStudent(student);
              blocklistRepository.save(blocklist);
            });
  }

  @Override
  public void save(Account student) {
    student.setRole(Role.ROLE_STUDENT);
    student.setPassword(encoder.encode(student.getPassword()));
    accountRepository.save(student);
  }

}
