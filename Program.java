import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static ArrayList<String> elements;

    static {
        try {
            elements = new ArrayList<String>(Files.readAllLines(Paths.get("elements.txt")));
        } catch (Exception e) {
            e.printStackTrace();
            elements = new ArrayList<String>();
        }
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        for (int a = 0; a < elements.size(); a++) {
            for (int b = 0; b < elements.size(); b++) {
                if (!formulaExists(elements.get(a), elements.get(b))) {
                    String formula = elements.get(a) + " + " + elements.get(b);
                    System.out.print(formula + " = ");
                    String result = kb.nextLine();
                    String combination = formula + " = " + result;
                    append(combination, "combinations.txt");
                    if (!elements.contains(result)) {
                        elements.add(result);
                        append(result, "elements.txt");
                    }
                }
            }
        }
        kb.close();
    }

    public static boolean formulaExists(String componentA, String componentB) {
        String formulaA = componentA + " + " + componentB;
        String formulaB = componentB + " + " + componentA;
    }

    public static void append(String s, String file) {
        try {
            Files.writeString(Paths.get(file), s + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}