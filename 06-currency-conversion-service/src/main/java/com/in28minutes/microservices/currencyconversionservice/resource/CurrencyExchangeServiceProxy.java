package com.in28minutes.microservices.currencyconversionservice.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

////http://localhost:8000 - is the url for currency exchange service
//in this case, CURRENCY_EXCHANGE_URI:http://localhost:8000 means use the CURRENCY_EXCHANGE_URI value, if not found, revert to default value using localhost.
//Note: FeignCLient uses this url to talk to the currency exchange service
//@FeignClient(name = "currency-exchange-service", url="${CURRENCY_EXCHANGE_URI:http://localhost:8000}")
//in this case, we have take the "url" property out, FeignClient uses the naming server(eureka), discover the currency exchange service and then talk to it.
//instead of using the url as in the previous case
//IMP: note the name of the application defined in the FeignClient:"currency-exchange-service", which is the same value as defined in the
//application.properties file of currency-exchange-service. When an application registers with Eureka, it uses the application name
//to register and that's how other applications can use Eureka to discover and call it
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	///currency-exchange/from/EUR/to/INR
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);
}