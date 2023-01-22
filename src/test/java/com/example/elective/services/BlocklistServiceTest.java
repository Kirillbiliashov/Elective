package com.example.elective.services;

import com.example.elective.dao.DAOFactory;
import com.example.elective.dao.interfaces.AccountDAO;
import com.example.elective.dao.interfaces.BlocklistDAO;
import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.models.Account;
import com.example.elective.models.Blocklist;
import com.example.elective.services.impl.AccountServiceImpl;
import com.example.elective.services.impl.BlocklistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class BlocklistServiceTest {

  private static final BlocklistServiceImpl service = new BlocklistServiceImpl();

  @Mock
  private TransactionManager tm;
  @Mock
  private BlocklistDAO dao;
  @Mock
  private DAOFactory factory;
  @Mock
  private Blocklist blocklist;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
    service.daoFactory = factory;
    Mockito.when(factory.getBlocklistDAO()).thenReturn(dao);
  }

  @Test
  void testBlock() throws Exception {
    final int ID = 1;
    try (MockedStatic<TransactionManager> mockedStatic =
             Mockito.mockStatic(TransactionManager.class)) {
      mockedStatic.when(TransactionManager::getInstance).thenReturn(tm);
      when(dao.find(anyInt())).thenReturn(Optional.empty());
      service.changeBlockStatus(ID);
      verify(dao, times(1)).save(ID);
      verifyTransactionManager();
    }
  }

  @Test
  void testUnlock() throws Exception {
    final int ID = 1;
    try (MockedStatic<TransactionManager> mockedStatic =
             Mockito.mockStatic(TransactionManager.class)) {
      mockedStatic.when(TransactionManager::getInstance).thenReturn(tm);
      when(dao.find(anyInt())).thenReturn(Optional.of(blocklist));
      service.changeBlockStatus(ID);
      verify(dao, times(1)).delete(ID);
      verifyTransactionManager();
    }
  }

  private void verifyTransactionManager() {
    verify(tm, times(1)).initTransaction(dao);
    verify(tm, times(1)).commitTransaction();
    verify(tm, times(1)).endTransaction();
  }

}
