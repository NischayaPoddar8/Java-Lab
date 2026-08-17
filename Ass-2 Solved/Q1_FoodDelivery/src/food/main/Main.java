package food.main;

import food.model.*;
import food.service.OrderUtility;
import food.utility.OrderFormatter;

public class Main {
    public static void main(String[] args) {

        FoodOrder[] orders = {
            new RegularOrder(101, "Aman", 500),
            new PremiumOrder(102, "Riya", 800),
            new RegularOrder(103, "Karan", 650),
            new PremiumOrder(104, "Simran", 1000),
            new RegularOrder(105, "Arjun", 450),
            new PremiumOrder(106, "Neha", 1200)
        };

        for (FoodOrder order : orders) {
            if (!OrderUtility.validateAmount(order.getAmount()) ||
                !OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println("Invalid order: " + order.getOrderId());
                continue;
            }

            System.out.println("\n========== ORDER BILL ==========");
            OrderFormatter.printBill(order);
            OrderUtility.generateOrderSummary(order);
        }

        FoodOrder.displayTotalOrders();
    }
}
