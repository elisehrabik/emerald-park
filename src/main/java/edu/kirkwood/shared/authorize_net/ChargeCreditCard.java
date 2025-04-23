package edu.kirkwood.shared.authorize_net;

import java.math.BigDecimal;
import java.math.RoundingMode;

import edu.kirkwood.shared.Config;
import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.CreateTransactionController;
import net.authorize.api.controller.base.ApiOperationBase;

public class ChargeCreditCard {
    public static void main(String[] args) {
        run(
                25.00,
                new String[]{"6011000000000012","1225","999"},
                new String[]{"John", "Doe", "123 Main Street", "Cedar Rapids","IA","52404","USA","3191234567"},
                "test@test.com"
        );
    }

    public static String run(Double amount, String[] creditCardInfo, String[] billingInfo, String customerEmail) {
        String apiLoginId = Config.getEnv("AUTHORIZE_API_LOGIN_ID");
        String transactionKey = Config.getEnv("AUTHORIZE_TRANSACTION_KEY");
        ApiOperationBase.setEnvironment(Environment.SANDBOX);

        MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
        merchantAuthenticationType.setName(apiLoginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);

        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(creditCardInfo[0]);
        creditCard.setExpirationDate(creditCardInfo[1]);
        creditCard.setCardCode(creditCardInfo[2]);

        PaymentType paymentType = new PaymentType();
        paymentType.setCreditCard(creditCard);

        CustomerDataType customer = new CustomerDataType();
        customer.setEmail(customerEmail);

        CustomerAddressType billingAddress = new CustomerAddressType();
        billingAddress.setFirstName(billingInfo[0]);
        billingAddress.setLastName(billingInfo[1]);
        billingAddress.setAddress(billingInfo[2]);
        billingAddress.setCity(billingInfo[3]);
        billingAddress.setState(billingInfo[4]);
        billingAddress.setZip(billingInfo[5]);
        billingAddress.setCountry(billingInfo[6]);
        billingAddress.setPhoneNumber(billingInfo[7]);

        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
        txnRequest.setCustomer(customer);
        txnRequest.setBillTo(billingAddress);
        txnRequest.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.CEILING));

        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setMerchantAuthentication(merchantAuthenticationType);
        apiRequest.setTransactionRequest(txnRequest);

        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();

        CreateTransactionResponse response = controller.getApiResponse();

        System.out.println("Authorize.net response object: " + response);

        if (response != null && response.getMessages() != null) {
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();
                if (result != null && result.getMessages() != null) {
                    return "Successfully received donation, thank you!";
                } else if (result != null && result.getErrors() != null && !result.getErrors().getError().isEmpty()) {
                    return "Transaction error: " + result.getErrors().getError().get(0).getErrorText();
                } else {
                    return "Transaction succeeded but no message was returned.";
                }
            } else {
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    return "Transaction declined: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText();
                } else if (!response.getMessages().getMessage().isEmpty()) {
                    return "Transaction failed: " + response.getMessages().getMessage().get(0).getText();
                } else {
                    return "Transaction failed with no additional details.";
                }
            }
        }

        ANetApiResponse errorResponse = controller.getErrorResponse();
        if (errorResponse != null && errorResponse.getMessages() != null && !errorResponse.getMessages().getMessage().isEmpty()) {
            String errorCode = errorResponse.getMessages().getMessage().get(0).getCode();
            String errorMsg = errorResponse.getMessages().getMessage().get(0).getText();
            System.out.println("Authorize.net error code: " + errorCode + ", message: " + errorMsg);
            return "Payment failed: " + errorMsg;
        }

        System.out.println("Authorize.net returned a null or empty error response.");
        return "Payment failed due to an unknown issue. Please try again later.";
    }
}