package com.test.cardservice;

import com.test.cardservice.controller.CardServiceController;
import com.test.cardservice.model.CardVerificationResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CardServiceApplication.class)
public class CardServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CardServiceController cardServiceController;

    @Test
    public void testVerifyEndpoint() {
        //CardServiceController cardServiceController = new CardServiceController();
        ResponseEntity<CardVerificationResponse> card = cardServiceController.getCard("6011111111111117");
        logger.info("Card Details -> {}", card.getBody());
        assertEquals(true, card.getBody().isSuccess());
    }

    public void testStatisticsEndpoint() throws Exception {
        //CardServiceController cardServiceController = new CardServiceController();
        ResponseEntity<Object> stat = cardServiceController.getStat(1, 3);
        logger.info("Card Details -> {}", stat.getBody());
        assertEquals(true,  stat.getStatusCode().is2xxSuccessful());
    }
}
