package com.dataart.yandrieiev;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created by Yurii Andrieiev on 7/11/2015.
 */
public class WordOccurrenceTableModel extends AbstractTableModel{
    final List<WordOccurrence> wordOccurrenceList;

    public WordOccurrenceTableModel(List<WordOccurrence> wordOccurrenceList) {
        Collections.sort(wordOccurrenceList);
        this.wordOccurrenceList = wordOccurrenceList;
    }

    @Override
    public int getRowCount() {
        return wordOccurrenceList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final WordOccurrence wordOccurrence = wordOccurrenceList.get(rowIndex);
        switch (columnIndex){
            case 0: return wordOccurrence.getWord();
            case 1: return wordOccurrence.getCounter();
        }
        assert false;
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Word";
            case 1: return "Occurrences counter";
        }
        assert false;
        return null;
    }
}
