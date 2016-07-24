package name.peterbukhal.android.taxi.partner.service.gcm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * TODO Доработать документацию
 *
 * @author Peter Bukhal (peter.bukhal@gmail.com)
 */
public final class GcmMessage implements Parcelable {

    private final Long eventId;
    private final Long orderId;
    private final Long cityId;
    private final String body;

    public GcmMessage(Long eventId, Long orderId, Long cityId, String body) {
        this.eventId = eventId;
        this.orderId = orderId;
        this.cityId = cityId;
        this.body = body;
    }

    protected GcmMessage(Parcel in) {
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

    public static final Creator<GcmMessage> CREATOR = new Creator<GcmMessage>() {

        @Override
        public GcmMessage createFromParcel(Parcel in) {
            return new GcmMessage(in);
        }

        @Override
        public GcmMessage[] newArray(int size) {
            return new GcmMessage[size];
        }

    };

    @Override
    public String toString() {
        return "GcmMessage{" +
                "eventId=" + eventId +
                ", orderId=" + orderId +
                ", cityId=" + cityId +
                ", body='" + body + '\'' +
                '}';
    }

}
