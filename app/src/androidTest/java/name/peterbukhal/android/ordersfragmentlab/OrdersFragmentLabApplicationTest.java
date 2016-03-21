package name.peterbukhal.android.ordersfragmentlab;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.test.ApplicationTestCase;

import org.junit.Test;

import java.security.Permissions;

public class OrdersFragmentLabApplicationTest extends ApplicationTestCase<OrdersFragmentLabApplication> {

    private OrdersFragmentLabApplication sut;

    public OrdersFragmentLabApplicationTest() {
        super(OrdersFragmentLabApplication.class);

        sut = getApplication();
    }

    @Test
    public void testApplication_checkSelfPermissions() {
        assertEquals("App needs be granted internet access", PackageManager.PERMISSION_GRANTED, ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET));
    }
}