package com.example.elective.services;

import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.DAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

  @Spy
  StudentServiceTestClass service;

  @Mock
  private TransactionManager transactionManager;

  @Mock
  private AccountDAO dao;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    service.setTransactionManager(transactionManager);
  }

  @Test
  void testChangeBlockStatus() throws Exception {
    final int ID = 5;
    Mockito.doNothing().when(service).performDaoWriteOperation(any());
    final Account account = Account.newBuilder().setId(ID).build();
    when(dao.find(anyInt())).thenReturn(Optional.of(account));
   doAnswer(i -> {
     Optional<Account> optAcc = dao.find(ID);
     if (optAcc.isPresent()) {
       Account acc = optAcc.get();
       acc.getBuilder().setBlocked(!acc.isBlocked());
       dao.update(acc);
     }
     return null;
   }).when(service).performDaoWriteOperation(any(DAOWriteOperation.class));
    service.changeBlockStatus(1);
    verify(service, times(1)).performDaoWriteOperation(any());
    verify(transactionManager, times(1)).initTransaction(any(DAO.class));
    verify(dao, times(1)).find(5);
    verify(dao, times(1)).update(account);
  }


  private static class StudentServiceTestClass extends StudentService {
    public void setTransactionManager(TransactionManager manager) {
      this.transactionManager = manager;
    }
  }
}
