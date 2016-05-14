package name.peterbukhal.android.taxi.client.server.api.json;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public final class Error implements Parcelable {

    private final Integer code;
    private final String message;
    private final String messageHuman;

    public Error(Integer code, String message, String messageHuman) {
        this.code = code;
        this.message = message;
        this.messageHuman = messageHuman;
    }

    protected Error(Parcel in) {
        code = in.readInt();
        message = in.readString();
        messageHuman = in.readString();
    }

    public static final Creator<Error> CREATOR = new Creator<Error>() {

        @Override
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }

    };

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHuman() {
        return messageHuman;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
        dest.writeString(messageHuman);
    }

}
