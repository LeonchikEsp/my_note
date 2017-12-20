package com.epam.mynote.util;

import com.epam.mynote.domain.User;
import com.epam.mynote.exceptions.InvalidDataException;

public class Validator {
    public static boolean validId(Long Id) {
        return !(Id == null || Id < 0L);
    }

    public static boolean validUser(User user) {
        return (!(user == null || user.getName().isEmpty() || user.getName() == null));
    }


}
