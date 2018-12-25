package pakhomov.labs.agent.Exceptions;

import java.util.Collection;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import pakhomov.labs.User;
import pakhomov.labs.agent.SearchAgent;

public class RequestServer extends CyclicBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
        if (message != null) {
            if (message.getPerformative() == ACLMessage.REQUEST) {
                myAgent.send(createReply(message));
            } else {
                Collection<User> users = parseMessage(message);
                ((SearchAgent) myAgent).showUsers(users);
            }
        } else {
            block();
        }
	}

	private Collection<User> parseMessage(ACLMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

	private ACLMessage createReply(ACLMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

}
