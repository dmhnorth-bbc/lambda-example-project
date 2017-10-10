package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class LambdaBasicExamples {
    /*
    Overloading I haven't got overloading to work, an int is called as a string in the current setup if all methods were
    named the same thing.

    But you can name a different method to be called and use different test methods within the lambda setup in AWS under
    the heading "Handler" - example.LambdaBasicExamples::calledWithoutInput
    I can confirm you can change this whilst a JAR is deployed.

    Logging appears in the cloudwatch logs page which comes with Lambda for free. The logging appears in a file
    next to the version of the Lambda that has been published.
    */

    public String calledWithoutInput(Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("The 'calledWithoutInput' function was executed");
        return "The 'calledWithoutInput' function was executed";
    }

    public String stringInputMethod(String myString, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("The 'stringInputMethod' received string : " + myString);
        return "The 'stringInputMethod' received string : " + myString;
    }

    public String intInputMethod(int myCount, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("The 'IntInputMethod' received int : " + myCount);
        return "The 'IntInputMethod' received int : " + myCount;
    }
}