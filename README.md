# lambda-example-project
Lambda Examples, including accessing S3. This is currently hosted as `S3Handler` in the UGC Lambda AWS dev dashboard. 
Findings:
- Lambda JAR is limited to 50mb upload limit. This caused problems with raw Apache Tika, so I had to remove some things from the original project and also change the import and use it slightly differently, which works. Also, full libararies have to be included in the Jar, so to build you must use `mvn clean compile assembly:single`
- You can have multiple functions set up in one lambda, and just re-route them using the Handler option in AWS. Currently hosted it is set to `example.S3HandlerExample::handleRequest` which tells you the keyname of anything uploaded to an S3 bucket.
- There is also an S3 bucket set up as an example, linked to this 
- You can test Lambdas within the dashboard, on the current one I have set up a `S3Put` call that tests the lambda by sending an S3 event that simulates creation of a JPEG.
- You can log very simply, as is shown in this example. Access to Cloudwatch logs is easy, there is a monitoring tab on the lambda page.
- Versioning will be done through Cosmos. Vanilla AWS allows you to set up lambdas though and then publish an immutable version.
- I couldn't get the official thumbnail creator tutorial to work. I will go back and look at this separately as I think it will be a useful exercise.
- I am worried about the MimeType checker, the class I have written as an example to check and log the MimeType of anything placed in a S3 bucket times out. I tried adjusting settings to increase the allowed timeouts, but it always seems to fail.
- As an alternative, we can trigger the Lambda using SNS also. Any SNS placed onto a given topic can trigger it. This would be useful if we want to have some kind of final trigger to start it after multipart uploading.
