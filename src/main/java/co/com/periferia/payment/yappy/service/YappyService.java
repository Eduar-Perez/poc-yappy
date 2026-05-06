package co.com.periferia.payment.yappy.service;

import java.io.IOException;


public interface YappyService {

	String getAutorizathionToken() throws IOException, InterruptedException;
	String createOrdenPayment(String token, String orderId, double total) throws IOException, InterruptedException;

}
