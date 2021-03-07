package com.jordy.app.exchangeratecalculrate.currency.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

@Slf4j
@Component
@Profile({"prod","test"})
public class ApiExchangeRateClient implements ExchangeRateClient{
    final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    final private String CURRENCY_LAYER_URL = "http://api.currencylayer.com/live";

    @Value("${currency-layer.access-key}")
    private String accessKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ExchangeRateResponse retrieveExchangeRate() throws UnsupportedEncodingException{
        final ResponseEntity<String> respEntity = requestToCurrencyLayer();
        final String body = respEntity.getBody();
        ExchangeRateResponse exchangeRateResponse;

        try {
            exchangeRateResponse = objectMapper.readValue(body, ExchangeRateResponse.class);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
            exchangeRateResponse = new ExchangeRateResponse();
        }
        return exchangeRateResponse;
    }

    protected ResponseEntity<String> requestToCurrencyLayer() throws UnsupportedEncodingException {
        final String decodedAccessKey = URLDecoder.decode(accessKey, DEFAULT_CHARSET);
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType( new MediaType("application", "json", DEFAULT_CHARSET));
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(CURRENCY_LAYER_URL)
                .queryParam("access_key", decodedAccessKey)
                .queryParam("currencies","KRW,JPY,PHP")
                .build(false);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);
    }
}
