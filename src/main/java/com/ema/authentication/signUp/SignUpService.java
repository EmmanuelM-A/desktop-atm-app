package com.ema.authentication.signUp;

public interface SignUpService extends SignUpValidator {
    public boolean signUp(String firstName, String lastName, String dob, String address, String phoneNumber, String accountName, String accountNumber, String sortCode, String accountType, String pin, double initialBalance);
}
