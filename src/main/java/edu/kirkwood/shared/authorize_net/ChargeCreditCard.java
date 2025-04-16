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
    public static ANetApiResponse run(Double amount, String[] creditCardInfo, String[] billingInfo, String customerEmail) {
        String apiLoginId = Config.getEnv("AUTHORIZE_API_LOGIN_ID");
        String transactionKey = Config.getEnv("AUTHORIZE_TRANSACTION_KEY");
        ApiOperationBase.setEnvironment(Environment.SANDBOX);

        MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
        merchantAuthenticationType.setName(apiLoginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);

        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(creditCardInfo[0]);
        creditCard.setExpirationDate(creditCardInfo[1]);
        creditCard.setCardCode(creditCardInfo[2]);
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

        return response;
    }

}