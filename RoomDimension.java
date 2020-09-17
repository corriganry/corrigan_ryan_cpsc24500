public class RoomDimension {
    /**
     * This function computes the area of the circle
     * @return area of the room
     */
    public static double computeArea() {
        double area = ((25*12)*(20*12)) - (.5 * (10*12) * (12*12));
        area = area + area * .2;
        return area;
    }
    /**
     * This function computes the total area of the boards in the packages
     * @return packageArea
     */
    public static double packageArea() {
        double boardArea = 30 * 6;
        double packageArea = 6 * boardArea;
        return packageArea;
    }
    /**
     * This function computes the amount of packages needed
     * @return area
     */
    public static double amountPackages() {
        double amountPackages = ((computeArea() / packageArea()) + 1);
        return amountPackages;
    }
    /**
     * This function computes the price of the packages
     * @return area
     */
    public static double pricePackages() {
        double price = amountPackages() * 24.99;
        return price;
    }
    // main
    public static void main(String[] args) {
        double amountPackages = amountPackages();
        System.out.printf("The amount of packages we would need is %.0f.\n", (amountPackages));
        double price = pricePackages();
        System.out.printf("The price of the packages would be $%.2f.", (price));
    }
}