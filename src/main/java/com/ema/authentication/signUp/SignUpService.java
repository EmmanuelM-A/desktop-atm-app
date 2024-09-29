package com.ema.authentication.signUp;

import com.ema.ui.signUp.AccountDetailsPage;
import com.ema.ui.signUp.PersonalDetialsPage;

public interface SignUpService extends SignUpValidator {
    public boolean signUp(PersonalDetialsPage personalDetialsPage, AccountDetailsPage accountDetailsPage, double initialBalance);
}
