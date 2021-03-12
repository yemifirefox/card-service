package com.test.cardservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cardservice.model.CardVerificationResponse;
import com.test.cardservice.service.CardService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/card-scheme")
public class CardServiceController {

    @Autowired
    private CardService cardService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/verify/{cardNumber}", produces={"application/json"})
    public ResponseEntity<CardVerificationResponse> getCard(@PathVariable String cardNumber){
        if(cardNumber == null || cardNumber.isEmpty()){
            //return new ResponseEntity<>(null);
            throw new RuntimeException("Card Number can not be empty");
        }
        return new ResponseEntity<>(cardService.verify(cardNumber), HttpStatus.OK);
    }

    @GetMapping(value = "/stats", produces={"application/json"})
    public ResponseEntity<Object> getStat(@RequestParam(required=true) long start,
                                              @RequestParam(required = true) long limit) throws Exception {
       /* if((start == null || start.isEmpty()) && (limit == null || limit.isEmpty())){
            return new ResponseEntity<>(null);
        }*/
        Object response = cardService.statistics(start, limit);

        return new ResponseEntity<>(objectMapper.writeValueAsBytes(response), HttpStatus.OK);
    }



}
