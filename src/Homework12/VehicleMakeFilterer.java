package Homework12;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: blangel
 * Date: 12/7/16
 * Time: 8:55 AM
 */
public class VehicleMakeFilterer {

    /**
     * @param region for which to query
     * @param nameStartsWith for which to check if the query results match
     * @param loader to load VehicleMake objects
     * @return loaded VehicleMake objects in {@code region} whose name starts with {@code nameStartsWith}
     */
    public static List<VehicleMake> loadMatching(Region region, String nameStartsWith, VehicleLoader loader) {
        if ((nameStartsWith == null) || (region == null) || (loader == null)) {
            throw new IllegalArgumentException("The VehicleLoader and both region and nameStartsWith are required when loading VehicleMake matches");
        }
        List<VehicleMake> regionMakes = loader.getVehicleMakesByRegion(region.name());
        if (regionMakes == null) {
            return null;
        }
        List<VehicleMake> matches = new ArrayList<>(regionMakes.size());
        for (VehicleMake make : regionMakes) {
            if ((make.getName() == null) || !make.getName().startsWith(nameStartsWith)) {
                continue;
            }
            matches.add(make);
        }
        return matches;
    }

    /**
     * Java 8 equivalent of {@link #loadMatching(Region, String, VehicleLoader)}
     * Note, this should use Java 8 streams API
     */
    public static Optional<List<VehicleMake>> loadMatchingJava8(Region region, String nameStartsWith, VehicleLoader loader) throws IllegalArgumentException {
    // TODO - remove this comment once completed
    	//Optional<List<VehicleMake>> matches = regionMakes.map(list -> new ArrayList<>(list.size()));;
    	return Optional.ofNullable(Optional.ofNullable(loader).get().getVehicleMakesByRegion(Optional.of(region).get().name())
    	.stream().filter(make -> make.getName() != null && make.getName().startsWith(Optional.of(nameStartsWith).get()))
    	.collect(Collectors.toList()));
    	
    	//collect(Collectors.toList());
    	/*
        Optional<List<VehicleMake>> regionMakes = Optional.of(Optional.of(loader).get().getVehicleMakesByRegion(Optional.of(region).get().name()));
        Optional<List<VehicleMake>> matches = regionMakes.map(list -> new ArrayList<>(list.size()));

        regionMakes.get().stream()
                   .filter(make -> make.getName().startsWith(Optional.of(nameStartsWith).get()))
                   .map(make -> matches.get().add(make));
        
        return matches;
        */
    }
}
