package name.peterbukhal.android.ordersfragmentlab.model.api.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class SubmitSmsCodeRequest implements Parcelable {

    public enum RequestSourceType {
        ANDROID, IOS, BROWSER
    }

    private String name;
    private Integer source;
    private Integer smsCode;
    private String phoneNumber;
    private String promoCode;

    public SubmitSmsCodeRequest() {}

    public SubmitSmsCodeRequest(String name, Integer source, Integer smsCode, String phoneNumber, String promoCode) {
        this.name = name;
        this.source = source;
        this.smsCode = smsCode;
        this.phoneNumber = phoneNumber;
        this.promoCode = promoCode;
    }

    protected SubmitSmsCodeRequest(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        promoCode = in.readString();
    }

    public static final Creator<SubmitSmsCodeRequest> CREATOR = new Creator<SubmitSmsCodeRequest>() {
        @Override
        public SubmitSmsCodeRequest createFromParcel(Parcel in) {
            return new SubmitSmsCodeRequest(in);
        }

        @Override
        public SubmitSmsCodeRequest[] newArray(int size) {
            return new SubmitSmsCodeRequest[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(Integer smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(promoCode);
    }
}
