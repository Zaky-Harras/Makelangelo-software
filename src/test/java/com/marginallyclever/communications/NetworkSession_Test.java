package com.marginallyclever.communications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NetworkSession_Test {
    private ImplTestNetSesh networkSession;

    @BeforeEach
    public void setUp() {
        networkSession = new ImplTestNetSesh();
    }

    @Test
    public void testOpenConnection() throws Exception {
        networkSession.openConnection("TestConnection");
        assertTrue(networkSession.isOpen(), "La connexion devrait être ouverte");
        assertEquals("TestConnection", networkSession.getName(), "Le nom de la connexion devrait être 'TestConnection'");
    }

    @Test
    public void testCloseConnection() throws Exception{
        networkSession.openConnection("TestConnection");
        networkSession.closeConnection();
        assertFalse(networkSession.isOpen(), "La connexion devrait être fermée");
    }

    @Test
    public void testAddListenerAndNotify() {
        NetworkSessionListener listener = evt -> assertEquals(NetworkSessionEvent.DATA_SENT, evt.flag);
        networkSession.addListener(listener);
        networkSession.notifyDataSent("Test data");
    }

    @Test
    public void testRemoveListener() {
        NetworkSessionListener listener = evt -> fail("Le listener ne devrait pas être notifié après suppression.");
        networkSession.addListener(listener);
        networkSession.removeListener(listener);
        networkSession.notifyDataSent("Test data");
    }
}

