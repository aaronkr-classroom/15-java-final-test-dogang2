import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField; // 결과를 표시하는 텍스트 필드
    private String currentInput = ""; // 현재 입력된 값을 저장
    private String operator = ""; // 현재 선택된 연산자를 저장
    private double firstNumber = 0; // 첫 번째 숫자를 저장

    public Calculator() {
        setTitle("계산기");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 메뉴 바 설정
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

        // 디스플레이 필드 설정
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        // 버튼 패널 설정
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("0123456789".contains(command)) {
            // 숫자 버튼이 눌렸을 때
            currentInput += command;
            displayField.setText(currentInput);
        } else if ("+-*/".contains(command)) {
            // 연산자 버튼이 눌렸을 때
            firstNumber = Double.parseDouble(currentInput);
            operator = command;
            currentInput = "";
        } else if (command.equals("C")) {
            // AC 버튼이 눌렸을 때
            resetCalculator();
        } else if (command.equals("=")) {
            // = 버튼이 눌렸을 때
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
