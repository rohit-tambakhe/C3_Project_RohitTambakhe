import java.util.HashMap;
import java.util.Map;

public class OrderValue {

    //Map<foodItem, number of foodItem>
    public static Map<Item, Integer> foodItemsWithCount = new HashMap<>();

    public void addFoodItemsToCart(Item foodItem, int count) {
        foodItemsWithCount.put(foodItem, count);
    }

    public int totalOrderValue() {
        int finalOrderValue = 0;
        for (Map.Entry<Item, Integer> foodItem : foodItemsWithCount.entrySet()) {

            //food item price * number of food item
            finalOrderValue = finalOrderValue + (foodItem.getKey().getPrice() * foodItem.getValue());
        }
        return finalOrderValue;
    }

}