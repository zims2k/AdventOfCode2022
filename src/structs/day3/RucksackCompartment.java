package structs.day3;

import java.util.ArrayList;

public class RucksackCompartment {
    private final RucksackItem[] rucksackItems;

    public RucksackCompartment(String rucksackItemsString) {
        ArrayList<RucksackItem> items = new ArrayList<>();

        for (int i=0;i<rucksackItemsString.length();i++){
            items.add(new RucksackItem(rucksackItemsString.charAt(i)));
        }

        rucksackItems = items.toArray(new RucksackItem[0]);
    }

    public RucksackItem[] getRucksackItems() {
        return rucksackItems;
    }
}
