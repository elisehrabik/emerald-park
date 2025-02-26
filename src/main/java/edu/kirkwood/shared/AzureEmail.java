package edu.kirkwood.shared;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;

public class AzureEmail {
    public static EmailClient getEmailClient() {
        String connectionString = Config.getEnv("AZURE_EMAIL_CONNECTION");

        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        return emailClient;
    }

    public static String sendEmail(String toEmailAddress, String subject, String bodyHTML) {
        EmailClient emailClient = getEmailClient();
        EmailAddress toAddress = new EmailAddress(toEmailAddress);
        EmailAddress replyToAddress = new EmailAddress(Config.getEnv("ADMIN_EMAIL"));
        String body = Helpers.html2text(bodyHTML);
        EmailMessage emailMessage = new EmailMessage()
                .setSenderAddress(Config.getEnv("AZURE_EMAIL_FROM"))
                .setToRecipients(toAddress)
                .setSubject(subject)
                .setReplyTo()
                .setBodyPlainText(body)
                .setBodyHtml(bodyHTML);
        SyncPoller<EmailSendResult, EmailSendResult> poller = null;
        try {
            poller = emailClient.beginSend(emailMessage, null);
        } catch(RuntimeException e) {
            return e.getMessage();
        }
        PollResponse<EmailSendResult> result = poller.waitForCompletion();

        return "";
    }
}