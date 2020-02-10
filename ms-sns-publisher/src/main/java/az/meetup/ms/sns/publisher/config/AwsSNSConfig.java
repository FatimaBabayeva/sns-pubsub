package az.meetup.ms.sns.publisher.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSNSConfig {

    @Value("${aws.credentials.accessKey}")
    String accessKey;

    @Value("${aws.credentials.secretKey}")
    String secretKey;

    @Value("${aws.region}")
    String region;

    @Value("${aws.host}")
    String host;

    @Bean
    public AmazonSNS snsClient() {
        // Create Amazon SNS Client
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(host, region))
                .build();
    }
}
