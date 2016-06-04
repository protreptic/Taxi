package name.peterbukhal.android.taxi.client.server.api.json.response;

/**
 * Created by
 *      petronic on 03.06.16.
 */
public final class SubmitInviteFriendResponse {

    private final Boolean status;

    public SubmitInviteFriendResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

}
