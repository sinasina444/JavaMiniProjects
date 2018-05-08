package Homework12;


import java.util.List;

/**
 * User: blangel
 * Date: 12/7/16
 * Time: 8:32 AM
 */
public interface VehicleLoader {

    /**
     * Note, the return value may be null.  However, if it is non-null you can be guaranteed that the results
     * within the List are non-null.
     * @param regionName the {@link Region#name()} for which to load associated {@link VehicleMake} values
     * @return the associated {@link VehicleMake} values with {@code regionName}
     */
    List<VehicleMake> getVehicleMakesByRegion(String regionName);

}
