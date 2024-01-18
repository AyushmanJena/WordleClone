import java.io.*;
import java.util.*;
public class Game {
    static int chance = 5;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Call the function to get a random word
        String answer = getRandomWordFromArray();

        /* // I know this is a more formal approach but I wanted to keep it simpler
        // Also to share the source code to friends easily to have fun modifying
        String answer = "HELLO";
        try{
            String filePath = "words.txt";
            answer = getRandomWordFromFile(filePath);
        }
        catch (Exception e){
            System.out.println("Some Error Occured");
        } */

        int c = 0;
        System.out.println("==== WELCOME TO WORDLE CLONE ====");
        System.out.println("Guess the "+answer.length()+" letter word ");
        while(true){
            if(c == chance){
                System.out.println("YOU LOSE, noobuda");
                System.out.println("ANSWER : "+answer.toUpperCase());
                break;
            }
            System.out.print(" : ");
            String input = sc.next();
            if(answer.length() != input.length()){
                System.out.println("Enter a "+ answer.length()+" letter word ");
                continue;
            }
            c++;
            if(check(answer.toUpperCase(), input.toUpperCase())){
                System.out.println("YOU WIN!!!");
                break;
            }
        }
    }

    public static boolean check(String ans, String input){
        ArrayList<Character> correct_letters = new ArrayList<Character>();
        ArrayList<Character> correct_pos = new ArrayList<Character>();

        if(ans.equals(input)){
            return true;
        }
        // checking correct letters
        for(int i = 0; i<input.length(); i++){//for input
            for(int j = 0; j < ans.length(); j++){//for ans
                if(ans.charAt(j) == input.charAt(i)){
                    char ch = input.charAt(i);
                    correct_letters.add(ch);
                    break;
                }
            }
        }

        // checking correct letters in correct position
        for(int i = 0; i<input.length(); i++){
            if(ans.charAt(i) == input.charAt(i)){
                char ch = input.charAt(i);
                correct_pos.add(ch);
            }
        }
        // correct_letters = correct_letters - correct_pos : to ignore repeatation of same letters in both lists
        ArrayList<Character> correct_let = new ArrayList<Character>();
        for(char c : correct_letters){
            if(!correct_pos.contains(c) && !correct_let.contains(c)){
                correct_let.add(c);
            }
        }

        System.out.print("Letters in correct Position : ");
        System.out.println(correct_pos.toString());
        System.out.print("Correct Letters but not in correct Pos : ");
        System.out.println(correct_let.toString());
        return false;
    }

    static String[] words = {
            "Apple", "Chair", "Smile", "Horse", "Sunny", "Green", "Water", "Tiger", "Queen",
            "Happy", "Beach", "River", "Dance", "Plant", "Cloud", "Jelly", "Magic", "Mirth",
            "Watch", "Bread", "Music", "Brush", "Mouse", "Space", "Round", "Angel", "Lemon",
            "Quiet", "Clock", "Paper", "Ocean", "Fairy", "Light", "Grasp", "House", "Sweet",
            "Smile", "Table", "Early", "Solid", "Puppy", "Mango", "Storm", "Amber", "Sound",
            "Grace", "Beach", "Frost", "Maple", "Candy", "Bloom", "Clown", "Swift", "Stone",
            "Snack", "Clear", "Color", "Blaze", "Spark", "Jolly", "Fairy", "Tiger", "Bliss",
            "Jelly", "Mirth", "Watch", "Angel", "Lemon", "Quiet", "Clock", "Ocean", "Light",
            "Grasp", "House", "Sweet", "Smile", "Table", "Early", "Solid", "Puppy", "Mango",
            "Storm", "Amber", "Sound", "Grace", "Beach", "Frost", "Maple", "Candy", "Bloom",
            "Clown", "Swift", "Stone", "Snack", "Clear", "Color", "Blaze", "Spark", "Jolly",
            "Fairy"
    };

    private static String getRandomWordFromArray() {
        // Check if the array is empty
        if (words == null || words.length == 0) {
            throw new IllegalArgumentException("The array is empty or null.");
        }

        // Select a random word from the array
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        return words[randomIndex];
    }

    private static String getRandomWordFromFile(String filePath) throws IOException {
        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Add each word to the list
                words.add(line.trim());
            }
        }

        // Check if there are any words in the file
        if (words.isEmpty()) {
            throw new IOException("No words found in the file.");
        }

        // Select a random word from the list
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }
}