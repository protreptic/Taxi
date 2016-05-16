package name.peterbukhal.android.taxi.client.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.fragment.OrdersFragment;

/**
 * Created on 18/03/16 18:37 by
 *
 * @author Peter Bukhal (petr@taxik.ru)
 */
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

    @Test
    public void testMainActivity_fragment_content_notnull() {
        assertNotNull(mMainActivity.findViewById(R.id.main_content));

        Fragment fragment = mMainActivity.getSupportFragmentManager().findFragmentByTag(OrdersFragment.FRAGMENT_TAG_ORDERS);

        assertNotNull(fragment);
        assertTrue(fragment instanceof OrdersFragment);
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

}