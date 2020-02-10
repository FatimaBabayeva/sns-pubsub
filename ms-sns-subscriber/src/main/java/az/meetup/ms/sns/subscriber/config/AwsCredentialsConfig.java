package az.meetup.ms.sns.subscriber.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsCredentialsConfig implements AWSCredentialsProvider {
    private String accessKey;
    private String secretKey;

    public AwsCredentialsConfig(@Value("${aws.credentials.accessKey}") String accessKey,
                                @Value("${aws.credentials.secretKey}") String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    @Override
    public AWSCredentials getCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Override
    public void refresh() {
    }
}
