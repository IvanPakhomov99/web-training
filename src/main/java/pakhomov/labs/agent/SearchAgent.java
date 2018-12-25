package pakhomov.labs.agent;

import java.util.Collection;
import pakhomov.labs.User;
import pakhomov.labs.agent.Exceptions.SearchException;
import pakhomov.labs.db.DaoFactory;
import pakhomov.labs.db.DatabaseException;
import jade.core.Agent;

/**
 * @author ІванПахомов
 *
 */
public class SearchAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started");
	}

	@Override
	protected void takeDown() {
		super.takeDown();
		System.out.println(getAID().getName() + " terminated");
	}

	public void search(String firstName, String lastName) throws SearchException {
		try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            if (users.size() > 0) {
                showUsers(users);
            } else {
                //
            }
        } catch (DatabaseException e) {
            throw new SearchException(e);
        }
	}

	public void showUsers(Collection<User> users) {
		//
	}
}
