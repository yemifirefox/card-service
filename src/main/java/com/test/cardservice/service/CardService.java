package com.test.cardservice.service;

import com.test.cardservice.exception.ResourceNotFoundException;
import com.test.cardservice.model.CardVerificationResponse;
import com.test.cardservice.model.Payload;
import com.test.cardservice.utils.Utility;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    public CardVerificationResponse verify(String cardNumber){
        CardVerificationResponse verificationResponse = new CardVerificationResponse();
        Payload payload = new Payload();


        String cardScheme = Utility.getCardScheme(cardNumber);
        if(cardScheme == null || cardScheme.isEmpty()){
            throw new ResourceNotFoundException("Card Type not found");
        }
        verificationResponse.setSuccess(true);
        payload.setScheme(cardScheme);
        payload.setType("debit");
        payload.setBank("UBA");
        verificationResponse.setPayload(payload);
        return verificationResponse;
    }

    public Object statistics(long start, long limit) throws Exception{

       return Utility.getStat(start, limit);
    }
}
