/*
Project:

compilation:
	double click run-project.cmd in graphs folder or do the following
	graphspackage imports: javac ./graphspackage/*.java
	main: javac graphs.java
execution:
	java Graphs.java
*/
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import graphspackage.AdjacencyMatrix;
import graphspackage.AdjacencyList;

public class Graphs{
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public Graphs() {
        prepareGui();
    }
	public static void main(String[] args){
		Graphs graphGui = new Graphs();
		graphGui.fileChooser();		
	}
	 private void prepareGui() {
        mainFrame = new JFrame("Menu");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void dataStructureSelect(String fileName) {
        headerLabel.setText("Select a data structure to use");

        JButton aMatrixButton = new JButton("Adjacency matrix");
        JButton aListButton = new JButton("Adjacency list");
        aListButton.setHorizontalTextPosition(SwingConstants.LEFT); //what does this do?

        aMatrixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Adjacency matrix selected.");
                try {
                    AdjacencyMatrix aM = new AdjacencyMatrix(fileName);
                } catch (IOException ex) {
                    Logger.getLogger(Graphs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        aListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Adjacency list selected.");
                try {
                    AdjacencyList aJ = new AdjacencyList(fileName);
                } catch (IOException ex) {
                    Logger.getLogger(Graphs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Adjacency matrix selected.");
                fileChooser();
            }
        });

        controlPanel.removeAll();
        controlPanel.add(aMatrixButton);
        controlPanel.add(aListButton);
        controlPanel.add(menuButton);

        mainFrame.setVisible(true);
    }

    private void fileChooser() {
        headerLabel.setText("Choose a file or return to menu");
        final JFileChooser fileDialog = new JFileChooser();
        JButton showFileDialogButton = new JButton("Open File");
        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(mainFrame);
                String fileName = "n";
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fileDialog.getSelectedFile();
                    fileName = file.getName();
                    statusLabel.setText("File Selected: " + fileName);
                } else {
                    statusLabel.setText("Open command cancelled by user.");
                }
                dataStructureSelect(fileName);
            }
        });

        controlPanel.removeAll();
        controlPanel.add(showFileDialogButton);

        mainFrame.setVisible(true);
    }
}