package net.deepnet.joshua.service;

import net.deepnet.joshua.dto.request.ConvertSimple;
import net.deepnet.joshua.dto.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ConvertSimpleService {

    final static Logger LOGGER = LoggerFactory.getLogger(ConvertSimpleService.class);

    @Value("${neospeech.endpoint}")
    private String neoSpeechEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    public Response sendRequest(final ConvertSimple convertSimple) {

        final ResponseEntity<Response> response = restTemplate.exchange(
                RequestEntity.post(URI.create(neoSpeechEndpoint)).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                        .body(convertSimple), Response.class);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("response={}", response);
        }
        return response.getBody();
    }

}
