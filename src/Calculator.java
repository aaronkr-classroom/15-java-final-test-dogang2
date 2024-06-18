import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField; 
    private String currentInput = "";
    private String operator = ""; 
    private double firstNumber = 0; 

    public Calculator() {
        setTitle("계산기");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.add(new JMenuItem("New"));
        fileMenu.add(new JMenuItem("Open"));

        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        helpMenu.add(new JMenuItem("About"));

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        String[] buttonLabels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "C", "=", "/"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("0123456789".contains(command)) {
          
            currentInput += command;
            displayField.setText(currentInput);
        } else if ("+-*/".contains(command)) {
          
            firstNumber = Double.parseDouble(currentInput);
            operator = command;
            currentInput = "";
        } else if (command.equals("C")) {
          
            resetCalculator();
        } else if (command.equals("=")) {
          
            calculateResult();
        }
    }

    private void resetCalculator() {
        currentInput = "";
        operator = "";
        firstNumber = 0;
        displayField.setText("");
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(currentInput);
        double result = 0;
        switch (operator) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> result = (secondNumber != 0) ? firstNumber / secondNumber : Double.NaN;
        }
        int intResult = (int) result;
        displayField.setText(String.valueOf(intResult));
        currentInput = String.valueOf(intResult);
        operator = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }
}
