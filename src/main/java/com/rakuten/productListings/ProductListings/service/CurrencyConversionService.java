package com.rakuten.productListings.ProductListings.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

	public Double convertToEur(Currency convertFrom, Double amount) throws IOException, JSONException {
		HttpURLConnection conn = null;
		Double conversionRate = null;
		try {
			URL url = new URL(
					"https://free.currencyconverterapi.com/api/v6/convert?q=" + convertFrom.getCurrencyCode() + "_EUR&compact=y");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder responsebuilder = new StringBuilder();
			String response;
			while ((response = br.readLine()) != null) {

				responsebuilder.append(response);
			}
			response = responsebuilder.toString();
			JSONObject jsonObj = new JSONObject(response);
			jsonObj = jsonObj.getJSONObject(convertFrom.getCurrencyCode() + "_EUR");
			conversionRate = (Double) jsonObj.get("val");
			
			
		} 
		finally {
			conn.disconnect();
		}
		return conversionRate * amount;
	}

}
