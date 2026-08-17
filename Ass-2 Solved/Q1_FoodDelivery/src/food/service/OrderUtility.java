package food.service;

import food.model.FoodOrder;

public class OrderUtility {

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static void generateOrderSummary(FoodOrder order) {
        double discount = ((food.model.Discountable) order).applyDiscount();
        double delivery = order.calculateDeliveryCharge();
        double finalAmount = order.getAmount() - discount + delivery;

        System.out.println("Order ID       : " + order.getOrderId());
        System.out.println("Customer       : " + order.getCustomerName());
        System.out.println("Bill Amount    : Rs. " + order.getAmount());
        System.out.println("Discount       : Rs. " + discount);
        System.out.println("Delivery Charge: Rs. " + delivery);
        System.out.println("Final Payable  : Rs. " + finalAmount);
        System.out.println("------------------------------------------");
    }
}
