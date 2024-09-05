package com.ema.authentication.signIn;

public interface SignInValidator {
    public boolean validateAccNo(String accountNo);

    public boolean validatePin(String pin);
}
