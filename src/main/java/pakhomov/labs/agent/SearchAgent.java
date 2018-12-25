package pakhomov.labs.agent;

import java.util.Collection;
import pakhomov.labs.User;
import pakhomov.labs.agent.Exceptions.SearchException;
import pakhomov.labs.db.DaoFactory;
import pakhomov.labs.db.DatabaseException;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * @author ІванПахомов
 *
 */
public class SearchAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AID[] aids;

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started");
		DFAgentDescription description = new DFAgentDescription();
		description.setName(getAID());
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName("JADE-searching");
		serviceDescription.setType("searching");
		description.addServices(serviceDescription);
		try {
			DFService.register(this, description);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		addBehaviour(new RequestServer());
	}

	@Override
	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated");
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		super.takeDown();
	}

	public void search(String firstName, String lastName) throws SearchException {
		try {
			Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			if (users.size() > 0) {
				showUsers(users);
			} else {
				addBehaviour(new SearchRequestBehaviour(aids, firstName, lastName));
			}
		} catch (DatabaseException e) {
			throw new SearchException(e);
		}
	}

	public void showUsers(Collection<User> users) {
		//
	}
}
