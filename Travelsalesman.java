import javax.swing.*;
import java.awt.event.*;

public class Travelsalesman extends JDialog {
    private JPanel contentPane;
    private JButton SUBMITButton;
    private JLabel label;
    private JRadioButton secondsRadioButton;
    private JRadioButton minutesRadioButton;
    private JRadioButton hoursRadioButton;
    private JTextArea textArea1;
    private JTextField textField1;
    private JComboBox comboBox2;
    private JButton QUITButton;
    private JTextField textField2;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Travelsalesman() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Alorithms and Complexity");

        SUBMITButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSUBMITButton();
            }
        });

        QUITButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onQUITButton();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onQUITButton();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onQUITButton();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSUBMITButton() {
        String name = textField1.getText();
        String occupation = (String) comboBox2.getSelectedItem();
        String age = textField2.getText();
        int age2 = Integer.parseInt(age);

        String timeMeasurement = "";
        if (secondsRadioButton.isSelected()) {
            timeMeasurement = "Seconds";
        } else if (minutesRadioButton.isSelected()) {
            timeMeasurement = "Minutes";
        } else if (hoursRadioButton.isSelected()) {
            timeMeasurement = "Hours";
        }

        int numOfPlaces = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of places:"));

        int[][] distances = new int[numOfPlaces][numOfPlaces];
        String[] placeNames = new String[numOfPlaces];

        for (int i = 0; i < numOfPlaces; i++) {
            for (int j = 0; j < numOfPlaces; j++) {
                if (i != j) {
                    int distance = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter distance from Place " + (i + 1) + " to Place " + (j + 1) + " (" + timeMeasurement + "):"));
                    distances[i][j] = distance;
                }
            }
        }

        for (int i = 0; i < numOfPlaces; i++) {
            placeNames[i] = JOptionPane.showInputDialog(null, "Enter name for Place " + (i + 1) + ":");
        }

        String shortestRoute = calculateShortestRoute(distances, placeNames);
        String longestRoute = calculateLongestRoute(distances, placeNames);

        String message = "Name: " + name + "\nAge: " + age2 + "\nOccupation: " + occupation + "\nTime Measurement: " + timeMeasurement + "\n" + "\nShortest Route:\n" + shortestRoute  + "\n\nLongest Route:\n" + longestRoute;
        JOptionPane.showMessageDialog(null, message, "Information Message", JOptionPane.INFORMATION_MESSAGE);

        textArea1.append("Shortest Route:\n" + shortestRoute + "\n\nLongest Route:\n" + longestRoute + "\n\n");
        textField1.setText("");
        textField2.setText("");
        comboBox2.setSelectedIndex(0);
        secondsRadioButton.setSelected(true);
    }

    private String calculateShortestRoute(int[][] distances, String[] placeNames) {
        int n = distances.length;
        int[] route = new int[n];
        for (int i = 0; i < n; i++) {
            route[i] = i;
        }

        int[] bestRoute = route.clone();
        int minDistance = Integer.MAX_VALUE;

        do {
            int currentDistance = 0;
            for (int i = 0; i < n - 1; i++) {
                currentDistance += distances[route[i]][route[i + 1]];
            }
            currentDistance += distances[route[n - 1]][route[0]]; // Return to the starting point

            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                bestRoute = route.clone();
            }
        } while (nextPermutation(route));

        StringBuilder shortestRoute = new StringBuilder();
        for (int i = 0; i < n; i++) {
            shortestRoute.append(placeNames[bestRoute[i]]);
            if (i < n - 1) {
                shortestRoute.append(" -> ");
            }
        }
        shortestRoute.append(" -> ").append(placeNames[bestRoute[0]]); // Return to the starting point
        shortestRoute.append("\nTotal Distance: ").append(minDistance);
        return shortestRoute.toString();
    }

    private String calculateLongestRoute(int[][] distances, String[] placeNames) {
        int n = distances.length;
        int[] route = new int[n];
        for (int i = 0; i < n; i++) {
            route[i] = i;
        }

        int[] longestRoute = route.clone();
        int maxDistance = Integer.MIN_VALUE;

        do {
            int currentDistance = 0;
            for (int i = 0; i < n - 1; i++) {
                currentDistance += distances[route[i]][route[i + 1]];
            }
            currentDistance += distances[route[n - 1]][route[0]]; // Return to the starting point

            if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
                longestRoute = route.clone();
            }
        } while (nextPermutation(route));

        StringBuilder longestRouteStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            longestRouteStr.append(placeNames[longestRoute[i]]);
            if (i < n - 1) {
                longestRouteStr.append(" -> ");
            }
        }
        longestRouteStr.append(" -> ").append(placeNames[longestRoute[0]]); // Return to the starting point
        longestRouteStr.append("\nTotal Distance: ").append(maxDistance);
        return longestRouteStr.toString();
    }

    private boolean nextPermutation(int[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        reverse(array, i + 1);
        return true;
    }

    private void reverse(int[] array, int start) {
        int i = start;
        int j = array.length - 1;
        while (i < j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    private void onQUITButton() {
        dispose();
    }

    public static void main(String[] args) {
        Travelsalesman dialog = new Travelsalesman();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        label = new JLabel(new ImageIcon("PHOTO4.png"));
    }
}
