package com.marginallyclever.communications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// On prend cette fonction, car elle est simple courte et semble importante.
// Rien de bon peut arriver si on a pas les bonnes connections.
public class NetworkSession_Test {
    private ImplTestNetSesh networkSession;
    //On initie var qu'on va re-utiliser :
    String nom = "TestConnection";

    @BeforeEach
    public void setUp() {
        networkSession = new ImplTestNetSesh();
    }

    //On im
    @Test
    public void testOpenConnection() throws Exception {
        networkSession.openConnection(nom);
        assertTrue(networkSession.isOpen(), "La connexion devrait être ouverte -> isOpen True");
        assertEquals("TestConnection", networkSession.getName(), "Le nom de la connexion devrait être : " + nom);
    }

    @Test
    public void testCloseConnection() throws Exception{
        networkSession.openConnection(nom);
        networkSession.closeConnection();
        assertFalse(networkSession.isOpen(), "La connexion devrait être fermée -> isOpen False");
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

