package com.example.elective.services;

import com.example.elective.dao.sql.TransactionManager;
import com.example.elective.exceptions.DAOException;
import com.example.elective.exceptions.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AbstractServiceTest {

  private static final AbstractService service = new Service();

  @Mock
  private TransactionManager tm;
  @Mock
  private DAOReader<String> reader;
  @Mock
  private DAOWriter writer;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRead() throws Exception {
    final String str = "testString";
    when(reader.read()).thenReturn(str);
    Assertions.assertEquals(str, service.read(tm, reader));
    verify(reader, times(1)).read();
    verify(tm, times(1)).commitTransaction();
    verify(tm, times(1)).endTransaction();
  }

  @Test
  void testReadThrowsDAOException() throws Exception {
    when(reader.read()).thenThrow(DAOException.class);
    Assertions.assertThrows(ServiceException.class, () -> service.read(tm, reader));
    verify(reader, times(1)).read();
    verify(tm, times(1)).rollbackTransaction();
    verify(tm, times(1)).endTransaction();
  }

  @Test
  void testWrite() throws Exception {
    service.write(tm, writer);
    verify(writer, times(1)).write();
    verify(tm, times(1)).commitTransaction();
    verify(tm, times(1)).endTransaction();
  }

  @Test
  void testWriteThrowsDAOException() throws Exception {
    Mockito.doThrow(DAOException.class).when(writer).write();
    Assertions.assertThrows(ServiceException.class, () -> service.write(tm, writer));
    verify(writer, times(1)).write();
    verify(tm, times(1)).rollbackTransaction();
    verify(tm, times(1)).endTransaction();
  }


  private static class Service extends AbstractService {}

}
