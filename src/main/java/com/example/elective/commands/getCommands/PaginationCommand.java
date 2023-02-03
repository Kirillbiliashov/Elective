package com.example.elective.commands.getCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.mappers.dtoMappers.StudentDTOMapper;
import com.example.elective.models.Account;
import com.example.elective.selection.Pagination;
import com.example.elective.services.interfaces.AccountService;
import com.example.elective.utils.PaginationUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.example.elective.utils.Constants.*;

/**
 * Class that renders paginated users depending on their role
 * @author Kirill Biliashov
 */

public class PaginationCommand extends Command {

  private AccountService service;
  private final String attrName;
  private final String roleName;
  private final String jspPage;
  private final StudentDTOMapper studentMapper = new StudentDTOMapper();

  public PaginationCommand(String attrName, String roleName, String jspPage) {
    this.attrName = attrName;
    this.roleName = roleName;
    this.jspPage = jspPage;
  }

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (service == null) service =
        (AccountService) context.getAttribute(ACCOUNT_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int page = PaginationUtils.getPageNumber(req);
    int displayCount = PaginationUtils.getItemsPerPage(req);
    int total = service.getTotalCount(roleName);
    Pagination pagination = new Pagination(page, displayCount, total);
    PaginationUtils.setPageAttributes(req, pagination.getPage());
    req.setAttribute(PAGES_COUNT_ATTR, pagination.getPagesCount());
    setEntitiesAttribute(pagination);
    forward(jspPage);
  }

  private void setEntitiesAttribute(Pagination pagination) {
    if (roleName.equals(STUDENT_ROLE)) {
      List<Account> students = service.getPaginatedStudents(pagination);
      req.setAttribute(attrName, students.stream().map(studentMapper::map).toList());
    } else {
      req.setAttribute(attrName, service.getPaginatedTeachers(pagination));
    }
  }

}
