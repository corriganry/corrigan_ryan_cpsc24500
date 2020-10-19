import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;
/**
 * public class
 */
public class App {
    public static void welcome() {
        System.out.println("**************************************************************");
        System.out.println("*           INTERNATIONAL COVID-19 MORTALITY RATES           *");
        System.out.println("**************************************************************");
    }
    /**
     * This function obtains the hashmap and reads all of the values
     * @param fsc which is the scanner
     * @return name and values of the countries
     */
    public static LinkedHashMap<String, double[]> readData(Scanner fsc) {
        LinkedHashMap<String, double[]> result = new LinkedHashMap<String, double[]>();
        fsc.nextLine();
        String[] parts;
        String line;
        double[] values;
        String name;
        while (fsc.hasNextLine()) {
            line = fsc.nextLine();
            parts = line.split("\t");
            name = parts[0];
            values = new double[parts.length - 1];
            for (int i = 1; i < parts.length; i++) {
                values[i - 1] = Double.parseDouble(parts[i]);
            }
            result.put(name, values);
        }
        return result;
    }
    /**
     * This function generates the x axis for the deaths
     * @param howMany is how many days there are
     * @return the number of deaths that are connected to the days
     */
    public static double[] getDeaths(int howMany) {
        double[] result = new double[howMany];
        for (int i = 0; i < howMany; i++) {
            result[i] = i;
        }
        return result;

    }
    /**
     * This function sets up the frame for the cumulative deaths
     * @param plot which is the plot from the main code that contains the number of deaths
     */
    public static void setUpAndShowFrame(Plot2DPanel plot) {
        JFrame frm = new JFrame();
        frm.setTitle("Cumulative Deaths");
        frm.setBounds(100,100,500,500);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = frm.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(plot,BorderLayout.CENTER);
        frm.setVisible(true);
        plot.setAxisLabels("Day","Deaths");

    }
    /**
     * This function sets up the frame for the daily deaths
     * @param plot which is the plot from the main code that contains the number of deaths
     */
    public static void setUpAndShowFrameDaily(Plot2DPanel plot) {
        JFrame frm = new JFrame();
        frm.setTitle("Daily Deaths");
        frm.setBounds(100,100,500,500);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = frm.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(plot,BorderLayout.CENTER);
        frm.setVisible(true);
        plot.setAxisLabels("Day","Deaths");

    }
    // main
    public static void main(String[] args) {
        welcome();
        LinkedHashMap<String, double[]> countries = null;
        String names;
        String[] parts;
        double[] data;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name of data file: ");
        String fName = sc.nextLine();
        try {
            Scanner fsc = new Scanner(new File(fName));
            countries = readData(fsc);
            fsc.close();
        } catch (Exception ex) {
            countries = null;
        }
        if (countries == null) {
            System.out.println("Couldn't read the country data.");
        } else {
            do {
                String deter = "cumulative";
                System.out.print("Enter countries to plot, seperated by commas (or quit to end): ");
                names = sc.nextLine();
                if (!names.equalsIgnoreCase("quit")) {
                    System.out.print("[D]aily or [C]umulative? ");
                    String determine = sc.nextLine();
                    Plot2DPanel plot = new Plot2DPanel();
                    plot.addLegend("SOUTH");
                    parts = names.split(",");
                    for (String part : parts) {
                        part = part.trim();
                        if (countries.containsKey(part) == false) {
                            System.out.printf("%s was not found.\n", part);
                        } else {
                            data = countries.get(part);
                            double data2[] = new double[data.length];
                            for (int i = 0; i < data.length; i++) {
                                if (i == 0) {
                                    data2[i] = data[i];
                                } else {
                                    data2[i] = (data[i]) - (data[i-1]);
                                }
                            }
                            for (int i = 0; i < data2.length; i++) {
                                
                            }
                            if (determine.equalsIgnoreCase("D")) {
                                BaseLabel title = new BaseLabel("Daily Deaths", Color.BLACK, 1, 1.1);
                                plot.addPlotable(title);
                                plot.addLinePlot(part,getDeaths(data2.length),data2);
                                deter = "daily";
                            }
                            else if (determine.equalsIgnoreCase("C")) {
                                BaseLabel title = new BaseLabel("Cumulative Deaths", Color.BLACK, 1, 1.1);
                                plot.addPlotable(title);
                                plot.addLinePlot(part,getDeaths(data.length),data);
                                deter = "cumulative";
                            }
                        }
                    }
                    if (deter.equalsIgnoreCase("cumulative")){
                        setUpAndShowFrame(plot);
                    }
                    else if (deter.equalsIgnoreCase("daily")) {
                        setUpAndShowFrameDaily(plot);
                    }
                    
                }
            } while (!names.equalsIgnoreCase("quit"));
        }
        System.out.println("Thank you for using my program.");
    }
}
