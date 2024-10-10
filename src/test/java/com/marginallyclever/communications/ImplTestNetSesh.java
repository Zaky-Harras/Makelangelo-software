package com.marginallyclever.communications;

//On veut instancier la classe abstraite avec ses méthodes abstraites :
/*
public abstract void closeConnection();

	public abstract void openConnection(String connectionName) throws Exception;

	public abstract boolean isOpen();

	public abstract void sendMessage(String msg) throws Exception;
*/
//Pour ensuite pouvoir tout tester avec la classe NetworkSession_Test.
public class ImplTestNetSesh extends NetworkSession {
    private boolean open = false;

    @Override
    public void openConnection(String connectionName) throws Exception {
        setName(connectionName);
        open = true;
    }

    @Override
    public void closeConnection() {
        open = false;
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public void sendMessage(String msg) throws Exception {
        System.out.println("Message sent: " + msg);
    }
}
