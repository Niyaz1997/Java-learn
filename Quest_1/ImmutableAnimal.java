import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public final class ImmutableAnimal {
    private final String name;
    private final int averageWeight;
    private final List<String> region;
    public ImmutableAnimal (String name, int averageWeight, List<String> region) {
        this.name =name;
        this.averageWeight=averageWeight;
        this.region=new ArrayList<>(region);
    }
    public String getName() {
        return name;
    }
    public int getAverageWeight() {
        return averageWeight;
    }
    public List<String> getRegion() {
        return List.copyOf(region);
    }
    public ImmutableAnimal addRegion(String district) {
        List<String> newRegion = new ArrayList<>(region);
        newRegion.add(district);
        return new ImmutableAnimal(name, averageWeight, newRegion);
    }
    public ImmutableAnimal removeRegion(String district) {
        List<String> newRegion = new ArrayList<>(region);
        newRegion.remove(district);
        return  new ImmutableAnimal(name, averageWeight, newRegion);
    }
    public ImmutableAnimal withName(String newName) {
        return new ImmutableAnimal(newName, averageWeight, region);
    }
    public ImmutableAnimal withAverageWeight(int newAverageWeight) {
        return new ImmutableAnimal(name,newAverageWeight, region);
    }
    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof ImmutableAnimal)) return false;
        ImmutableAnimal other=(ImmutableAnimal) o;
        return averageWeight==other.averageWeight && Objects.equals(name, other.name) && Objects.equals(region, other.region);
    }
    @Override
    public int hashCode() {
        return  Objects.hash(name, averageWeight, region);
    }
    public String toString() {
        return String.format("ImmutableAnimal{name='%s', averageWeight=%d, region=%s}", name, averageWeight, region);
    }
}
