package pakhomov.labs.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pakhomov.labs.User;
import pakhomov.labs.db.DaoFactory;
import pakhomov.labs.db.DatabaseException;

public class EditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final DateFormat FORMATTER = new SimpleDateFormat("dd.mm.yyyy");

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (Objects.nonNull(req.getParameter("okButton"))) {
			updateUser(req, resp);
		} else if (Objects.nonNull(req.getParameter("cancelButton"))) {
			cancelEdit(req, resp);
		} else {
			showPage(req, resp);
		}
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = getUser(req);
		try {
			processUser(user);
		} catch (DatabaseException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		req.getRequestDispatcher("/browse").forward(req, resp);
	}

	private void cancelEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().update(user);
	}

	private User getUser(HttpServletRequest req) {
		User user = new User();
		String idString = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateString = req.getParameter("date");
		if (Objects.nonNull(idString)) {
			user.setId(new Long(idString));
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			user.setBirthday(FORMATTER.parse(dateString));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

}
