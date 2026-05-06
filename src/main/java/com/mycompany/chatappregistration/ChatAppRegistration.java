package com.mycompany.chatappregistration;

import java.util.Scanner;

public class ChatAppRegistration {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.println("  ==================================================");
        System.out.println("   QUICKCHAT - REGISTRATION");
        System.out.println("  ==================================================");

        // Username input
        String username = "";
        boolean usernameValid = false;
        
        while (usernameValid == false) {
            System.out.println();
            System.out.println("  USERNAME REQUIREMENTS:");
            System.out.println("  - Must contain an underscore ( _ )");
            System.out.println("  - Must be 5 characters or less");
            System.out.println();
            System.out.print("  Enter username: ");
            username = scan.nextLine();
            
            Registration tempReg = new Registration("", "", username, "Temp@1234", "+27000000000");
            
            if (tempReg.checkUserName()) {
                System.out.println("  [OK] Username successfully captured.");
                usernameValid = true;
            } else {
                System.out.println("  [!] Username is not correctly formatted.");
                System.out.println("  [!] Please ensure it contains an underscore");
                System.out.println("  [!] and is no more than five characters in length.");
            }
        }

        // Password input
        String password = "";
        boolean passwordValid = false;
        
        while (passwordValid == false) {
            System.out.println();
            System.out.println("  PASSWORD REQUIREMENTS:");
            System.out.println("  - At least 8 characters long");
            System.out.println("  - Must contain a capital letter");
            System.out.println("  - Must contain a number");
            System.out.println("  - Must contain a special character (e.g. @ # !)");
            System.out.println();
            System.out.print("  Enter password: ");
            password = scan.nextLine();
            
            Registration tempReg = new Registration("", "", "a_1", password, "+27000000000");
            
            if (tempReg.checkPasswordComplexity()) {
                System.out.println("  [OK] Password successfully captured.");
                passwordValid = true;
            } else {
                System.out.println("  [!] Password is not correctly formatted.");
                System.out.println("  [!] Please ensure it meets all the requirements above.");
            }
        }

        // Cell phone input
        String cellPhone = "";
        boolean cellValid = false;
        
        while (cellValid == false) {
            System.out.println();
            System.out.println("  CELL NUMBER REQUIREMENTS:");
            System.out.println("  - Must include an international code");
            System.out.println("  - Example: +27838968976");
            System.out.println();
            System.out.print("  Enter cell phone number: ");
            cellPhone = scan.nextLine();
            
            Registration tempReg = new Registration("", "", "a_1", "Temp@1234", cellPhone);
            
            if (tempReg.checkCellPhoneNumber()) {
                System.out.println("  [OK] Cell number successfully captured.");
                cellValid = true;
            } else {
                System.out.println("  [!] Cell number is incorrectly formatted.");
                System.out.println("  [!] Please ensure it includes an international code.");
            }
        }

        // First name and last name
        System.out.println();
        System.out.print("  Enter first name: ");
        String firstName = scan.nextLine();
        
        System.out.print("  Enter last name:  ");
        String lastName = scan.nextLine();

        // Register the user
        Registration reg = new Registration(firstName, lastName, username, password, cellPhone);
        System.out.println();
        System.out.println("  --------------------------------------------------");
        System.out.println("  " + reg.registerUser());
        System.out.println("  --------------------------------------------------");

        // LOGIN
        System.out.println();
        System.out.println("  ==================================================");
        System.out.println("   QUICKCHAT - LOGIN");
        System.out.println("  ==================================================");
        
        boolean loginSuccess = false;
        
        while (loginSuccess == false) {
            System.out.println();
            System.out.print("  Enter username: ");
            String loginUsername = scan.nextLine();
            
            System.out.print("  Enter password: ");
            String loginPassword = scan.nextLine();
            
            Login login = new Login(loginUsername, loginPassword, reg);
            
            System.out.println();
            
            if (login.loginUser()) {
                System.out.println("  [OK] " + login.returnLoginStatus());
                loginSuccess = true;
            } else {
                System.out.println("  [!] Username or password incorrect, please try again.");
            }
        }

        // WELCOME AND MESSAGE COUNT
        System.out.println();
        System.out.println("  --------------------------------------------------");
        System.out.println("        Welcome to QuickChat.");
        System.out.println("  --------------------------------------------------");
        System.out.println();
        
        int totalMessagesAllowed = 0;
        boolean countValid = false;
        
        while (countValid == false) {
            System.out.print("  How many messages do you wish to send this session? ");
            String countInput = scan.nextLine();
            
            try {
                totalMessagesAllowed = Integer.parseInt(countInput.trim());
                if (totalMessagesAllowed > 0) {
                    countValid = true;
                    System.out.println();
                    System.out.println("  [OK] You may send " + totalMessagesAllowed + " message(s) this session.");
                } else {
                    System.out.println("  [!] Please enter a number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a whole number.");
            }
        }

        int sentCount = 0;
        Message lastMsg = null;
        boolean running = true;

        // MAIN MENU LOOP
        while (running == true) {
            System.out.println();
            System.out.println("  --------------------------------------------------");
            System.out.println("               MAIN MENU");
            System.out.println("  --------------------------------------------------");
            System.out.println();
            System.out.println("   [1]  Send Messages");
            System.out.println("   [2]  Show Recently Sent Messages");
            System.out.println("   [3]  View All Sent Messages");
            System.out.println("   [4]  Search Messages by Recipient");
            System.out.println("   [5]  View Message Statistics");
            System.out.println("   [6]  Quit");
            System.out.println();
            System.out.println("  --------------------------------------------------");
            System.out.print("   Enter your choice: ");
            String menuChoice = scan.nextLine().trim();
            
            switch (menuChoice) {
                case "1":
                    if (sentCount >= totalMessagesAllowed) {
                        System.out.println();
                        System.out.println("  [!] You have already sent all " + totalMessagesAllowed + " message(s) for this session.");
                        break;
                    }
                    
                    while (sentCount < totalMessagesAllowed) {
                        System.out.println();
                        System.out.println("  --------------------------------------------------");
                        System.out.println("   SENDING MESSAGE " + (sentCount + 1) + " OF " + totalMessagesAllowed);
                        System.out.println("  --------------------------------------------------");
                        
                        String recipientCell = "";
                        boolean recipientValid = false;
                        
                        while (recipientValid == false) {
                            System.out.println();
                            System.out.println("  RECIPIENT REQUIREMENTS:");
                            System.out.println("  - Must start with + (international code)");
                            System.out.println("  - Must be 10 characters or less");
                            System.out.println("  - Example: +2783896");
                            System.out.println();
                            System.out.print("  Enter recipient cell number: ");
                            recipientCell = scan.nextLine();
                            
                            Message tempMsg = new Message(recipientCell, "placeholder");
                            String cellCheck = tempMsg.checkRecipientCell();
                            
                            if (cellCheck.equals("Cell phone number successfully captured.")) {
                                System.out.println("  [OK] " + cellCheck);
                                recipientValid = true;
                            } else {
                                System.out.println("  [!] Cell number is incorrectly formatted.");
                            }
                        }
                        
                        String messageText = "";
                        boolean messageValid = false;
                        
                        while (messageValid == false) {
                            System.out.println();
                            System.out.println("  MESSAGE REQUIREMENTS:");
                            System.out.println("  - Must be 250 characters or less");
                            System.out.println();
                            System.out.print("  Enter your message: ");
                            messageText = scan.nextLine();
                            
                            if (messageText.length() <= 250) {
                                System.out.println("  [OK] Message ready to send.");
                                messageValid = true;
                            } else {
                                int over = messageText.length() - 250;
                                System.out.println("  [!] Message exceeds 250 characters by " + over + ".");
                            }
                        }
                        
                        Message msg = new Message(recipientCell, messageText);
                        
                        if (msg.checkMessageID() == false) {
                            System.out.println("  [!] Message ID error. Please try again.");
                            continue;
                        }
                        
                        System.out.println();
                        System.out.println("  --------------------------------------------------");
                        System.out.println("   MESSAGE GENERATED SUCCESSFULLY");
                        System.out.println("  --------------------------------------------------");
                        System.out.println("   Message ID   : " + msg.getMessageID());
                        System.out.println("   Message Hash : " + msg.getMessageHash());
                        System.out.println("  --------------------------------------------------");
                        
                        System.out.println();
                        System.out.println("   What would you like to do with this message?");
                        System.out.println();
                        System.out.println("   [1]  Send Message");
                        System.out.println("   [2]  Disregard Message");
                        System.out.println("   [3]  Store Message to send later");
                        System.out.println();
                        System.out.print("   Enter your choice: ");
                        
                        String actionInput = scan.nextLine().trim();
                        int actionChoice = 0;
                        
                        try {
                            actionChoice = Integer.parseInt(actionInput);
                        } catch (NumberFormatException e) {
                            System.out.println("  [!] Invalid input. Treating as Disregard.");
                            actionChoice = 2;
                        }
                        
                        String result = msg.SentMessage(actionChoice);
                        
                        System.out.println();
                        
                        switch (actionChoice) {
                            case 1:
                                sentCount = sentCount + 1;
                                lastMsg = msg;
                                System.out.println("  --------------------------------------------------");
                                System.out.println("   MESSAGE SENT SUCCESSFULLY");
                                System.out.println("  --------------------------------------------------");
                                System.out.println("   Message ID   : " + msg.getMessageID());
                                System.out.println("   Message Hash : " + msg.getMessageHash());
                                System.out.println("   Recipient    : " + msg.getRecipientCell());
                                System.out.println("   Message      : " + msg.getMessage());
                                System.out.println("  --------------------------------------------------");
                                System.out.println("  [OK] " + result);
                                break;
                            case 2:
                                System.out.println("  --------------------------------------------------");
                                System.out.println("   MESSAGE DISREGARDED");
                                System.out.println("  --------------------------------------------------");
                                System.out.println("   " + result);
                                System.out.println("   You may enter a new message.");
                                System.out.println("  --------------------------------------------------");
                                break;
                            case 3:
                                System.out.println("  --------------------------------------------------");
                                System.out.println("   MESSAGE STORED");
                                System.out.println("  --------------------------------------------------");
                                System.out.println("  [OK] " + result);
                                System.out.println("   Saved to: messages.json");
                                System.out.println("   You may enter a new message.");
                                System.out.println("  --------------------------------------------------");
                                break;
                            default:
                                System.out.println("  [!] Unknown option. Message was not sent.");
                                break;
                        }
                    }
                    
                    if (sentCount >= totalMessagesAllowed) {
                        System.out.println();
                        System.out.println("  --------------------------------------------------");
                        System.out.println("   All " + totalMessagesAllowed + " message(s) for this session have been sent.");
                        System.out.println("  --------------------------------------------------");
                    }
                    break;
                    
                case "2":
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    System.out.println("   RECENTLY SENT MESSAGES");
                    System.out.println("  --------------------------------------------------");
                    System.out.println("   Coming Soon.");
                    System.out.println("  --------------------------------------------------");
                    break;
                    
                case "3":
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    System.out.println("   ALL SENT MESSAGES");
                    System.out.println("  --------------------------------------------------");
                    if (lastMsg != null) {
                        System.out.println();
                        System.out.println(lastMsg.printMessages());
                        System.out.println();
                    } else {
                        System.out.println("   No messages have been sent yet.");
                    }
                    System.out.println("  --------------------------------------------------");
                    break;
                    
                case "4":
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    System.out.println("   SEARCH MESSAGES BY RECIPIENT");
                    System.out.println("  --------------------------------------------------");
                    System.out.println();
                    System.out.print("   Enter recipient cell number to search: ");
                    String searchNumber = scan.nextLine().trim();
                    System.out.println();
                    System.out.println(Message.searchByRecipient(searchNumber));
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    break;
                    
                case "5":
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    System.out.println("   MESSAGE STATISTICS");
                    System.out.println("  --------------------------------------------------");
                    int totalSent = 0;
                    if (lastMsg != null) {
                        totalSent = lastMsg.returnTotalMessages();
                    }
                    int remaining = totalMessagesAllowed - sentCount;
                    System.out.println();
                    System.out.println("   Messages sent this session : " + totalSent);
                    System.out.println("   Messages remaining         : " + remaining);
                    System.out.println("   Total allowed this session : " + totalMessagesAllowed);
                    if (lastMsg != null) {
                        System.out.println();
                        System.out.println("   Longest message sent:");
                        System.out.println("   " + Message.getLongestMessage());
                    }
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    break;
                    
                case "6":
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    System.out.println("   SESSION SUMMARY");
                    System.out.println("  --------------------------------------------------");
                    int total = 0;
                    if (lastMsg != null) {
                        total = lastMsg.returnTotalMessages();
                    }
                    System.out.println();
                    System.out.println("   Total messages sent: " + total);
                    System.out.println();
                    System.out.println("  --------------------------------------------------");
                    System.out.println("   Thank you for using QuickChat. Goodbye!");
                    System.out.println("  --------------------------------------------------");
                    running = false;
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("  [!] Invalid option. Please choose a number from 1 to 6.");
                    break;
            }
        }
        
        
    }
}