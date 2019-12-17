package com.thoughtworks.exception;

import static com.thoughtworks.exception.ErrorCode.USER_NOT_FOUND_MESSAGE;

public class UserNotFoundException extends BaseException{

    public UserNotFoundException() {
        super(USER_NOT_FOUND_MESSAGE);
    }
}
