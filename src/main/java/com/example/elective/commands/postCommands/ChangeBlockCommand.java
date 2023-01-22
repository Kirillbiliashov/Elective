package com.example.elective.commands.postCommands;

import com.example.elective.commands.Command;
import com.example.elective.exceptions.ServiceException;
import com.example.elective.services.interfaces.BlocklistService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.elective.utils.Constants.ADMIN_URL;
import static com.example.elective.utils.Constants.BLOCKLIST_SERVICE;

/**
 * Class that calls corresponding service method after client changed student's block status
 * @author Kirill Biliashov
 */

public class ChangeBlockCommand extends Command {

  private static final String JSP_PAGE = ADMIN_URL + "/students";

  private BlocklistService blocklistService;

  @Override
  public void init(ServletContext context, HttpServletRequest req,
                   HttpServletResponse resp) {
    super.init(context, req, resp);
    if (blocklistService == null) blocklistService =
        (BlocklistService) context.getAttribute(BLOCKLIST_SERVICE);
  }

  @Override
  public void process() throws ServletException, IOException {
    int id = getIdFromPathInfo();
    try {
      blocklistService.changeBlockStatus(id);
    } catch (ServiceException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
    resp.sendRedirect(JSP_PAGE + "?" + req.getQueryString());
  }

}
