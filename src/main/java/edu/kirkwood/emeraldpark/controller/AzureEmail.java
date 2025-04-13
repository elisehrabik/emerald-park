package edu.kirkwood.emeraldpark.controller;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.implementation.models.ErrorResponseException;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import edu.kirkwood.shared.Helpers;
import edu.kirkwood.shared.Validators;
import io.github.cdimascio.dotenv.Dotenv;

public class AzureEmail {
    public static EmailClient getEmailClient() {
        String connectionString = Dotenv.load().get("AZURE_EMAIL_CONNECTION");

        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        return emailClient;
    }

    public static String sendEmail(String toEmailAddress, String subject, String bodyHTML, String replyTo) {
        EmailClient emailClient = getEmailClient();
        EmailAddress toAddress = new EmailAddress(toEmailAddress);
        String body = Helpers.html2text(bodyHTML);
        EmailMessage emailMessage = new EmailMessage()
                .setSenderAddress(Dotenv.load().get("AZURE_EMAIL_FROM"))
                .setToRecipients(Dotenv.load().get("ADMIN_EMAIL"))
                .setSubject(subject)
                .setBodyPlainText(body)
                .setBodyHtml(bodyHTML)
                .setReplyTo(new EmailAddress(replyTo));

        SyncPoller<EmailSendResult, EmailSendResult> poller = null;
        try {
            poller = emailClient.beginSend(emailMessage, null);
        } catch(ErrorResponseException e) {
            return e.getMessage();
        }
        PollResponse<EmailSendResult> result = poller.waitForCompletion();
        System.out.println("🔎 Email status: " + result.getValue().getStatus());
        System.out.println("📨 Message ID: " + result.getValue().getId());


        return "";
    }


    public static void main(String[] args) {
        // Get all parameters
        String toEmailAddress = "ellie.hrabik@gmail.com"; // Use your own email address
        String subject = "Testing";
        String bodyHTML = "<h2>This is a test email</h2><p>Testing, Testing, Testing</p>";
        String replyTo = "elise.hrabik@gmail.com";
        // Set the parameters as attribute
        

        // Validate the user inputs
        boolean error =false;
        if(toEmailAddress == null || !Validators.isValidEmail(toEmailAddress)) {
            // set error attribute
            System.out.println("Invalid email address: " + toEmailAddress);
            error = true;
        }

        if(subject == null || subject.isEmpty()) {
            // set error attribute
            System.out.println("Subject is required");
            error = true;
        }

        if(bodyHTML == null || bodyHTML.isEmpty()) {
            // set error attribute
            System.out.println("Body is required");
            error = true;
        }

        if(!error) {
            // Second thread is commented out to only send email to admin email
            //EmailThread emailThread2 = new EmailThread(toEmailAddress, subject, bodyHTML, replyTo);
            //emailThread2.start();
            EmailThread emailThread1 = new EmailThread((Dotenv.load().get("ADMIN_EMAIL")), subject, bodyHTML, replyTo);
            emailThread1.start();
            try {
                emailThread1.join();
                //emailThread2.join();
            } catch (InterruptedException e) {

            }
            String errorMessage1 = emailThread1.getErrorMessage();
            //String errorMessage2 = emailThread2.getErrorMessage(); && errorMessage2.isEmpty()
            if (errorMessage1.isEmpty()) {
                // Set a success attribute
                System.out.println("Message sent to " + (Dotenv.load().get("ADMIN_EMAIL")));
            } else {
                // Set an error attribute
                System.out.println("Message not sent to " + (Dotenv.load().get("ADMIN_EMAIL")) + " - " + errorMessage1);
            }
        }
        // Forward req/resp to a JSP

        String result = sendEmail(toEmailAddress, subject, bodyHTML, replyTo);
        System.out.println("Send result: " + result);
    }
}

