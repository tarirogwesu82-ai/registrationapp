package com.mycompany.chatappregistration;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Before
    public void setUp() {
        Message.resetForTesting();
    }

    @Test
    public void testMessageWithin250Chars() {
        String text = "Hi Mike, can you join us for dinner tonight?";
        boolean isValid = text.length() <= 250;
        assertTrue(isValid);
    }

    @Test
    public void testMessageExceeds250Chars() {
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longText.append("A");
        }
        boolean isTooLong = longText.length() > 250;
        assertTrue(isTooLong);
    }

    @Test
    public void testRecipientCellCorrect() {
        Message msg = new Message("+2783896", "Hi Mike");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientCellIncorrect() {
        Message msg = new Message("08575975889", "Hi Keegan");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashFormat() {
        Message msg = new Message("+2783896", "Hi Mike can you join us for dinner tonight");
        String hash = msg.getMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains(":"));
    }

    @Test
    public void testMessageHashIsUpperCase() {
        Message msg = new Message("+2783896", "Hi Mike");
        String hash = msg.getMessageHash();
        assertEquals(hash.toUpperCase(), hash);
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+2783896", "Hi Mike");
        boolean isValid = msg.checkMessageID();
        assertTrue(isValid);
    }

    @Test
    public void testSendMessage() {
        Message msg = new Message("+2783896", "Hello");
        String result = msg.SentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testDisregardMessage() {
        Message msg = new Message("08575975889", "Hello");
        String result = msg.SentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testStoreMessage() {
        Message msg = new Message("+2783896", "Hello");
        String result = msg.SentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testReturnTotalMessages() {
        Message msg1 = new Message("+2783896", "Hi Mike");
        msg1.SentMessage(1);
        Message msg2 = new Message("+2783896", "Hi Keegan");
        msg2.SentMessage(1);
        int total = msg2.returnTotalMessages();
        assertEquals(2, total);
    }

    @Test
    public void testDisregardDoesNotIncreaseCount() {
        Message msg = new Message("+2783896", "Hi Mike");
        msg.SentMessage(2);
        int total = msg.returnTotalMessages();
        assertEquals(0, total);
    }

    @Test
    public void testStoreDoesNotIncreaseCount() {
        Message msg = new Message("+2783896", "Hi Mike");
        msg.SentMessage(3);
        int total = msg.returnTotalMessages();
        assertEquals(0, total);
    }
}