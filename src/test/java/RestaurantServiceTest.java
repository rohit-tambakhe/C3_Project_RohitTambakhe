import org.junit.jupiter.api.*;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    OrderValue orderValue = new OrderValue();

    //REFACTOR ALL THE REPEATED LINES OF CODE

    private void mockData()
    { LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119,true);
        restaurant.addToMenu("Vegetable lasagne", 269,false);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        mockData();
        List<Restaurant> getRestaurants = service.getRestaurants();
        Restaurant getRestaurant = service.findRestaurantByName("Amgit elie's cafe");
        assertEquals(getRestaurants.get(0),getRestaurant);
    }
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        mockData();

        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Pantry d'or"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        mockData();
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        mockData();
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        mockData();
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void calculate_final_order_value_pass() {
        addFoodItemsToCart();

        assertEquals(1215, orderValue.totalOrderValue());
    }

    @Test
    public void calculate_final_order_value_fail() {
        addFoodItemsToCart();

        assertNotEquals(100, orderValue.totalOrderValue());
    }

    private void addFoodItemsToCart() {
        Item chulhaRoti = new Item("Chulha Roti", 25, true);
        orderValue.addFoodItemsToCart(chulhaRoti, 10);

        Item Rice = new Item("Rice", 110, true);
        orderValue.addFoodItemsToCart(Rice, 1);

        Item shahiPaneer = new Item("Shahi Paneer", 250, true);
        orderValue.addFoodItemsToCart(shahiPaneer, 2);

        Item dalTadka = new Item("Dal Tadka", 180, true);
        orderValue.addFoodItemsToCart(dalTadka, 1);

        Item rasMalai = new Item("Ras Malain", 35, true);
        orderValue.addFoodItemsToCart(rasMalai, 5);
    }
}