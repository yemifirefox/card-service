package com.test.cardservice.utils;

import com.test.cardservice.exception.ResourceNotFoundException;
import enums.CreditCardType;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Utility {

    private final String cardRegex = "^(?:(?<Visa>4\\d{3})|(?<MasterCard>5[1-5]\\d{2})|(?<Discover>6011)|(?<DinersClub>(?:3[68]\\d{2})|(?:30[0-5]\\d))|(?<Amex>3[47]\\d{2}))([ -]?)(?(DinersClub)(?:\\d{6}\\1\\d{4})|(?(Amex)(?:\\d{6}\\1\\d{5}) |(?:\\d{4}\\1\\d{4}\\1\\d{4})))$";
    private static Map<String, Integer> payload = new HashMap<>();

    public static final String AMERICAN_EXPRESS = "American Express";
    public static final String DISCOVER = "Discover";
    public static final String JCB = "JCB";
    public static final String DINERS_CLUB = "Diners Club";
    public static final String VISA = "Visa";
    public static final String MASTERCARD = "MasterCard";
    public static final String UNKNOWN = "Unknown";


    public static final String[] PREFIXES_AMERICAN_EXPRESS = {"34", "37"};
    public static final String[] PREFIXES_DISCOVER = {"60", "62", "64", "65"};
    public static final String[] PREFIXES_JCB = {"35"};
    public static final String[] PREFIXES_DINERS_CLUB = {"300", "301", "302", "303", "304", "305", "309", "36", "38", "39"};
    public static final String[] PREFIXES_VISA = {"4"};
    public static final String[] PREFIXES_MASTERCARD = {
            "2221", "2222", "2223", "2224", "2225", "2226", "2227", "2228", "2229",
            "223", "224", "225", "226", "227", "228", "229",
            "23", "24", "25", "26",
            "270", "271", "2720",
            "50", "51", "52", "53", "54", "55"
    };

    public static boolean hasAnyPrefix(String number, String... prefixes) {
        if (number == null) {
            return false;
        }
        for (String prefix : prefixes) {
            if (number.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static String getCardScheme(String number) {

        String evaluatedType;
        if (Utility.hasAnyPrefix(number, PREFIXES_AMERICAN_EXPRESS)) {
            evaluatedType = AMERICAN_EXPRESS;
        } else if (Utility.hasAnyPrefix(number, PREFIXES_DISCOVER)) {
            evaluatedType = DISCOVER;
        } else if (Utility.hasAnyPrefix(number, PREFIXES_JCB)) {
            evaluatedType = JCB;
        } else if (Utility.hasAnyPrefix(number, PREFIXES_DINERS_CLUB)) {
            evaluatedType = DINERS_CLUB;
        } else if (Utility.hasAnyPrefix(number, PREFIXES_VISA)) {
            evaluatedType = VISA;
        } else if (Utility.hasAnyPrefix(number, PREFIXES_MASTERCARD)) {
            evaluatedType = MASTERCARD;
        } else {
            evaluatedType = UNKNOWN;
        }
        return evaluatedType;
    }

    public static boolean isCardValid(long num){

        double digit = 0;
        int sum = 0;
        int n = 1;
        int i = 0;


        while (num > 0) {
            digit = num % 10;
            num = num / 10;

            System.out.println(n + " digit is : " + digit);

            if (i % 2 != 0 ) {
                digit *= 2;
            }

            System.out.println(n + " digit is : " + digit);

            if (digit > 9) {
                digit = (digit % 10) + 1;
            }
            else
                digit *= 1;

            System.out.println(n + " digit is : " + digit);

            sum += digit;
            n++;
            i++;
        }

        if(sum % 10 == 0) {
            return true;
        }
        else
            return false;
    }

    public static void putInCache(String cardNumber) {

        if(payload.containsKey(cardNumber)){
            payload.put(cardNumber, payload.get(cardNumber) + 1);
        }else{
            payload.put(cardNumber, 1);
        }
    }

    public static Object getStat(long start, long limit) throws Exception{

        if(payload.isEmpty()){
            throw new ResourceNotFoundException("No Hit found in Database");
        }
        Map<String, Object> jsonObject = new HashMap<>();
        //JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("start", start);
        jsonObject.put("limit", limit);
        jsonObject.put("size", payload.size());
        jsonObject.put("payload", payload);
        return jsonObject;
    }

}
