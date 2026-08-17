package food.utility;

import food.model.FoodOrder;

public class OrderFormatter {
    public static void printBill(FoodOrder order) {
        System.out.println("Restaurant: " + FoodOrder.getRestaurantName());
        System.out.println("Order Type: " + order.getClass().getSimpleName());
    }
}
