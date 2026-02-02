import java.io.*;
import java.util.*;

public class FileStringOperations {

    public static void main(String[] args) {

        String paragraph = "";

        //FILE READING (try-catch)
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
            br.close();

            paragraph = sb.toString().trim();

        } catch (IOException e) {
            System.out.println("Error reading file");
            return;
        }

        //REPLACE MULTIPLE SPACES
        paragraph = paragraph.replaceAll("\\s+", " ");

        //COUNT CHARACTERS (no spaces)
        int charCount = paragraph.replace(" ", "").length();

        //WORD COUNT
        String[] words = paragraph.split(" ");
        int wordCount = words.length;

        //SENTENCE COUNT

        String[] sentences = paragraph.split("[.!?]");
        int sentenceCount = sentences.length;
         
        //TOP 5 FREQUENT WORDS
        Map<String, Integer> freqMap = new HashMap<>();

        for (String word : words) {
            word = word.toLowerCase().replaceAll("[^a-z]", "");
            if (!word.isEmpty()) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(freqMap.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Top 5 frequent words:");
        for (int i = 0; i < Math.min(5, list.size()); i++) {
            System.out.println(list.get(i).getKey() + " = " + list.get(i).getValue());
        }
        //REVERSE EACH SENTENCE

        System.out.println("\nReversed sentences:");

        for (String s : sentences) {
            String trimmed = s.trim();
            if (!trimmed.isEmpty()) {
                StringBuilder rev = new StringBuilder(trimmed);
                System.out.println(rev.reverse().toString());
            }
        }

      
        //== vs equals()
        String s1 = new String("Java");
        String s2 = new String("Java");

        System.out.println("\n== vs equals():");
        System.out.println("Using == : " + (s1 == s2));
        System.out.println("Using equals(): " + s1.equals(s2));


        //FINAL COUNTS
      
        System.out.println("\nFinal Counts:");
        System.out.println("Characters (excluding spaces): " + charCount);
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
    }
}
