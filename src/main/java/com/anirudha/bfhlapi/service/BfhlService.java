package com.anirudha.bfhlapi.service;

import com.anirudha.bfhlapi.dto.RequestDto;
import com.anirudha.bfhlapi.dto.ResponseDto;

public interface BfhlService {
    ResponseDto processData(RequestDto request);
}
