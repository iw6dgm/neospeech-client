package net.deepnet.joshua.controller;

import net.deepnet.joshua.dto.request.ConvertSimple;
import net.deepnet.joshua.dto.request.ObjectFactory;
import net.deepnet.joshua.dto.response.Response;
import net.deepnet.joshua.service.ConvertSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ConvertSimpleController {

    final static String CONVERT_SIMPLE_ENDPOINT = "/simple";
    public static final String TTS_NEOBRIDGET_DB = "TTS_NEOBRIDGET_DB";
    public static final String SIMPLE_VIEW = "simple";

    @Autowired
    private ConvertSimpleService convertSimpleService;

    @Autowired
    private ObjectFactory objectFactory;

    @RequestMapping(value = CONVERT_SIMPLE_ENDPOINT, method = RequestMethod.GET)
    public ModelAndView init(final ModelAndView modelAndView) {
        modelAndView.setViewName(SIMPLE_VIEW);
        return modelAndView;
    }

    @RequestMapping(value = CONVERT_SIMPLE_ENDPOINT, method = RequestMethod.POST)
    public ModelAndView sendConvertSimple(@RequestParam(required = true, defaultValue = TTS_NEOBRIDGET_DB) final String voice, @RequestParam(required = true) final String text, final ModelAndView modelAndView) {
        final ConvertSimple convertSimple = objectFactory.createConvertSimple();
        convertSimple.setText(text);
        convertSimple.setVoice(voice);
        final Response response = convertSimpleService.sendRequest(convertSimple);
        if (null != response) {
            final String message = String.format("Result code=%d; Result Msg=%s; Status Code=%d; Status=%s; Conversion Number=%d", response.getResultCode(), response.getResultString(), response.getStatusCode(), response.getStatus(), response.getConversionNumber());
            modelAndView.addObject("result", message);
        } else {
            modelAndView.addObject("error", "Error: response is null");
        }
        modelAndView.setViewName(SIMPLE_VIEW);
        return modelAndView;

    }

}
