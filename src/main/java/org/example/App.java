package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Чтобы  воспользоваться этой программой, укажите
    имя файла, который требуется обработать.
    Например, чтобы обработать файл TEST.txt,
    введите в командной строке java WordCounter TEST.txt
*/
public class App
{
    public static void main( String[] args )throws Exception
    {
        WordCounter counterWord = new WordCounter();
        List<String> wordList = counterWord.getWordList(args[0]);
        Map<String, Long> words = counterWord.wordCounting(wordList);
        counterWord.sortAndPrint(words);
        System.out.println(String.format("Общее количество слов в файле: %d", wordList.size()));
    }
}
