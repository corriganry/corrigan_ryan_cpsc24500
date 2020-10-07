import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
public class BaseballStandings {
    /**
     * This function welcomes the user and describes what the program does
     */
    public static void welcome(){
        System.out.println("****************************************");
        System.out.println("*     BASEBALL STANDINGS ANALYZER      *");
        System.out.println("****************************************\n");
        System.out.println("This program reads a file that contains");
        System.out.println("current baseball stnadings and adds to");
        System.out.println("more detailed statistics. It also prints");
        System.out.println("overall standings in the American and");
        System.out.println("national leagues.\n");
    }
    /**
     * This function prints out all of the options the user can enter
     */
    public static int standingsList(){
        Scanner sc = new Scanner(System.in);
        int standingChoice;
        System.out.println("");
        System.out.println("Which standings would you like to see?");
        System.out.println("1. AL East");
        System.out.println("2. AL Central");
        System.out.println("3. AL West");
        System.out.println("4. NL East");
        System.out.println("5. NL Central");
        System.out.println("6. NL West");
        System.out.println("7. Overall");
        System.out.println("8. Exit");
        System.out.print("Enter the number of your choice: ");
        standingChoice = sc.nextInt();
        return standingChoice; 
        }
    
    /**
     * This function calculates a win percentage for a team
     * @param line one team
     * @return the winning percentage
     */
    public static double getPercentage(String line) {
        String[] parts = line.split("\t");
        double percentage = (Double.parseDouble(parts[1]))/((Double.parseDouble(parts[1])) + (Double.parseDouble(parts[2])));
        return percentage;
    }
    /**
     * This function prints all of the stats for the teams
     * @param standings the wins and losses for each team
     */
    public static void printStats(ArrayList<String> standings){
        String [] parts;
        String [] parts1;
        parts1 = standings.get(0).split("\t");
        double mostWins = Double.parseDouble(parts1[1]);
        double leastLosses = Double.parseDouble(parts1[2]);
        System.out.printf("%-13s%13s%13s%13s%14s","Team","Wins","Losses","Pct.","Behind\n");
        System.out.println("-----------------------------------------------------------------");
        for (String standing : standings) {
            parts = standing.split("\t");
            double percentage = getPercentage(standing);
            double gamesBehind = (((mostWins - Double.parseDouble(parts[1])) + (Double.parseDouble(parts[2]) - leastLosses)))/2;
            if (gamesBehind == 0) {
                System.out.printf("%-13s%13s%13s%13.3f\n",parts[0],parts[1],parts[2],percentage);
            }
            else {
            System.out.printf("%-13s%13s%13s%13.3f%13.1f\n",parts[0],parts[1],parts[2],percentage,gamesBehind);
            }
        }
    }
    /**
     * This function is for when the user wants all of the teams in order of win percentage
     * @param overall all of the teams
     * @param line each team seperately
     */
    public static void insertByPercentage(ArrayList<String>overall, String line) {
        double percentage = getPercentage(line);
        double otherPercentage;
        int pos = -1;
        for (int i = 0; i < overall.size(); i++) {
            otherPercentage = getPercentage(overall.get(i));
            if (percentage > otherPercentage) {
                pos = i;
                break;
            }
        }
            if (pos < 0) {
                overall.add(line);
            } else {
                overall.add(pos,line);
            }
        
    }
    //main
    public static void main(String[] args) {
        welcome();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the standings file: ");
        String fName = sc.nextLine();
        ArrayList<String> alEast = new ArrayList<String>();
        ArrayList<String> alCentral = new ArrayList<String>();
        ArrayList<String> alWest = new ArrayList<String>();
        ArrayList<String> nlEast = new ArrayList<String>();
        ArrayList<String> nlCentral = new ArrayList<String>();
        ArrayList<String> nlWest = new ArrayList<String>();
        ArrayList<String> target = null;
        ArrayList<String> overall = new ArrayList<String>();
        String line;
        String [] parts;
        String division;
        boolean goAhead;
        int standingChoice;
        try {
            Scanner fsc = new Scanner(new File(fName));
            while (fsc.hasNextLine()) {
                line = fsc.nextLine();
                parts = line.split("\t");
                if (parts[0].equalsIgnoreCase("League")) {
                    division = parts[1];
                    if (division.equalsIgnoreCase("al east")){
                        target = alEast;
                    } else if (division.equalsIgnoreCase("al central")){
                        target = alCentral;
                    } else if (division.equalsIgnoreCase("al west")){
                        target = alWest;
                    } else if (division.equalsIgnoreCase("nl east")){
                        target = nlEast;
                    } else if (division.equalsIgnoreCase("nl central")){
                        target = nlCentral;
                    } else if (division.equalsIgnoreCase("nl west")){
                        target = nlWest;
                    }
                }
                else {
                    target.add(line);
                    insertByPercentage(overall,line);
                }
            }

            fsc.close();
            goAhead = true;
        }catch (Exception ex) {
            System.out.println("Could not read the file.");
            goAhead = false;
        }
        if (goAhead) {
            do {
                try{
                    standingChoice = standingsList();
                } catch (InputMismatchException ex) {
                    standingChoice = standingsList();
                }
                if (standingChoice == 1) {
                    printStats(alEast);
                }
                else if (standingChoice == 2) {
                    printStats(alCentral);
                }
                else if (standingChoice == 3) {
                    printStats(alWest);
                }
                else if (standingChoice == 4) {
                    printStats(nlEast);
                }
                else if (standingChoice == 5) {
                    printStats(nlCentral);
                }
                else if (standingChoice == 6) {
                    printStats(nlWest);
                }
                else if (standingChoice == 7) {
                    for (String team : overall) {
                        System.out.println(team);
                    }
                }
            } while (standingChoice != 8);
        }

        System.out.println("Thank you for using my program");
    }
    
}
