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
                Config.getEnv("AUTHORIZE_API_LOGIN_ID"),
                Config.getEnv("AUTHORIZE_TRANSACTION_KEY"),
                25.00,
                new String[]{"6011000000000012","1225","999"},
                new String[]{"John", "Doe", "123 Main Street", "Cedar Rapids","IA","52404","USA","3191234567"},
                new String[]{"John", "Doe", "123 Main Street", "Cedar Rapids", "IA", "52404", "USA"},
                "test@test.com",
                true
        );
    }

    public static ANetApiResponse run(String apiLoginId, String transactionKey, Double amount, String[] creditCardInfo, String[] billingInfo, String[] shippingInfo, String customerEmail, boolean useBillingAsShipping) {

        // Set the request to operate in either the sandbox or production environment
        ApiOperationBase.setEnvironment(Environment.SANDBOX);

        // Create object with merchant authentication details
        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
        merchantAuthenticationType.setName(apiLoginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(creditCardInfo[0]);
        creditCard.setExpirationDate(creditCardInfo[1]);
        creditCard.setCardCode(creditCardInfo[2]);
        paymentType.setCreditCard(creditCard);

        // Set email address (optional)
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

        CustomerAddressType shippingAddress = new CustomerAddressType();
        if(!useBillingAsShipping) {
            shippingAddress.setFirstName(shippingInfo[0]);
            shippingAddress.setLastName(shippingInfo[1]);
            shippingAddress.setAddress(shippingInfo[2]);
            shippingAddress.setCity(shippingInfo[3]);
            shippingAddress.setState(shippingInfo[4]);
            shippingAddress.setZip(shippingInfo[5]);
            shippingAddress.setCountry(shippingInfo[6]);
        }

        // Create the payment transaction object
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
        txnRequest.setCustomer(customer);
        txnRequest.setBillTo(billingAddress);
        if(useBillingAsShipping) {
            txnRequest.setShipTo(billingAddress);
        } else {
            txnRequest.setShipTo(shippingAddress);
        }
        txnRequest.setAmount(new BigDecimal(amount).setScale(2, RoundingMode.CEILING));

        // Create the API request and set the parameters for this specific request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setMerchantAuthentication(merchantAuthenticationType);
        apiRequest.setTransactionRequest(txnRequest);

        // Call the controller
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();

        // Get the response
        CreateTransactionResponse response = new CreateTransactionResponse();
        response = controller.getApiResponse();

        // Parse the response to determine results
        if (response!=null) {
            // If API Response is OK, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();
                if (result.getMessages() != null) {
                    System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    System.out.println("Response Code: " + result.getResponseCode());
                    System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    System.out.println("Auth Code: " + result.getAuthCode());
                } else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            } else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                } else {
                    System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        } else {
            // Display the error code and message when response is null
            ANetApiResponse errorResponse = controller.getErrorResponse();
            System.out.println("Failed to get response");
            if (!errorResponse.getMessages().getMessage().isEmpty()) {
                System.out.println("Error: "+errorResponse.getMessages().getMessage().get(0).getCode()+" \n"+ errorResponse.getMessages().getMessage().get(0).getText());
            }
        }

        return response;
    }
}