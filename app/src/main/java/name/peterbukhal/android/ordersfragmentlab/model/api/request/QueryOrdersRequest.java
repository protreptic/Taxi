package name.peterbukhal.android.ordersfragmentlab.model.api.request;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class QueryOrdersRequest implements Parcelable {

    public enum RequestOrderType {
        ALL(0), ACTIVE(1), CANCELED(2), FINISHED(3);

        int value;

        RequestOrderType(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

    }

    private String token;
    private Integer offset;
    private Integer count;
    private RequestOrderType type;

    public QueryOrdersRequest() {}

    public QueryOrdersRequest(String token, Integer offset, Integer count, RequestOrderType type) {
        this.token = token;
        this.offset = offset;
        this.count = count;
        this.type = type;
    }

    protected QueryOrdersRequest(Parcel in) {
        token = in.readString();
    }

    public static final Creator<QueryOrdersRequest> CREATOR = new Creator<QueryOrdersRequest>() {
        @Override
        public QueryOrdersRequest createFromParcel(Parcel in) {
            return new QueryOrdersRequest(in);
        }

        @Override
        public QueryOrdersRequest[] newArray(int size) {
            return new QueryOrdersRequest[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public RequestOrderType getType() {
        return type;
    }

    public void setType(RequestOrderType type) {
        this.type = type;
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
