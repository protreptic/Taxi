package name.peterbukhal.android.taxi.client.server.api.json.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitSmsCodeResponse implements Parcelable {

    private String token;

    public SubmitSmsCodeResponse() {}

    public SubmitSmsCodeResponse(String token) {
        this.token = token;
    }

    protected SubmitSmsCodeResponse(Parcel in) {
        token = in.readString();
    }

    public static final Creator<SubmitSmsCodeResponse> CREATOR = new Creator<SubmitSmsCodeResponse>() {
        @Override
        public SubmitSmsCodeResponse createFromParcel(Parcel in) {
            return new SubmitSmsCodeResponse(in);
        }

        @Override
        public SubmitSmsCodeResponse[] newArray(int size) {
            return new SubmitSmsCodeResponse[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
    }
}
