package edu.kirkwood.emeraldpark.controller;

public class EmailThread extends Thread {
    private String toEmailAddress;
    private String subject;
    private String bodyHTML;
    private String errorMessage;
    private String replyTo;

    public EmailThread(String toEmailAddress, String subject, String bodyHTML, String replyTo) {
        this.toEmailAddress = toEmailAddress;
        this.subject = subject;
        this.bodyHTML = bodyHTML;
        this.replyTo = replyTo;
    }

    public void run() {
        errorMessage = AzureEmail.sendEmail(toEmailAddress, subject, bodyHTML, replyTo);
        // TODO: Add a backup email service if an error occurs
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
