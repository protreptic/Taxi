package name.peterbukhal.android.taxi.client.model.mut;

import android.os.Parcel;

import name.peterbukhal.android.taxi.client.model.City;
import name.peterbukhal.android.taxi.client.model.Point;
import name.peterbukhal.android.taxi.client.model.SpnPoint;
import name.peterbukhal.android.taxi.client.model.impl.CityImpl;
import name.peterbukhal.android.taxi.client.model.Immutable;

/**
 * Created by
 *      petronic on 18.05.16.
 */
public final class MutableCity implements City, Immutable<City> {

    private Long id;
    private String name;
    private String apiUrl;
    private Point point;
    private SpnPoint spnPoint;
    private Boolean transfers;
    private String[] inAppPayMethods;
    private Long experimentalEconomPlus;

    public MutableCity(Long id, String name, String apiUrl, Point point, SpnPoint spnPoint,
                    Boolean transfers, String[] inAppPayMethods, Long experimentalEconomPlus) {
        this.id = id;
        this.name = name;
        this.apiUrl = apiUrl;
        this.point = point;
        this.spnPoint = spnPoint;
        this.transfers = transfers;
        this.inAppPayMethods = inAppPayMethods;
        this.experimentalEconomPlus = experimentalEconomPlus;
    }

    public MutableCity(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.apiUrl = city.getApiUrl();
        this.point = city.getPoint();
        this.spnPoint = city.getSpnPoint();
        this.transfers = city.getTransfers();
        this.inAppPayMethods = city.getInAppPayMethods();
        this.experimentalEconomPlus = city.getExperimentalEconomPlus();
    }

    protected MutableCity(Parcel parcel) {
        id = (Long) parcel.readValue(Long.class.getClassLoader());
        name = (String) parcel.readValue(String.class.getClassLoader());
        apiUrl = (String) parcel.readValue(String.class.getClassLoader());
        point = (Point) parcel.readValue(Point.class.getClassLoader());
        spnPoint = (SpnPoint) parcel.readValue(SpnPoint.class.getClassLoader());
        transfers = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        inAppPayMethods = (String[]) parcel.readValue(String[].class.getClassLoader());
        experimentalEconomPlus = (Long) parcel.readValue(Long.class.getClassLoader());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public SpnPoint getSpnPoint() {
        return spnPoint;
    }

    @Override
    public Boolean getTransfers() {
        return transfers;
    }

    @Override
    public String[] getInAppPayMethods() {
        return inAppPayMethods;
    }

    @Override
    public Long getExperimentalEconomPlus() {
        return experimentalEconomPlus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setSpnPoint(SpnPoint spnPoint) {
        this.spnPoint = spnPoint;
    }

    public void setTransfers(Boolean transfers) {
        this.transfers = transfers;
    }

    public void setInAppPayMethods(String[] inAppPayMethods) {
        this.inAppPayMethods = inAppPayMethods;
    }

    public void setExperimentalEconomPlus(Long experimentalEconomPlus) {
        this.experimentalEconomPlus = experimentalEconomPlus;
    }

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(apiUrl);
        dest.writeValue(point);
        dest.writeValue(spnPoint);
        dest.writeValue(transfers);
        dest.writeValue(inAppPayMethods);
        dest.writeValue(experimentalEconomPlus);
    }

    public static final Creator<City> CREATOR = new Creator<City>() {

        @Override
        public City createFromParcel(Parcel in) {
            return new MutableCity(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }

    };

    @Override
    public City toImmutable() {
        return new CityImpl(this);
    }

}
