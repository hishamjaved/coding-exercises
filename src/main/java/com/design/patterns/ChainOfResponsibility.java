package com.design.patterns;

/**
 * Behavioral Patterns
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        EmailProcessor ep = new EmailProcessor();
        ep.addHandler(new BusinessMailHandler());
        ep.prevHandler.handleRequest("hisham@pepal.xyz");
    }
}


interface EmailHandler {
    //reference to the next handler in the chain
    public void setNext(EmailHandler handler);

    //handle request
    public void handleRequest(String email);
}


class BusinessMailHandler implements EmailHandler {
    private EmailHandler next;

    public void setNext(EmailHandler handler) {
        next = handler;
    }

    public void handleRequest(String email) {
        if (!email.endsWith("@pepal.xyz")) {
            next.handleRequest(email);
        } else {
            //handle request (move to correct folder)
            System.out.println("Sent business email address");
        }
    }
}


class GMailHandler implements EmailHandler {
    private EmailHandler next;

    public void setNext(EmailHandler handler) {
        next = handler;
    }

    public void handleRequest(String email) {
        if (!email.endsWith("@gmail.com")) {
            next.handleRequest(email);
        } else {
            //handle request (move to correct folder)}}}
            System.out.println("Sent gmail personal email address");
        }
    }
}


class EmailProcessor {
    //maintain a reference to the previous handler so we can add the next one
    public EmailHandler prevHandler;

    public void addHandler(EmailHandler handler) {
        if (prevHandler != null) {
            prevHandler.setNext(handler);
        }
        prevHandler = handler;
    }
}