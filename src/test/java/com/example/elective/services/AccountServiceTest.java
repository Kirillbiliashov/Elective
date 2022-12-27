package com.example.elective.services;

import com.example.elective.dao.interfaces.DAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.models.Account;
import com.example.elective.utils.PasswordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

  @Spy
  private AccountServiceTestClass service = new AccountServiceTestClass();

  @Mock
  private TransactionManager transactionManager;


  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    service.setTransactionManager(transactionManager);
  }

  @Test
  void testFinByCredentialsSuccess() throws Exception {
    final String LOGIN = "login";
    final String PASSWORD = "password";
    Optional<Account> optAcc = Optional.of(Account
        .newBuilder()
        .setUsername(LOGIN)
        .setPassword(PasswordUtils.hashPassword(PASSWORD))
        .build());
    doReturn(optAcc).when(service).performDaoReadOperation(any());
    Assertions.assertEquals(optAcc, service.findByCredentials(LOGIN, PASSWORD));
    verify(service, times(1)).performDaoReadOperation(any());
    verifyTransactionManagerInitCall();
  }

  @Test
  void testFindByCredentialsAdmin() throws Exception {
    final String ADMIN_STR = "admin";
    Optional<Account> optAcc = Optional.of(Account
        .newBuilder()
        .setUsername(ADMIN_STR)
        .setPassword(ADMIN_STR)
        .build());
    doReturn(optAcc).when(service).performDaoReadOperation(any());
    Assertions.assertEquals(optAcc, service.findByCredentials(ADMIN_STR, ADMIN_STR));
    verify(service, times(1)).performDaoReadOperation(any());
    verifyTransactionManagerInitCall();
  }

  @Test
  void testFindByRole() throws Exception {
    List<Account> accs = Arrays.asList(Account.newBuilder().build(),
        Account.newBuilder().build(), Account.newBuilder().build());
    doReturn(accs).when(service).performDaoReadOperation(any());
    Assertions.assertEquals(accs, service.getByRole("role"));
    verify(service, times(1)).performDaoReadOperation(any());
    verifyTransactionManagerInitCall();
  }

  private void verifyTransactionManagerInitCall() {
    verify(transactionManager, times(1)).initTransaction(any(DAO.class));
  }

  private static class AccountServiceTestClass extends AccountService {
    public void setTransactionManager(TransactionManager manager) {
      this.transactionManager = manager;
    }
  }

}
