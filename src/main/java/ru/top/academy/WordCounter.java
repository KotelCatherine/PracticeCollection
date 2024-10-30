package ru.top.academy;

import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {
    private Map<String, Integer> uniqueWord = new HashMap<>();
    private List<String> textInTheList = new ArrayList<>();
    private int count = 3;
    private String text =
            """
                    The Collection in Java is a framework that provides an\
                     architecture to store and manipulate the group of objects.
                    Java Collections can achieve all the operations that you\
                     perform on a data such as searching, sorting, insertion, manipulation, and deletion.
                    Java Collection means a single unit of objects. Java Collection\
                     framework provides many interfaces (Set, List, Queue, Deque) \
                    and classes (ArrayList, Vector, LinkedList, PriorityQueue, HashSet, LinkedHashSet, TreeSet).""";

    public WordCounter() {
        text = ignoreCase(text);
        String s = text.replaceAll("\\pP", "");
        Collections.addAll(textInTheList, s.split("[\\s+]"));

        wordCounterMethod();

        Map<String, Integer> sortedByKey = sortedMapByKey(uniqueWord);
        printMap(sortedByKey);

        System.out.println("\nСписок часто используемых слов, которые входят в топ 3: ");
        Map<String, Integer> sortedByValue = sortedMapByValue(uniqueWord);

        printMap(sortedByValue, count);
    }

    private String ignoreCase(String text) {
        return text.toLowerCase();
    }

    private Map<String, Integer> sortedMapByValue(Map<String, Integer> uniqueWord) {
        return uniqueWord
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new
                ));
    }

    private Map<String, Integer> sortedMapByKey(Map<String, Integer> uniqueWord) {
        return uniqueWord
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new
                ));
    }

    private void wordCounterMethod() {
        for (String s1 : textInTheList) {
            int counter = 0;
            for (String s2 : textInTheList) {
                if (s1.equals(s2)) {
                    counter++;
                }
            }
            uniqueWord.put(s1, counter);
        }
    }

    private void printMap(Map<String, Integer> sortedByValue, int count) {
        for (Map.Entry<String, Integer> topWord : sortedByValue.entrySet()) {
            if (count > 0) {
                System.out.printf("%-20s%-2d%n", topWord.getKey(), topWord.getValue());
            } else {
                break;
            }
            count--;
        }
    }

    private void printMap(Map<String, Integer> sortedByKey) {
        for (Map.Entry<String, Integer> map : sortedByKey.entrySet()) {
            System.out.printf("%-20s%-2d%n", map.getKey(), map.getValue());
        }
    }
}
