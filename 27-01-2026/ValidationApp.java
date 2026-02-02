// Custom Checked Exception for Username
class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

// Custom Checked Exception for Email
class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

// Custom Unchecked Exception for Age
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Validation Logic Class
class Validator {

    // Checked Exception → uses throws
    public static void validateUsername(String username)
            throws InvalidUsernameException {

        if (username == null || username.length() < 5) {
            throw new InvalidUsernameException(
                "Username must be at least 5 characters long");
        }
    }

    // Unchecked Exception → no throws needed
    public static void validateAge(int age) {

        if (age < 18 || age > 60) {
            throw new InvalidAgeException(
                "Age must be between 18 and 60");
        }
    }

    // Checked Exception → uses throws
    public static void validateEmail(String email)
            throws InvalidEmailException {

        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException(
                "Email format is invalid");
        }
    }
}

// Main Class
public class ValidationApp {

    public static void main(String[] args) {

        try {
            System.out.println("Validation Started...");

            Validator.validateUsername("Joy");          // Invalid username
            Validator.validateAge(25);                  // Valid age
            Validator.validateEmail("joygmail.com");    // Invalid email

            System.out.println("All validations passed!");

        } catch (InvalidUsernameException e) {
            System.out.println("Username Error: " + e.getMessage());

        } catch (InvalidEmailException e) {
            System.out.println("Email Error: " + e.getMessage());

        } catch (InvalidAgeException e) {
            System.out.println("Age Error: " + e.getMessage());

        } finally {
            // Logging validation completion
            System.out.println("Validation Completed (Logged)");
        }
    }
}
