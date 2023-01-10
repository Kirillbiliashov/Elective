package com.example.elective.daos;

import com.example.elective.dao.sql.mysql.AccountMySQLDAO;
import com.example.elective.mappers.Mapper;
import com.example.elective.mappers.resultSetMappers.ResultSetMapper;
import com.example.elective.models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AccountDAOTest {

  private static final AccountDAOTestClass dao = new AccountDAOTestClass();
  private static final int ACC_ID = 5;
  @Mock
  private Connection conn;
  @Mock
  private PreparedStatement ps;
  @Mock
  private ResultSetMapper<Account> mapper;
  @Mock
  private ResultSet rs;

  @BeforeEach
  void beforeEach() throws SQLException {
    MockitoAnnotations.openMocks(this);
    dao.setConnection(conn);
    dao.setMapper(mapper);
    when(conn.prepareStatement(anyString())).thenReturn(ps);
    when(conn.prepareStatement(anyString(), anyInt())).thenReturn(ps);
    when(ps.executeQuery()).thenReturn(rs);
  }

  @Test
  void testFind() throws Exception {
    Account acc = Account.newBuilder().setId(ACC_ID).build();
    when(mapper.map(rs)).thenReturn(acc);
    when(rs.next()).thenReturn(true);
    Assertions.assertEquals(Optional.of(acc), dao.find(ACC_ID));
    verify(rs, times(1)).next();
    verify(ps, times(1)).setObject(1, ACC_ID);
  }

  @Test
  void testUpdate() throws Exception {
    final boolean isBlocked = false;
    Account acc = Account.newBuilder()
        .setId(ACC_ID)
        .setBlocked(isBlocked)
        .build();
    dao.update(acc);
    verify(ps, times(1)).setObject(1, isBlocked);
    verify(ps, times(1)).setObject(2, ACC_ID);
    verify(ps, times(1)).executeUpdate();
  }

  @Test
  void testSaveSuccess() throws Exception {
    Account acc = getTestAccount();
    when(ps.getGeneratedKeys()).thenReturn(rs);
    when(rs.next()).thenReturn(true);
    when(rs.getInt(1)).thenReturn(ACC_ID);
    dao.save(acc);
    verifyExpectedNumberOfCalls();
    Assertions.assertEquals(acc.getId(), ACC_ID);
  }

  @Test
  void testSaveFail() throws Exception {
    final int EXP_ID = 0;
    Account acc = getTestAccount();
    when(ps.getGeneratedKeys()).thenReturn(rs);
    when(rs.next()).thenReturn(false);
    dao.save(acc);
    verifyExpectedNumberOfCalls();
    Assertions.assertEquals(acc.getId(), EXP_ID);
  }

  private Account getTestAccount() {
    return Account.newBuilder()
        .setUsername("login")
        .setPassword("password")
        .setFirstName("firstName")
        .setLastName("lastName")
        .build();
  }

  private void verifyExpectedNumberOfCalls() throws Exception {
    verify(ps, times(6)).setObject(anyInt(), any());
    verify(ps, times(1)).executeUpdate();
    verify(rs, times(1)).next();
  }


  private static class AccountDAOTestClass extends AccountMySQLDAO {
    public void setMapper(Mapper<ResultSet, Account> mapper) {
      this.mapper = mapper;
    }
  }

}
