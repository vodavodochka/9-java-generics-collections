package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<List<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (List<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<List<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Set<String> uniqueWords = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                if (!containsRussianCharacters(line) || line.length() < 3)
                    continue;
                uniqueWords.add(line);
            }
        }
        catch (IOException ignored) {
        }

        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : uniqueWords) {
            String sortedChars = sortCharacters(word);

            if (!anagramGroups.containsKey(sortedChars)) {
                anagramGroups.put(sortedChars, new ArrayList<>());
            }
            anagramGroups.get(sortedChars).add(word);
        }

        List<List<String>> sortedAnagramGroups = new ArrayList<>();

        for (List<String> anagrams : anagramGroups.values()) {
            if (anagrams.size() >= 2) {
                Collections.sort(anagrams);
                sortedAnagramGroups.add(anagrams);
            }
        }

        sortedAnagramGroups.sort(Comparator.comparing(list -> list.get(0))); // по 1-ому элементу

        return sortedAnagramGroups;
    }

    private static String sortCharacters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private static boolean containsRussianCharacters(String value) {
        return value.chars().allMatch(x -> isRussianCharacter((char)x));
    }

    private static boolean isRussianCharacter(char c) {
        return (c >= '\u0400' && c <= '\u04FF');
    }
}