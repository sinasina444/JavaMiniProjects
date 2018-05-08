package Homework12;



import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User: blangel
 * Date: 12/7/16
 * Time: 8:25 AM
 *
 * Convert Java 1.7 (and below) style code into Java 1.8 style code
 */
public class Conversions {

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * @param values to remove empty values
     * @return non-empty list of {@code values}
     */
    public static List<String> removeEmptyValues(List<String> values) {
        if (values == null) {
            return null;
        }
        List<String> nonEmpty = new ArrayList<>(values.size());
        for (String value : values) {
            if ((value == null) || value.trim().isEmpty()) {
                continue;
            }
            nonEmpty.add(value.trim());
        }
        return nonEmpty;
    }

    /**
     * Java 8 equivalent of {@link #removeEmptyValues(List)}
     * This should <b>not</b> be parallel
     */
    public static List<String> removeEmptyValuesJava8(List<String> values) {
    // TODO - remove this comment once completed
        return values == null ? null : values.stream().filter(str -> str != null && !str.trim().isEmpty()).collect(Collectors.toList());
    }

    /**
     * Note, the uniqueness should be by {@link VehicleMake} itself not its associated name.
     * @param vehicleLoader for which to load {@link VehicleMake} values by {@link Region#name()}
     * @return a unique {@link NavigableSet} of lower-case {@link VehicleMake#getName()} for all {@link Region#values()}
     */
    public static NavigableSet<String> getUniqueAndNavigableLowerCaseMakeNames(VehicleLoader vehicleLoader) {
        Region[] regions = Region.values();
        final CountDownLatch latch = new CountDownLatch(regions.length);

        final Set<VehicleMake> uniqueVehicleMakes = new HashSet<>();
        for (Region region : regions) {
            EXECUTOR.submit(new Runnable() {
                @Override public void run() {
                    List<VehicleMake> regionMakes = vehicleLoader.getVehicleMakesByRegion(region.name());
                    if (regionMakes != null) {
                        uniqueVehicleMakes.addAll(regionMakes);
                    }
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }

        NavigableSet<String> navigableMakeNames = new ConcurrentSkipListSet<>();
        for (VehicleMake make : uniqueVehicleMakes) {
            if (make.getName() == null) {
                continue;
            }
            navigableMakeNames.add(make.getName().toLowerCase());
        }
        return navigableMakeNames;
    }

    /**
     * Java 8 equivalent of {@link #getUniqueAndNavigableLowerCaseMakeNames(VehicleLoader)}
     * This should <b>be</b> parallel
     */
    public static NavigableSet<String> getUniqueAndNavigableLowerCaseMakeNamesJava8(VehicleLoader vehicleLoader) {
    // TODO - remove this comment once completed
         Region[] regions = Region.values();
         final CountDownLatch latch = new CountDownLatch(regions.length);
         final Set<VehicleMake> uniqueVehicleMakes = new HashSet<>();
         
         Stream.of(regions).forEach((region) -> {
             EXECUTOR.submit(() -> {
                 List<VehicleMake> regionMakes = vehicleLoader.getVehicleMakesByRegion(region.name());
                 if (regionMakes != null) {
                     uniqueVehicleMakes.addAll(regionMakes);
                 }
                 latch.countDown();
             });
         });
         
         try {
             latch.await();
         } catch (InterruptedException ie) {
             Thread.currentThread().interrupt();
             throw new RuntimeException(ie);
         }
         
         NavigableSet<String> navigableMakeNames = new ConcurrentSkipListSet<>();
         uniqueVehicleMakes.stream().filter(make -> make.getName() != null).forEach(make -> navigableMakeNames.add(make.getName().toLowerCase()));
         
         return navigableMakeNames;
    }
}
