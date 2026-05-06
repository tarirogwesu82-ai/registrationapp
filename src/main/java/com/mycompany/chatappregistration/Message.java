package com.mycompany.chatappregistration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Message {

    private final String messageID;
    private final String recipientCell;
    private final String message;
    private String messageHash;

    private static int numMessagesSent = 0;
    private static final ArrayList<String> sentMessages = new ArrayList<>();
    private static final ArrayList<String> storedMessages = new ArrayList<>();

    public Message(String recipientCell, String message) {
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash(numMessagesSent + 1);
    }

    private String generateMessageID() {
        Random random = new Random();
        long id = (long)(random.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        if (messageID == null) {
            return false;
        }
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipientCell == null || recipientCell.length() > 10 || !recipientCell.startsWith("+")) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        return "Cell phone number successfully captured.";
    }

    public String createMessageHash(int messageNumber) {
        String idPrefix = messageID.substring(0, 2);
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");
        String hash = (idPrefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
        this.messageHash = hash;
        return hash;
    }

    public String createMessageHash() {
        return createMessageHash(numMessagesSent + 1);
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                numMessagesSent++;
                sentMessages.add("Message ID: " + messageID + " | Hash: " + messageHash + " | Recipient: " + recipientCell + " | Message: " + message);
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option. Please choose 1, 2, or 3.";
        }
    }

    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        String output = "=== Sent Messages ===\n";
        for (int i = 0; i < sentMessages.size(); i++) {
            output = output + "Message " + (i + 1) + ": " + sentMessages.get(i) + "\n";
        }
        return output.trim();
    }

    public int returnTotalMessages() {
        return numMessagesSent;
    }

    public String storeMessage() {
        String json = "{\"messageID\": \"" + messageID + "\", \"messageHash\": \"" + messageHash + "\", \"recipient\": \"" + recipientCell + "\", \"message\": \"" + message + "\", \"numMessagesSent\": " + numMessagesSent + "}";
        try (FileWriter fw = new FileWriter("messages.json", true)) {
            fw.write(json + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Could not save message to file: " + e.getMessage());
        }
        storedMessages.add(json);
        return "Message successfully stored.";
    }

    public static String searchByRecipient(String recipient) {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        String result = "";
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).contains(recipient)) {
                result = result + sentMessages.get(i) + "\n";
            }
        }
        if (result.equals("")) {
            return "No messages found for recipient: " + recipient;
        }
        return result.trim();
    }

    public static String getLongestMessage() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent yet.";
        }
        String longest = "";
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).length() > longest.length()) {
                longest = sentMessages.get(i);
            }
        }
        return longest;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessage() { return message; }

    public static void resetForTesting() {
        numMessagesSent = 0;
        sentMessages.clear();
        storedMessages.clear();
    }
}