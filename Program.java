import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static ArrayList<String> elements;
    public static ArrayList<String> combinations;

    static {
        try {
            elements = new ArrayList<String>(Files.readAllLines(Paths.get("elements.txt")));
            combinations = new ArrayList<String>(Files.readAllLines(Paths.get("combinations.txt")));
        } catch (Exception e) {
            e.printStackTrace();
            if (elements == null) elements = new ArrayList<String>();
            if (combinations == null) combinations = new ArrayList<String>();
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
                    combinations.add(combination);
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
        String formulaA = componentA + " + " + componentB + " =";
        String formulaB = componentB + " + " + componentA + " =";
        for (String c : combinations) if (c.startsWith(formulaA) || c.startsWith(formulaB)) return true;
        return false;
    }

    public static void append(String s, String file) {
        try {
            Files.writeString(Paths.get(file), s + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}