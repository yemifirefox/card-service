package com.test.cardservice.config;

import com.test.cardservice.exception.ResourceNotFoundException;
import com.test.cardservice.utils.Utility;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        final Map<String, String> pathVariables = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if(!pathVariables.isEmpty()){

            String cardNumber = pathVariables.get("cardNumber");

            boolean cardValidity = Utility.isCardValid(new Long(cardNumber));
            if(!cardValidity){
                throw new ResourceNotFoundException("Card is invalid");
            }

            Utility.putInCache(cardNumber);
        }
        return true;
    }
}
