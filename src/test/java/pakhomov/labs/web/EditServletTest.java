package pakhomov.labs.web;

import java.util.Date;
import java.text.DateFormat;
import pakhomov.labs.User;

public class EditServletTest extends MockServletTestCase {
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit() {
        Date date = new Date();
        User user = new User(new Long(1000), "John", "Doe", date);
        getMockUserDao().expect("update", user);

        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "");
        addRequestParameter("lastName", "");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
}
