package com.example.elective.services;

import com.example.elective.dao.DAOFactory;
import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.models.Account;
import com.example.elective.services.impl.AccountServiceImpl;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.utils.PasswordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AccountServiceTest {

  private static final AccountServiceImpl accService = new AccountServiceImpl();
  private static final String login = "login";
  private static final String password = "password";
  private static final List<String> logins = Arrays.asList("login1", "login2",
      "login3", "login4");

  @Mock
  private TransactionManager tm;
  @Mock
  private AccountDAO accountDAO;
  @Mock
  private DAOFactory factory;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    accService.daoFactory = factory;
    Mockito.when(factory.getAccountDAO()).thenReturn(accountDAO);
  }

  @Test
  void testFind() throws Exception {
    final int id = 5;
    Account acc = Account.newBuilder().setId(id).build();
    Mockito.when(accountDAO.find(Mockito.anyInt())).thenReturn(Optional.of(acc));
    Assertions.assertEquals(Optional.of(acc), accService.find(tm, id));
    verify(tm, times(1)).initTransaction(accountDAO);
  }

  @Test
  void testFindByCredentials() throws Exception {
    try (MockedStatic<TransactionManager> mockedStatic =
             Mockito.mockStatic(TransactionManager.class)) {
      Optional<Account> optAcc = getOptionalAcc();
      mockedStatic.when(TransactionManager::getInstance).thenReturn(tm);
      when(accountDAO.findByLogin(anyString())).thenReturn(optAcc);
      Assertions.assertEquals(optAcc, accService.findByCredentials(login, password));
      verify(tm, times(1)).initTransaction(accountDAO);
      verify(tm, times(1)).commitTransaction();
      verify(tm, times(1)).endTransaction();
    }
  }

  private Optional<Account> getOptionalAcc() {
    return Optional.of(Account.newBuilder()
        .setUsername(login)
        .setPassword(PasswordUtils.hashPassword(password))
        .build());
  }

  @Test
  void testGetLogins() throws Exception {
    try (MockedStatic<TransactionManager> mockedStatic =
             Mockito.mockStatic(TransactionManager.class)) {
      mockedStatic.when(TransactionManager::getInstance).thenReturn(tm);
      when(accountDAO.getLogins()).thenReturn(logins);
      Assertions.assertEquals(logins, accService.getLogins());
      verify(tm, times(1)).initTransaction(accountDAO);
      verify(tm, times(1)).commitTransaction();
      verify(tm, times(1)).endTransaction();
    }
  }

}
