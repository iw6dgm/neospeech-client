package net.deepnet.joshua.service;

import net.deepnet.joshua.dto.request.ConvertSimple;
import net.deepnet.joshua.dto.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ConvertSimpleService {

    final static Logger LOGGER = LoggerFactory.getLogger(ConvertSimpleService.class);

    @Value("${neospeech.endpoint}")
    private String neoSpeechEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    public Response sendRequest(final ConvertSimple convertSimple) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<ConvertSimple> httpEntity = new HttpEntity<>(convertSimple, headers);
        try {
            final URI uri = new URI(neoSpeechEndpoint);
            final ResponseEntity<Response> response = restTemplate.postForEntity(uri, httpEntity, Response.class);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("response={}", response);
            }
            return response.getBody();
        } catch (URISyntaxException e) {
            LOGGER.warn("", e);
        }
        return null;
    }

}
