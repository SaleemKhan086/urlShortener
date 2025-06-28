package com.urlshortener.controller;

import com.urlshortener.model.GenerateShortUrlRequest;
import com.urlshortener.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GenerateShortUrlController {
    private final ShortUrlService generateShortUrlService;

    @PostMapping
    public String generateShortUrlController(@RequestBody GenerateShortUrlRequest request) {
        return generateShortUrlService.generateShortUrl(request.getLongUrl());
    }
}
