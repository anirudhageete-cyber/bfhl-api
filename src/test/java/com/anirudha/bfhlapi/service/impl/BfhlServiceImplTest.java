package com.anirudha.bfhlapi.service.impl;

import com.anirudha.bfhlapi.dto.RequestDto;
import com.anirudha.bfhlapi.dto.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceImplTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
    }

    // Example A: {"data": ["a", "1", "334", "4", "R", "$"]}
    @Test
    void testExampleA() {
        RequestDto request = new RequestDto();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1"), response.getOdd_numbers());
        assertEquals(List.of("334", "4"), response.getEven_numbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecial_characters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcat_string());
    }

    // Example B: {"data": ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]}
    @Test
    void testExampleB() {
        RequestDto request = new RequestDto();
        request.setData(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOdd_numbers());
        assertEquals(List.of("2", "4", "92"), response.getEven_numbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecial_characters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcat_string());
    }

    // Example C: {"data": ["A", "ABCD", "DOE"]}
    @Test
    void testExampleC() {
        RequestDto request = new RequestDto();
        request.setData(Arrays.asList("A", "ABCD", "DOE"));

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOdd_numbers().isEmpty());
        assertTrue(response.getEven_numbers().isEmpty());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecial_characters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcat_string());
    }

    @Test
    void testEmptyData() {
        RequestDto request = new RequestDto();
        request.setData(List.of());

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOdd_numbers().isEmpty());
        assertTrue(response.getEven_numbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecial_characters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcat_string());
    }

    @Test
    void testNullData() {
        RequestDto request = new RequestDto();
        request.setData(null);

        ResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("0", response.getSum());
    }

    @Test
    void testUserIdFormat() {
        RequestDto request = new RequestDto();
        request.setData(List.of("1"));

        ResponseDto response = bfhlService.processData(request);

        assertEquals("aniruddha_geete_26052026", response.getUser_id());
        assertNotNull(response.getEmail());
        assertNotNull(response.getRoll_number());
    }
}
