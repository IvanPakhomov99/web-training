package pakhomov.labs.web;

import pakhomov.labs.User;
import pakhomov.labs.db.DaoFactory;
import pakhomov.labs.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class BrowseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.nonNull(req.getParameter("addButton"))) {
            add(req, resp);
        } else if (Objects.nonNull(req.getParameter("editButton"))) {
            edit(req, resp);
        } else if (Objects.nonNull(req.getParameter("deleteButton"))) {
            delete(req, resp);
        } else if (Objects.nonNull(req.getParameter("detailsButton"))) {
            details(req, resp);
        } else {
            browse(req, resp);
        }
    }
	
	private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession(true).setAttribute("users", users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (DatabaseException e) {
            throw new ServletException(e);
        }
    }
}
