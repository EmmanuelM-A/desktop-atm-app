package com.ema.authentication.signIn;

import com.ema.logic.account.Account;

/**
 * The LoginService interface provides a login method used to authorize user logins. It extends the LoginValidator
 * which provides additional validation for user inputs.
 */
public interface SignInService extends SignInValidator {
    /**
     * Executes a user login with the provided inputs.
     * @param accountNo The account number of the user attempting to login.
     * @param pin The pin associated with the account number.
     * @return True if the login is successful and false otherwise.
     */
    public Account signIn(String accountNo, String pin);
}
