package name.peterbukhal.android.taxi.client.service.gcm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 * petronic on 10.05.16.
 */
public class TaxiGcmMessage implements Parcelable {

    private Long eventId;
    private Long orderId;
    private Long cityId;
    private String body;

    public TaxiGcmMessage() {

    }

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

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
