import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {

    File folder = new File("../perimetertest/src/");
    File[] files = folder.listFiles();

        if (files != null) {
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    System.out.println("\n" + file.getName());
                    processFile(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}


    public static void processFile(File file) throws FileNotFoundException {
        double previousX = 0;
        double previousY = 0;
        double sideLength = 0;
        double perimeter = 0;
        double startX = 0;
        double startY = 0;
        boolean firstCoord = true;

        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String[] tokens = input.nextLine().split(",");
            if (tokens.length >= 2) {
                double x = Double.parseDouble(tokens[0].trim());
                double y = Double.parseDouble(tokens[1].trim());

                if (firstCoord) {
                    startX = x;
                    startY = y;
                    previousX = x;
                    previousY = y;
                    firstCoord = false;
                } else {
                    sideLength = calcSideLength(previousX, previousY, x, y);
                    System.out.println(sideLength);
                    perimeter += sideLength;

                    // assign previous coordinates to the old placeholders
                    previousX = x;
                    previousY = y;
                }
            }
        }

        double closingSideLength = calcSideLength(previousX, previousY, startX, startY);
        perimeter += closingSideLength;
        System.out.println(closingSideLength);
        System.out.println("Perimeter = " + perimeter);
    }


    public static double calcSideLength(double previousX, double previousY, double newX, double newY) {
        double x = newX - previousX; // calculate next x length
        double y = newY - previousY; // calculate next y length

        // calculate side length from old coord point
        double sideLength = sqrt(pow(x, 2) + pow(y, 2));
        return sideLength;
    }
}