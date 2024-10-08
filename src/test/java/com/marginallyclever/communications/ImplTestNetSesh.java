package com.marginallyclever.communications;

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
        // Logique fictive
        System.out.println("Message sent: " + msg);
    }
}
