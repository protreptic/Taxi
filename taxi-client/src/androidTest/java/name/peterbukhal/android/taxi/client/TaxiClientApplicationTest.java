package name.peterbukhal.android.taxi.client;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.test.ApplicationTestCase;

public class TaxiClientApplicationTest extends ApplicationTestCase<TaxiClientApplication> {

    private TaxiClientApplication sut;

    public TaxiClientApplicationTest() {
        super(TaxiClientApplication.class);

        sut = getApplication();
    }

    public void testApplication_checkSelfPermissions() {
        assertEquals("App needs be granted internet access", PackageManager.PERMISSION_GRANTED, ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET));
    }
}