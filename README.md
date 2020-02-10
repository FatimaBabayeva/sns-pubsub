# PubSub example with Amazon SNS & Amazon SQS

* **ms-sns-publisher** is a microservice to publish a message to SNS topic
* **ms-sns-subscriber** is a microservice to receive messages from a SNS topic via SQS queue

## Steps:

* ### Run localstack on your local machine
      docker run -p 4575:4575 -p 4576:4576 --name localstack -d localstack/localstack

* ### Run the following script  
        SQS_POLICY=
        sqs_policy()
        {
        #First param is the queue arn, second param is the topic arn
        SQS_POLICY=`printf '{ "Policy": "{\x5C\"Version\x5C\":\x5C\"2020-01-03\x5C\",\x5C\"Statement\x5C\":[{\x5C\"Sid\x5C\":\x5C\"PubSubExampleSQSPolicy\x5C\",\x5C\"Effect\x5C\":\x5C\"Allow\x5C\",\x5C\"Principal\x5C\":\x5C\"*\x5C\",\x5C\"Action\x5C\":\x5C\"sqs:SendMessage\x5C\",\x5C\"Resource\x5C\":\x5C\"%s\x5C\",\x5C\"Condition\x5C\":{\x5C\"ArnEquals\x5C\":{\x5C\"aws:SourceArn\x5C\":\x5C\"%s\x5C\"}}}]}" }' "$1" "$2"`
        `echo $SQS_POLICY > $PWD/sqs-policy.json`
        }
        
        SNS_NAME="test-sns-topic"
        SQS_NAME="test-sqs-queue"
        
        #Create SNS topic to send notifications to
        SNS_ARN=`aws --endpoint-url=http://localhost:4575 sns create-topic --name ${SNS_NAME} | jq -r '.TopicArn'`
        
        #Create SQS to send SNS to 
        SQS_URL=`aws --endpoint-url=http://localhost:4576 sqs create-queue --queue-name ${SQS_NAME} | jq -r '.QueueUrl'`
        SQS_ARN=`aws --endpoint-url=http://localhost:4576 sqs get-queue-attributes --queue-url ${SQS_URL} --attribute-names QueueArn | jq -r '.Attributes .QueueArn'`
        
        #Add necessary SQS <--> SNS permissions
        sqs_policy ${SQS_ARN} ${SNS_ARN}
        `aws --endpoint-url=http://localhost:4576 sqs set-queue-attributes --queue-url ${SQS_URL} --attributes file://sqs-policy.json`
        
        #subscribe the queue to the notifications
        aws --endpoint-url=http://localhost:4575 sns subscribe --topic-arn ${SNS_ARN} --protocol sqs --notification-endpoint ${SQS_ARN}
        
        echo "SQS_URL = $1" $SQS_URL
        echo "SNS_ARN = $1" $SNS_ARN
        
* ### Run the publisher microservice

* ### Run the subscriber microservice

* ### In publisher, call the /publish endpoint with desired message
    use **http://localhost:8080/swagger-ui.html** or Postman (or any other tool)

* ### In subscriber, call the /message endpoint to see received messages
    use **http://localhost:8081/swagger-ui.html** or Postman (or any other tool)