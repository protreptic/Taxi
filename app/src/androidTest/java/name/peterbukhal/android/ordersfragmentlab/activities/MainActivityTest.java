package name.peterbukhal.android.ordersfragmentlab.activities;

import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created on 18/03/16 18:37 by
 *
 * @author Peter Bukhal (petr@taxik.ru)
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        mMainActivity = getActivity();
    }

    @Test
    public void testMainActivity_notnull() {
        assertNotNull(mMainActivity);
    }

    @Test
    public void testMainActivity_instanceof_AppCompatActivity() {
        //noinspection ConstantConditions
        assertTrue(mMainActivity instanceof AppCompatActivity);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

}