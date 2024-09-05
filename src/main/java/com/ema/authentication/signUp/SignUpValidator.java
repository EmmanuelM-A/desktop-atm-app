package com.ema.authentication.signUp;

public interface SignUpValidator {
    // validate name
    public boolean validateName(String firstname);

    // validate dob
    public boolean validateDob(String dob);

    // validate address
    public boolean validateAddress(String address);

    // validate phone number
    public boolean validatePhoneNumber(String phoneNumber);

    // validate account type
    public boolean validateAccountType(String accountType);

    // validate account pin
    public boolean validateAccountPin(String accountPin);
}
