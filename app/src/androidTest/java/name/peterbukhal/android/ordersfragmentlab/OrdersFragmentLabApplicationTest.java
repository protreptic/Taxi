package name.peterbukhal.android.ordersfragmentlab;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.test.ApplicationTestCase;

public class OrdersFragmentLabApplicationTest extends ApplicationTestCase<OrdersFragmentLabApplication> {

    private OrdersFragmentLabApplication sut;

    public OrdersFragmentLabApplicationTest() {
        super(OrdersFragmentLabApplication.class);

        sut = getApplication();
    }

    public void testApplication_checkSelfPermissions() {
        assertEquals("App needs be granted internet access", PackageManager.PERMISSION_GRANTED, ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET));
    }
}