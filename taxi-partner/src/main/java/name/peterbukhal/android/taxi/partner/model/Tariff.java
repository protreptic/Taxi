package name.peterbukhal.android.taxi.partner.model;

/**
 * Created by
 * petronic on 04.06.16.
 */
public class Tariff {

    public float basePrice = 200.0F;
    public int includedDistance = 5;
    public float movingPrice = 19.0F;
    public float trafficJamMovingPrice = 8.0F;
    public float stopPrice = 8.0F;
    public long freeWaitingTime = 1000 * 60 * 10;
    public float waitingTime = 8.0F;

}
