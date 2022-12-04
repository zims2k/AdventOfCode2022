package structs.day3;

public class RucksackItem {

    private final int itemValue;

    public RucksackItem(char itemChar) {

        if (itemChar < 'A' || itemChar > 'z' || (itemChar < 'a' && itemChar > 'Z')){
            throw new IllegalArgumentException("That's not what you wanted to do, ey? THAT IS NOT A LETTER!!");
        }

        if (itemChar >= 'a'){
            itemValue = itemChar - 'a'+1;
        }
        else{
            itemValue = itemChar - 'A'+1+26;
        }
    }

    public int getItemValue() {
        return itemValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RucksackItem that = (RucksackItem) o;

        return itemValue == that.itemValue;
    }

    @Override
    public int hashCode() {
        return itemValue;
    }
}
