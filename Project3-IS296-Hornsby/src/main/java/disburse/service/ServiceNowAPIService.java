package disburse.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ServiceNowAPIService {
	
	public String callServiceNow(String shortDescription) {
		WebClient.Builder webClientBuilder = WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
		.codecs(configurer -> configurer
		.defaultCodecs()
		.maxInMemorySize(16 * 1024 * 1024))
		.build());
		Map<String, String> jsonObj = new HashMap<String, String>();
		jsonObj.put("short_description", shortDescription);
		jsonObj.put("category", "inquiry");
		jsonObj.put("priority", "1");
		String responseBody = webClientBuilder.build()
		.post()
		.uri("https://dev116364.service-now.com/api/now/table/incident?sysparm_fields=number")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:weN^7kZ!xB4N".getBytes()))
		.body(Mono.just(jsonObj), Map.class)
		.retrieve().onStatus(HttpStatus::isError, clientResponse -> {
		return Mono.error(new Exception("error"));
		}).bodyToMono(String.class)
		.block();
		return responseBody;
		
	}
}
