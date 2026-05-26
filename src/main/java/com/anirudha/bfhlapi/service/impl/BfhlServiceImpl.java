package com.anirudha.bfhlapi.service.impl;

import com.anirudha.bfhlapi.dto.RequestDto;
import com.anirudha.bfhlapi.dto.ResponseDto;
import com.anirudha.bfhlapi.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDto processData(RequestDto request) {
        List<String> data = request.getData();

        if (data == null || data.isEmpty()) {
            ResponseDto response = new ResponseDto();
            response.setSuccess(true);
            response.setUser_id("aniruddha_geete_26052026");
            response.setEmail("aniruddhageete@gmail.com");
            response.setRoll_number("0101CS221010");
            response.setOdd_numbers(Collections.emptyList());
            response.setEven_numbers(Collections.emptyList());
            response.setAlphabets(Collections.emptyList());
            response.setSpecial_characters(Collections.emptyList());
            response.setSum("0");
            response.setConcat_string("");
            return response;
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        int sum = 0;

        for (String item : data) {
            item = item.trim();

            if (isNumber(item)) {
                int num = Integer.parseInt(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabet(item)) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        // concat_string: concatenate all alphabets → reverse → alternating caps
        StringBuilder concat = new StringBuilder();
        for (String alpha : alphabets) {
            concat.append(alpha);
        }
        String reversed = concat.reverse().toString();

        StringBuilder alternating = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            if (i % 2 == 0) {
                alternating.append(Character.toUpperCase(reversed.charAt(i)));
            } else {
                alternating.append(Character.toLowerCase(reversed.charAt(i)));
            }
        }

        ResponseDto response = new ResponseDto();
        response.setSuccess(true);
        response.setUser_id("aniruddha_geete_26052026");
        response.setEmail("aniruddhageete@gmail.com");
        response.setRoll_number("0101CS221010");
        response.setOdd_numbers(oddNumbers);
        response.setEven_numbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecial_characters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcat_string(alternating.toString());

        return response;
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabet(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.chars().allMatch(Character::isLetter);
    }
}
