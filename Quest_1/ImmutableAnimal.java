import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ImmutableAnimal {
    private final String name;
    private final int averageWeight;
    private final List<String> regions;


    public ImmutableAnimal(String name, int averageWeight, List<String> regions) {
        this.name = Objects.requireNonNull(name, "name не может быть null");
        this.averageWeight = averageWeight;
        if (regions == null) {
            throw new NullPointerException("regions не может быть null");
        }
        ArrayList<String> sortedRegions = new ArrayList<>(regions);
        if (sortedRegions.contains(null)) {
            throw new IllegalArgumentException("Элементы в regions не могут быть  null");
        }
        sortedRegions.sort(String.CASE_INSENSITIVE_ORDER);
        this.regions = List.copyOf(regions);
    }

    public String getName() {
        return name;
    }

    public int getAverageWeight() {
        return averageWeight;
    }

    public List<String> getRegions() {
        return List.copyOf(regions);
    }

    public ImmutableAnimal addRegion(String district) {
        List<String> newRegions = new ArrayList<>(regions);
        newRegions.add(district);
        return new ImmutableAnimal(name, averageWeight, newRegions)

                ;
    }

    public ImmutableAnimal removeRegion(String district) {
        List<String> newRegions = new ArrayList<>(regions);
        newRegions.remove(district);
        return new ImmutableAnimal(name, averageWeight, newRegions);
    }

    public ImmutableAnimal withName(String newName) {
        return new ImmutableAnimal(newName, averageWeight, regions);
    }

    public ImmutableAnimal withAverageWeight(int newAverageWeight) {
        return new ImmutableAnimal(name, newAverageWeight, regions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutableAnimal)) return false;
        ImmutableAnimal other = (ImmutableAnimal) o;
        return averageWeight == other.averageWeight && Objects.equals(name, other.name) && Objects.equals(regions, other.regions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, averageWeight, regions);
    }

    @Override
    public String toString() {
        return String.format("ImmutableAnimal{name='%s', averageWeight=%d, region=%s}", name, averageWeight, regions);
    }
}
