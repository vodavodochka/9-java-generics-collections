package com.example.task03;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> result = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            reader.lines().map(String::toLowerCase)
                    .filter(word -> word.matches("[а-яё]+") && word.length() > 2)
                    .forEach(word -> {
                        char[] chars = word.toCharArray();
                        Arrays.sort(chars);
                        String sortedWord = new String(chars);
                        result.computeIfAbsent(sortedWord, key -> new TreeSet<>()).add(word);
                    });

        }
        catch (IOException e) {
            e.getStackTrace();
        }

        return result.values().stream()
                .filter(group -> group.size() >= 2)
                .collect(Collectors.toList());
    }
}
