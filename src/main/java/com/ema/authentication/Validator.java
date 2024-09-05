package com.ema.authentication;

public interface Validator<T> {
    public boolean validate(T input);
}
