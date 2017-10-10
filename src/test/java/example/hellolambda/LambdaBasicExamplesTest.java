package example.hellolambda;

import com.amazonaws.services.lambda.runtime.Context;
import example.LambdaBasicExamples;
import example.S3EventProcessorCreateThumbnail;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaBasicExamplesTest {

    private static String stringInput;

    @BeforeClass
    public static void createInput() throws IOException {
        stringInput = "Some Cool String";
    }

    private Context createContext() {
        TestContext ctx = new TestContext();
        ctx.setFunctionName("stringInputMethod");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        LambdaBasicExamples handler = new LambdaBasicExamples();
        Context ctx = createContext();
        String output = handler.stringInputMethod(stringInput, ctx);
        Assert.assertEquals("The 'stringInputMethod' received string : " + stringInput, output);
    }
}
