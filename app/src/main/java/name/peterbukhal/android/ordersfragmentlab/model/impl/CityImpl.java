package name.peterbukhal.android.ordersfragmentlab.model.impl;

import name.peterbukhal.android.ordersfragmentlab.model.City;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class CityImpl implements City {

    private Long id;
    private String name;

    public CityImpl(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

}
