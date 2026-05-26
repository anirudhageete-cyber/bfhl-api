package com.anirudha.bfhlapi.controller;

import com.anirudha.bfhlapi.dto.RequestDto;
import com.anirudha.bfhlapi.dto.ResponseDto;
import com.anirudha.bfhlapi.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ResponseDto> processData(@RequestBody RequestDto request) {
        try {
            ResponseDto response = bfhlService.processData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto errorResponse = new ResponseDto();
            errorResponse.setSuccess(false);
            return ResponseEntity.ok(errorResponse);
        }
    }

    @GetMapping("/bfhl")
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }
}
