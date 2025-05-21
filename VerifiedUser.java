public class VerifiedUser extends User {
    // attributes

    public enum VerificationMethod {
        Password,
        MFA,
        Email,
        OAuth,
        SMS,
        CAPTCHA
    }

    public boolean verify(VerificationMethod verificationMethod) {
        return true;
    }

    // constructor
    public VerifiedUser(String username, VerificationMethod verificationMethod) throws Exception {
        super(username);
        if (!verify(verificationMethod)) {
            throw new Exception("Verification failed for user: " + username);
        }
    }
}