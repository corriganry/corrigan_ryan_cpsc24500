import java.util.Random;
public class CircleCalc {
    /**
     * This Function calculates the area of the circle
     * @param a the radius
     * @return the area of the circle
     */
    public static double computeArea(int a) {
        double area = 3.14159 * (a * a);
        return area;
    }

    /**
     * This Function calculates the circumference of the circle
     * @param a the radius
     * @return the circumference of the circle
     */
    public static double computeCircumference(int a) {
        double circum = 2 * 3.14159 * a;
        return circum;
    }

    // Main
    public static void main(String[] args) {
        Random rnd = new Random();
        int radius = rnd.nextInt(25);
        double area = computeArea(radius);
        double circumference = computeCircumference(radius);
        System.out.printf("The radius was %d.\n", (radius));
        System.out.printf("The area was %.2f.\n", (area));
        System.out.printf("The circumference was %.2f.\n", (circumference));
    }
}