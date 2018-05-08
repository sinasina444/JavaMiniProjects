package Homework12;

/**
 * User: blangel
 * Date: 12/7/16
 * Time: 8:33 AM
 */
public class VehicleMake {

    private final String id;

    private final Region region;

    private final String name;

    public VehicleMake(String id, Region region, String name) {
        this.id = id;
        this.region = region;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VehicleMake that = (VehicleMake) o;
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
