package name.peterbukhal.android.ordersfragmentlab.model;

/**
 * Created by
 *      petronic on 21.03.16.
 */
public class City {

    private Long id;
    private String name;

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
