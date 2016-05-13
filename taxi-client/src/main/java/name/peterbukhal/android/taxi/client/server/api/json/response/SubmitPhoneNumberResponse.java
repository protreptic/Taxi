package name.peterbukhal.android.taxi.client.server.api.json.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitPhoneNumberResponse implements Parcelable {

    private Boolean status;
    private Integer smsCodeLength;
    private String smsCode;
    private Integer retryDelay;

    public SubmitPhoneNumberResponse() {
    }

    public SubmitPhoneNumberResponse(Boolean status, Integer smsCodeLength, String smsCode, Integer retryDelay) {
        this.status = status;
        this.smsCodeLength = smsCodeLength;
        this.smsCode = smsCode;
        this.retryDelay = retryDelay;
    }

    protected SubmitPhoneNumberResponse(Parcel in) {
        smsCode = in.readString();
    }

    public static final Creator<SubmitPhoneNumberResponse> CREATOR = new Creator<SubmitPhoneNumberResponse>() {

        @Override
        public SubmitPhoneNumberResponse createFromParcel(Parcel in) {
            return new SubmitPhoneNumberResponse(in);
        }

        @Override
        public SubmitPhoneNumberResponse[] newArray(int size) {
            return new SubmitPhoneNumberResponse[size];
        }
    };

    public Boolean getStatus() {
        return status;
    }

    public Integer getSmsCodeLength() {
        return smsCodeLength;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public Integer getRetryDelay() {
        return retryDelay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(smsCode);
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setSmsCodeLength(Integer smsCodeLength) {
        this.smsCodeLength = smsCodeLength;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public void setRetryDelay(Integer retryDelay) {
        this.retryDelay = retryDelay;
    }
}
