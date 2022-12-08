package org.example;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class WordCounterTest{

    private final WordCounter wordCounter = new WordCounter();

    private final String testFileName = "test.txt";

    @Test
    public void checkListWordLoader() throws IOException {
        List<String> wordList = wordCounter.getWordList(testFileName);
        assertEquals(14, wordList.size());

    }

    @Test
    public void checkListWordCounter() throws IOException {
        Map<String, Long> wordMap = wordCounter.wordCounting(wordCounter.getWordList(testFileName));
        assertAll("wordMap",
                () -> assertEquals((Long) 5L, wordMap.get("стол")),
                () -> assertEquals((Long) 4L, wordMap.get("стул")),
                () -> assertEquals((Long) 3L, wordMap.get("шкаф")),
                () -> assertEquals((Long) 2L, wordMap.get("диван"))
        );
    }

    @Test
    public void checkFileNotFoundException(){
        Throwable exception = assertThrows(FileNotFoundException.class, () -> wordCounter.getWordList("set.txt"));
        assertEquals("Файл с таким именем не найден", exception.getMessage());
    }

}
