package structs.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Rucksack {

    private final RucksackCompartment rucksackCompartment1;
    private final RucksackCompartment rucksackCompartment2;

    public Rucksack(String rucksackCompartmentString) {
        int splitLen = rucksackCompartmentString.length() / 2;

        rucksackCompartment1 = new RucksackCompartment(rucksackCompartmentString.substring(0, splitLen));
        rucksackCompartment2 = new RucksackCompartment(rucksackCompartmentString.substring(splitLen));
    }

    public static RucksackItem[] findBadge(RucksackCompartment[] compartments) {
        ArrayList<ArrayList<RucksackItem>> itemsItems = new ArrayList<>();

        for (int i = 0; i < compartments.length; i++) {
            if (i % 2 == 0){
                ArrayList<RucksackItem> items = new ArrayList<>();
                itemsItems.add(items);
            }

            RucksackItem[] rucksackItems = compartments[i].getRucksackItems();
            itemsItems.get(i/2).addAll(Arrays.asList(rucksackItems));
        }

        HashSet<RucksackItem> itemsSet0 = new HashSet<>(itemsItems.get(0));
        HashSet<RucksackItem> itemsSet1 = new HashSet<>(itemsItems.get(1));
        HashSet<RucksackItem> itemsSet2 = new HashSet<>(itemsItems.get(2));

        itemsSet0.retainAll(itemsSet1);
        itemsSet0.retainAll(itemsSet2);

        return itemsSet0.toArray(new RucksackItem[0]);
    }

    public List<RucksackCompartment> getCompartmentsList(){
        ArrayList<RucksackCompartment> compartments = new ArrayList<>();
        compartments.add(rucksackCompartment1);
        compartments.add(rucksackCompartment2);

        return compartments;
    }

    public RucksackItem[] findDuplicates(){
        return findDuplicates(rucksackCompartment1, rucksackCompartment2);
    }

    private static RucksackItem[] findDuplicates(RucksackCompartment rucksackCompartment1, RucksackCompartment rucksackCompartment2){
        return findDuplicates(rucksackCompartment1.getRucksackItems(), rucksackCompartment2.getRucksackItems());
    }

    public static RucksackItem[] findDuplicates(RucksackItem[] items1, RucksackItem[] items2){
        HashSet<RucksackItem> itemsHs2 = new HashSet<>();

        if (items1 == null) {
            itemsHs2 = new HashSet<>(Arrays.asList(items2));
            return itemsHs2.toArray(new RucksackItem[0]);
        }

        HashSet<RucksackItem> itemsHs = new HashSet<>(Arrays.asList(items1));

        for (RucksackItem rucksackItem : items2) {
            if (itemsHs.contains(rucksackItem)){
                itemsHs2.add(rucksackItem);
            }
        }

        return itemsHs2.toArray(new RucksackItem[0]);

    }

}
