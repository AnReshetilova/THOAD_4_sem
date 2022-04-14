package by.reshetilova.callcenter;

public class Client implements Runnable{
    private final static int WAITING_TIME = 100;
    private final int id;
    private final CallCenter callCenter;

    public Client(CallCenter callCenter, int id) {
        this.callCenter = callCenter;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        Operator operator = null;
        try {
            while (operator == null) {
                System.out.println("Client #" + id + " calls");
                operator = callCenter.tryCall(this, WAITING_TIME);
            }
            System.out.println("Client #" + id + " got through to operator #" + operator.getId());
            operator.talk();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (operator != null) {
                System.out.println("Client #" + id + " ends");
                callCenter.endCall(operator);
            }
        }
    }
}
