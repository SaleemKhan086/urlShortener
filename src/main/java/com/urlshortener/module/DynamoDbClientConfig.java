package com.urlshortener.module;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbClientConfig {
    @Value("${awsConfig.ddb.region}")
    private String region;
    @Value("${awsConfig.ddb.accessKey}")
    private String accessKey;
    @Value("${awsConfig.ddb.secretKey}")
    private String secretKey;

    @Bean
    public DynamoDbClient getDynamoDbClient() {

        System.out.println("ksaleemr - creating ddb bean with "+ region+ accessKey+ secretKey);
        AwsCredentials credentials = AwsBasicCredentials.create(
                accessKey,
                secretKey
        );
        return DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(credentials)
                )
                .build();
    }
}
