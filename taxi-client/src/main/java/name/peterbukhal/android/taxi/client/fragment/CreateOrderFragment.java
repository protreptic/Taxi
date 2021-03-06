package name.peterbukhal.android.taxi.client.fragment;

import android.accounts.Account;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import name.peterbukhal.android.taxi.client.R;
import name.peterbukhal.android.taxi.client.model.Cities;
import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.server.api.json.JsonTaxikServiceImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by
 *      petronic on 16.05.16.
 */
public final class CreateOrderFragment extends Fragment {

    public static final String FRAGMENT_TAG_CREATE_ORDER = "fragment_tag_create_order";
    public static final String ARG_ACCOUNT = "arg_account";

    public static Fragment newInstance(Account account) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_ACCOUNT, account);

        Fragment fragment = new CreateOrderFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_create_order, container, false);
    }

}
