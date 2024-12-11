package com.devjr.recaptcha.services;

import com.devjr.recaptcha.controllers.RecaptchaResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaServiceImpl implements RecaptchaService{

    private static final String GOOGLE_RECAPTCHA_ENDPOINT="https://www.google.com/recaptcha/api/siteverify";

    private final String RECAPTCHA_SECRET = "6LcSCZgqAAAAAMLrSu5m9vJUa1BMe8hOm24bZp3Z";

    @Override
    public boolean validateRecaptcha(String captcha) {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String,String> request= new LinkedMultiValueMap<>();
        request.add("secret",RECAPTCHA_SECRET);
        request.add("response",captcha);

        RecaptchaResponse apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT,request,RecaptchaResponse.class);

        if(apiResponse == null){
            return  false;
        }

        return Boolean.TRUE.equals(apiResponse.getSuccess());
    }
}
