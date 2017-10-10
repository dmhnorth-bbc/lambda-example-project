package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

public class S3HandlerMimeTypeCheck implements RequestHandler<S3Event, Object> {

    private Tika defaultTika = new Tika();

    @Override
    public String handleRequest(S3Event input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("The 'calledWithS3Event' function was executed: " + input.getRecords() + "\n");

        S3EventNotification.S3EventNotificationRecord record;
        try {
            record = input.getRecords().get(0);

            String srcBucket = record.getS3().getBucket().getName();
            // Object key may have spaces or unicode non-ASCII characters.
            String srcKey = record.getS3().getObject().getKey()
                    .replace('+', ' ');
            srcKey = URLDecoder.decode(srcKey, "UTF-8");

            logger.log("SourceKey: " + srcKey + "SourceBucket: " + srcBucket + "\n");

            // Download the image from S3 into a stream
            logger.log("Enter S3 download\n");

            AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
            logger.log("client loaded\n");

            //TODO times out in below statement
            S3Object s3Object = s3Client.getObject(new GetObjectRequest(
                    srcBucket, srcKey));
            logger.log("s3Object loaded\n");

            InputStream objectData = s3Object.getObjectContent();
            logger.log("objectData loaded\n");

            logger.log("Exit S3 Download\n");

            logger.log("Incoming object is of Mime type: " + defaultTika.detect(objectData));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Input key: " + record.getS3().getObject().getKey();
    }
}