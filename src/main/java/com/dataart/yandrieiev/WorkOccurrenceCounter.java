package com.dataart.yandrieiev;

import com.google.common.collect.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.dataart.yandrieiev.Util.isBlank;

/**
 * Created by Yurii Andrieiev on 7/11/2015.
 */
public class WorkOccurrenceCounter {

    private Collection<String> split(String text) {
        return Arrays.stream(text.split("\\s+"))
                .map(word -> word.replaceAll("\\W+", "").trim().toLowerCase())
                .collect(Collectors.toList());
    }

    public List<WordOccurrence> countWords(String text) {
        final Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(split(text));
        return Maps.asMap(wordsMultiset.elementSet(), wordsMultiset::count)
                .entrySet()
                .stream()
                .filter(entry -> !isBlank(entry.getKey()))
                .map(entry -> new WordOccurrence(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
