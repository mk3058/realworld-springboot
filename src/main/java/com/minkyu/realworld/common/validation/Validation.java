package com.minkyu.realworld.common.validation;

import java.util.regex.Pattern;

public class Validation {

    private static final String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

    public static boolean email(String email) {
        return Pattern.compile(emailPattern).matcher(email).matches();
    }

}
