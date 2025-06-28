package com.urlshortener.controller;

import com.urlshortener.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class ShortUrlRedirectController {

    private final ShortUrlService generateShortUrlService;

    @GetMapping("/{shortUrlKey}")
    public RedirectView redirectToLongUrl(@PathVariable String shortUrlKey) {
        String longUrl = generateShortUrlService.getLongUrl(shortUrlKey);

        if (longUrl != null) {
            return new RedirectView(longUrl);
        }
        return new RedirectView("/shortUrlNotFound");
    }
}
