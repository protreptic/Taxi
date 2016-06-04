package name.peterbukhal.android.taxi.client.server.api.json.request;

/**
 * Created by
 *      petronic on 03.06.16.
 */
public final class SubmitInviteFriendRequest extends EmptyRequest {

    private final String phoneNumber;

    public SubmitInviteFriendRequest(String token, String phoneNumber) {
        super(token);

        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
