package ca.tesscorp.kelvinator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ca.tesscorp.kelvinator", appContext.getPackageName());
    }

    @Test
    public void testFahrenheitOutput() {
        double inputVal = 0.0;

        UnitConverter uc = new UnitConverter(Units.C, inputVal);

        double f = Double.parseDouble(uc.getOutputF());
        assertNotEquals(inputVal, f, 0);
    }
}
