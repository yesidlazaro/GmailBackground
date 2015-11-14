package com.creativityapps.gmailbackgroundlibrary.exceptions;

/**
 * Created by Yesid Lazaro on 11/14/15.
 */
public class NotNetworkException extends RuntimeException {


    public NotNetworkException() {
        super("NotNetworkException, you need internet connection to send the email");
    }

    public NotNetworkException(String detailMessage) {
        super(detailMessage);
    }
}
