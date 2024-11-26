package samples.TokenManagement.Customer;

import java.lang.invoke.MethodHandles;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.cybersource.authsdk.core.MerchantConfig;

import Api.*;
import Data.Configuration;
import Invokers.ApiClient;
import Invokers.ApiException;
import Model.*;

public class UpdateCustomersDefaultPaymentInstrument {
	private static String responseCode = null;
	private static String status = null;
	private static Properties merchantProp;

	public static void WriteLogAudit(int status) {
		String filename = MethodHandles.lookup().lookupClass().getSimpleName();
		System.out.println("[Sample Code Testing] [" + filename + "] " + status);
	}

	public static void main(String args[]) throws Exception {
		// Accept required parameters from args[] and pass to run.
		run();
	}
	public static PatchCustomerRequest run() {
		String customerTokenId = "AB695DA801DD1BB6E05341588E0A3BDC";
		
		PatchCustomerRequest requestObj = new PatchCustomerRequest();

		Tmsv2customersDefaultPaymentInstrument defaultPaymentInstrument = new Tmsv2customersDefaultPaymentInstrument();
		defaultPaymentInstrument.id("AB6A54B982A6FCB6E05341588E0A3935");
		requestObj.defaultPaymentInstrument(defaultPaymentInstrument);

		PatchCustomerRequest result = null;
		try {
			merchantProp = Configuration.getMerchantDetails();
			ApiClient apiClient = new ApiClient();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			apiClient.merchantConfig = merchantConfig;

			CustomerApi apiInstance = new CustomerApi(apiClient);
			result = apiInstance.patchCustomer(customerTokenId, requestObj, null, null);

			responseCode = apiClient.responseCode;
			status = apiClient.status;
			System.out.println("ResponseCode :" + responseCode);
			System.out.println("ResponseMessage :" + status);
			System.out.println(result);
			WriteLogAudit(Integer.parseInt(responseCode));
		} catch (ApiException e) {
			e.printStackTrace();
			WriteLogAudit(e.getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
