package com.urlshortener.service;

import com.urlshortener.db.UrlMappingModel;
import com.urlshortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.urlshortener.constants.Constants.SOURCE_STRING;

@Service
@RequiredArgsConstructor
public class ShortUrlService {
    private final ShortUrlRepository repository;

    public String generateShortUrl(String longUrl) {
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
        for (int i=0;i<6;i++){
            int index = random.nextInt(52);
            shortUrl.append(SOURCE_STRING.charAt(index));
        }
        // TODO: add collision validation

        repository.saveItem(createUrlMappingModel(longUrl, shortUrl.toString()));

        return shortUrl.toString();
    }

    public String getLongUrl(String shortUrlKey) {
        return repository.getItem(shortUrlKey);
    }

    private UrlMappingModel createUrlMappingModel(String longUrl, String shortUrl) {
        return UrlMappingModel.builder()
                .shortUrl(shortUrl)
                .longUrl(longUrl)
                .build();
    }
}
