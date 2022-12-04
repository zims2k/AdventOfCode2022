package structs.day1;

public record Elf(int elfId, int maxCalories) implements Comparable<Elf> {

    @Override
    public int compareTo(Elf o) {
        return Integer.compare(this.maxCalories, o.maxCalories);
    }
}
