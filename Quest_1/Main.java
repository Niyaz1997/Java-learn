
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ImmutableAnimal animal = new ImmutableAnimal("Тигр", 220, List.of("Азия", "Европа"));
        System.out.println("Исходный объект: " + animal);

        ImmutableAnimal withNewRegion = animal.addRegion("Африка");
        System.out.println("После добавления региона: " + withNewRegion);

        System.out.println("Исходный объект после addRegion: " + animal);

        ImmutableAnimal withoutRegion = withNewRegion.removeRegion("Азия");
        System.out.println("После удаления региона: " + withoutRegion);

        ImmutableAnimal renamed = withoutRegion.withName("Амурский тигр");
        System.out.println("После смены имени: " + renamed);

        ImmutableAnimal heavier = renamed.withAverageWeight(250);
        System.out.println("После изменения веса: " + heavier);

        // Проверка equals и hashCode
        ImmutableAnimal duplicate = new ImmutableAnimal("Амурский тигр", 250, List.of("Европа", "Африка"));
        System.out.println("Объекты равны (equals): " + heavier.equals(duplicate));
        System.out.println("hashCode совпадают: " + (heavier.hashCode() == duplicate.hashCode()));
        List<String> regionsCopy = heavier.getRegion();
        System.out.println("Копия списка регионов: " + regionsCopy);

        // Проверка
        try {
            regionsCopy.add("Северная Америка");
            System.out.println("Изменённая копия: " + regionsCopy);
            System.out.println("Объект остался прежним: " + heavier);
        } catch (UnsupportedOperationException e) {
            System.out.println("Попытка изменения вызвала исключение: " + e.getMessage());
        }
    }
}