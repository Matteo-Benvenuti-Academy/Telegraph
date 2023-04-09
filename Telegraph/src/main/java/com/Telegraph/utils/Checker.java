package com.Telegraph.utils;

import java.util.regex.Pattern;

// this class is for future check on email,username or password sintax
public class Checker {

    private static final String emailValidationRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validateUserData(String string){
        return !(findSpace(string));
    }

    private static boolean findSpace(String string){
        return string.contains(" "); 
    }

    public static boolean validateEmail(String email) {
        return Pattern.compile(emailValidationRegex)
        .matcher(email)
        .matches();
    }
}
