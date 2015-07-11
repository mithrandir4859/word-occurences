package com.dataart.yandrieiev;

import jdk.nashorn.internal.ir.BaseNode;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Yurii Andrieiev on 7/11/2015.
 */
public class MainFrame extends JFrame {

    private final JTextArea inputTextArea = new JTextArea();
    private final JTable workOccurrencesTable = new JTable();

    public MainFrame() throws HeadlessException {
        setSize(750, 400);
        setTitle("Yurii Andrieiev Work Occurrences App for DataArt");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JSplitPane splitPane = new JSplitPane();
        splitPane.add(new JScrollPane(workOccurrencesTable), JSplitPane.LEFT);
        splitPane.add(createInputPanel(), JSplitPane.RIGHT);
        add(splitPane);
        splitPane.setDividerLocation(350);
        refreshOccurrenceTable();
    }

    private void refreshOccurrenceTable(){
        final String text = inputTextArea.getText();
        final List<WordOccurrence> wordOccurrences = new WorkOccurrenceCounter().countWords(text);
        final WordOccurrenceTableModel newModel = new WordOccurrenceTableModel(wordOccurrences);
        workOccurrencesTable.setModel(newModel);
    }

    private JPanel createInputPanel(){
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                refreshOccurrenceTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                refreshOccurrenceTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);
        inputPanel.add(new JLabel("Please enter your text in the area below: "), BorderLayout.NORTH);
        inputPanel.add(createClearPanel(), BorderLayout.SOUTH);
        return inputPanel;
    }

    private JPanel createClearPanel(){
        final JPanel clearPanel = new JPanel();
        final JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> {
            inputTextArea.setText(null);
            refreshOccurrenceTable();
        });
        clearPanel.add(btnClear);
        return clearPanel;
    }

    private void setDefaultText(){
        try {
            final StringBuilder wholeText = new StringBuilder();
            Files.lines(Paths.get("src/main/resources/hpmor.txt"))
                    .forEach(wholeText::append);
            inputTextArea.setText(wholeText.toString());
            refreshOccurrenceTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultText();
        mainFrame.setVisible(true);
    }
}
