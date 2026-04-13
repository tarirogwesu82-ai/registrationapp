package com.mycompany.chatappregistration;

/**
 * Registration class handles user account creation.
 * Author:Student 
 * Date: 2026
 * /**
 * Registration - This class handles the registration of a new user.
 * It validates the username, password complexity, and cell phone number.
 * It returns appropriate messages based on whether validation passes or fails.
 *
 * Regular expressions used for cell phone validation:
 * Oracle. 2024. Java SE 17 Documentation: Pattern.
 * [Online]. Available at: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * [Accessed: 13 April 2026]
 *
 * Character class used for password complexity checking:
 * Oracle. 2024. Java SE 17 Documentation: Character.
 * [Online]. Available at: https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html
 * [Accessed: 13 April 2026]
 *
 * Author: ST10498762
 * Student Number: ST10498762
 * Date: 13 April 2026
 
 */
@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class Registration {

    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    public Registration(String firstName, String lastName,
                        String username, String password,
                        String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) return false;

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null) return false;
        String regex = "^\\+27[0-9]{9}$";
        return cellPhoneNumber.matches(regex);
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure "
                 + "that your username contains an underscore and is no "
                 + "more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure "
                 + "that the password contains at least eight characters, "
                 + "a capital letter, a number, and a special character.";
        }
        return "Welcome " + firstName + " " + lastName
             + ", it is great to see you.";
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}