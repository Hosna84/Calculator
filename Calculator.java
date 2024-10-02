import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputLine;
        Pattern pattern = Pattern.compile("\\s*\"(?<string>[^\"]*)\"\\s*(\\*\\s*(?<adad>\\d+))?\\s*");
        
        while (true) {
            //Scanning inputs in a infinity while
            inputLine = scanner.nextLine();
            //Condition for ending loop(exit can have spaces before and after)
            if (inputLine.trim().equals("exit")) {
                break;
            }

            StringBuilder result = new StringBuilder();
            //spiliting by "+"
            List<String> separatedInputByPlus = splitInput(inputLine);
            
            boolean runPrint = true;
            for (String input : separatedInputByPlus) {
                Matcher matcher = pattern.matcher(input);
                
                if (!matcher.matches()) {
                    System.out.println("Invalid Command.");
                    runPrint =false;
                    break;
                }
                
                String stringPart = matcher.group("string");
                String adadPart = matcher.group("adad");
                //we just had +
                if (adadPart == null) {
                    result.append(stringPart);
                } else {
                    //we had *
                    int number = Integer.parseInt(adadPart);
                    for (int p = 0; p < number; p++) {
                        result.append(stringPart);
                    }
                }
            }
            
            if (runPrint) {
                System.out.println(result);
            }
        }
        scanner.close();
    }
    //spilit by "+"
    static List<String> splitInput(String inputLine) {
        List<String> separatedInput = new ArrayList<>();
        boolean insideQuotes = false;
        StringBuilder save = new StringBuilder();

        for (int i = 0; i < inputLine.length(); i++) {
            char eachChar = inputLine.charAt(i);
            
            if (eachChar == '"') {
                insideQuotes = !insideQuotes;
            }
            
            if (eachChar == '+' && !insideQuotes) {
                separatedInput.add(save.toString());
                save.setLength(0);
            } else {
                save.append(eachChar);
            }
            
            if (i + 1 == inputLine.length()) {
                separatedInput.add(save.toString());
            }
        }
        
        return separatedInput;
    }
}
