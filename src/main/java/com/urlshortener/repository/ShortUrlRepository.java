package com.urlshortener.repository;

import com.urlshortener.db.UrlMappingModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ShortUrlRepository {
    private final DynamoDbClient dynamoDbClient;
    @Value("${awsConfig.ddb.tableName}")
    private String tableName;

    public UrlMappingModel saveItem(UrlMappingModel model) {
        Map<String, AttributeValue> map = new HashMap<>();
        map.put("shortUrl", AttributeValue.fromS(model.getShortUrl()));
        map.put("longUrl", AttributeValue.fromS(model.getLongUrl()));
        map.put("createdAt", AttributeValue.fromN(String.valueOf(Instant.now().toEpochMilli())));
        map.put("lastUpdatedAt", AttributeValue.fromN(String.valueOf(Instant.now().toEpochMilli())));
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(map)
                .build();
        dynamoDbClient.putItem(putItemRequest);
        return model;
    }

    public String getItem(String shortUrlKey) {
        Map<String, AttributeValue> map = new HashMap<>();
        map.put("shortUrl", AttributeValue.fromS(shortUrlKey));
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(map)
                .build();
        GetItemResponse getItemResponse = dynamoDbClient.getItem(request);
        if (getItemResponse.item() != null) {
            return getItemResponse.item().get("longUrl").s();
        }
        return null;
    }
}
