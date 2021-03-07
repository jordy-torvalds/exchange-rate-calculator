package com.jordy.app.exchangeratecalculrate.currency.infra;

import com.jordy.app.exchangeratecalculrate.currency.dto.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
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
import java.nio.charset.StandardCharsets;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@Profile("prod")
public class ApiExchangeRateClient implements ExchangeRateClient{
    final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    final private String CURRENCY_LAYER_URL = "http://api.currencylayer.com/live";

    @Value("${currency-layer.access-key}")
    private String accessKey;

    @Override
    public ExchangeRateResponse retrieveExchangeRate() {
        RestTemplate restTemplate = new RestTemplate();
        //<T> ResponseEntity<T> exchange(String var1, HttpMethod var2, @Nullable HttpEntity<?> var3, Class<T> var4, Object... var5) throws RestClientException;
//        restTemplate.
        return null;
    }

    protected ResponseEntity<ExchangeRateResponse> requestToCurrencyLayer() throws UnsupportedEncodingException {
        System.out.println(Charset.forName("UTF-8"));
        final String decodedAccessKey = URLDecoder.decode(accessKey, DEFAULT_CHARSET);
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType( new MediaType("application", "json", DEFAULT_CHARSET));



        UriComponents builder = UriComponentsBuilder.fromHttpUrl(CURRENCY_LAYER_URL)
                .queryParam("access_key", decodedAccessKey)
                .queryParam("currencies","KRW,JPY,PHP")
                .build(false);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers), ExchangeRateResponse.class);
    }
}
