package com.dataart.yandrieiev;

/**
 * Created by Yurii Andrieiev on 7/11/2015.
 */
public class WordOccurrence implements Comparable<WordOccurrence> {
    private final String word;
    private final int counter;

    public WordOccurrence(String word, int counter) {
        if (Util.isBlank(word)){
            throw new IllegalArgumentException("Word should not be blank");
        } else if (counter < 0){
            throw new IllegalArgumentException("Counter should not be null");
        }
        this.word = word;
        this.counter = counter;
    }

    public String getWord() {
        return word;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "WordOccurrence{" +
                "word='" + word + '\'' +
                ", counter=" + counter +
                '}';
    }

    @Override
    public int compareTo(WordOccurrence other) {
        if (counter != other.counter) {
            // intentionally reversed
            return other.counter - counter;
        }
        return word.compareTo(other.word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordOccurrence that = (WordOccurrence) o;

        if (counter != that.counter) return false;
        return word.equals(that.word);

    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + counter;
        return result;
    }
}
