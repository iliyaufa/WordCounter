package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCounter {

    private final String regExp = "[а-яА-Я]+";

    public List<String> getWordList(String fileName) throws IOException{
        List<String> wordList = new ArrayList<>();
        String line = "";
        Pattern pattern = Pattern.compile(regExp);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    wordList.add(matcher.group());
                }
            }
        } catch (FileNotFoundException e) {
            String message = "Файл с таким именем не найден";
            System.out.println(message);
            throw new FileNotFoundException(message);
        } catch (IOException ex) {
            String message = "Ошибка чтения файла";
            System.out.println(message);
            throw new IOException(message);
        }
        return wordList;
    }

    public Map<String, Long> wordCounting(List<String> wordList) {
        Map<String, Long> words = new HashMap<>();
        if (!wordList.isEmpty()) {
            words = wordList.stream().collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
        } else System.out.println("Задан пустой файл или файл на другом языке.");
        return words;
    }

    public void sortAndPrint(Map<String, Long> words) {
        words.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).forEach(System.out::println);
    }
}
