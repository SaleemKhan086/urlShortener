package com.urlshortener.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UrlMappingModel {
    private String shortUrl;
    private String longUrl;
    private int lastUpdatedAt;
    private int createdAt;
}
