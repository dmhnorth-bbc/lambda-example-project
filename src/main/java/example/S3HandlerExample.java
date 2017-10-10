package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

import java.util.Arrays;

public class S3HandlerExample implements RequestHandler<S3Event, Object> {

    @Override
    public String handleRequest(S3Event input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("The 'S3HandlerExample' function was executed: " + Arrays.toString(input.getRecords().toArray()));
            S3EventNotification.S3EventNotificationRecord record;
            record = input.getRecords().get(0);
        return "Input key: " + record.getS3().getObject().getKey();
    }

}