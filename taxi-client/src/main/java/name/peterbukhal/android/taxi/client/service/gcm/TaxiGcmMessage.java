package name.peterbukhal.android.taxi.client.service.gcm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 * petronic on 10.05.16.
 */
public final class TaxiGcmMessage implements Parcelable {

    private final Long eventId;
    private final Long orderId;
    private final Long cityId;
    private final String body;

    public TaxiGcmMessage(Long eventId, Long orderId, Long cityId, String body) {
        this.eventId = eventId;
        this.orderId = orderId;
        this.cityId = cityId;
        this.body = body;
    }

    protected TaxiGcmMessage(Parcel in) {
        eventId = in.readLong();
        orderId = in.readLong();
        cityId = in.readLong();
        body = in.readString();
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCityId() {
        return cityId;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(eventId);
        dest.writeLong(orderId);
        dest.writeLong(cityId);
        dest.writeString(body);
    }

    public static final Creator<TaxiGcmMessage> CREATOR = new Creator<TaxiGcmMessage>() {

        @Override
        public TaxiGcmMessage createFromParcel(Parcel in) {
            return new TaxiGcmMessage(in);
        }

        @Override
        public TaxiGcmMessage[] newArray(int size) {
            return new TaxiGcmMessage[size];
        }

    };

}
