package by.reshetilova.callcenter;

import java.util.ArrayList;

public class CallCenter {
    private final static int ATTEMPTS = 2;
    ArrayList<Operator> operators;

    public CallCenter(ArrayList<Operator> operators) {
        this.operators = operators;
    }

    public synchronized Operator tryCall(Client client, int waitingTime) {
        int attempts = 0;
        boolean success = false;
        while (!success) {
            for (var operator : operators) {
                if (searchFreeOperator(operator, client)) {
                    return operator;
                }
            }

            if (!success) {
                try {
                    attempts++;
                    if (checkAttempts(attempts, waitingTime, client))
                        return null;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public synchronized void endCall(Operator operator) {
        operator.setClient(null);
        operators.add(operator);
        notify();
    }

    public synchronized boolean checkAttempts(int attempts, int waitingTime, Client client)
        throws InterruptedException {
        if (attempts == ATTEMPTS) {
            System.out.println("Client #" + client.getId() + " timed out ");
            wait(waitingTime);
            return true;
        }
        else {
            System.out.println("Client #" + client.getId() + " waiting ");
            wait(waitingTime);
        }

        return false;
    }

    private boolean searchFreeOperator(Operator operator, Client client) {
        if (operator.getClient() == null) {
            operator.setClient(client);
            operators.remove(operator);
            return true;
        }
        return false;
    }
}
