package com.urlshortener.model;

import lombok.Data;

@Data
public class GenerateShortUrlRequest {
    private String longUrl;
}
