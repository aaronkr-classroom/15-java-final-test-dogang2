import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField; 
    private JPanel buttonPanel; 
    private JTextField calculator;
    private JTextField jmenubar; 
    private JLabel imageLabel1; 
    private JLabel imageLabel2; 
    private String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "AC", "=", "/"
    };
    private JButton[] buttons = new JButton[buttonLabels.length];

    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;

    public Calculator() {
        setTitle("계산기");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(newItem);
        fileMenu.add(openItem);


        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        JPanel imagePanel = new JPanel(new GridLayout(1, 4, 10, 10));
        calculator = new JTextField(10);
        jmenubar = new JTextField(10);
        imageLabel1 = new JLabel();
        imageLabel2 = new JLabel();
        imageLabel1.setHorizontalAlignment(JLabel.CENTER);
        imageLabel2.setHorizontalAlignment(JLabel.CENTER);

        imagePanel.add(new JLabel("이미지 1 파일 이름:"));
        imagePanel.add(calculator);
        imagePanel.add(new JLabel("이미지 2 파일 이름:"));
        imagePanel.add(jmenubar);

        // 디스플레이 필드 설정
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);

        // 버튼 패널 설정
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); 
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        JPanel displayPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        displayPanel.add(imageLabel1);
        displayPanel.add(imageLabel2);

        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.SOUTH);
        add(displayPanel, BorderLayout.EAST);
    }


    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();


        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            currentInput += command;
            displayField.setText(currentInput);
        } 

        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            firstNumber = Double.parseDouble(currentInput);
            operator = command;
            currentInput = "";
        } 

        else if (command.equals("AC")) {
            currentInput = "";
            operator = "";
            firstNumber = 0;
            displayField.setText("");
        } 

        else if (command.equals("=")) {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        displayField.setText("오류: 0으로 나눌 수 없습니다.");
                        return;
                    }
                    break;
            }
            displayField.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        }

        if (e.getSource() == calculator || e.getSource() == jmenubar) {
            String imageName1 = calculator.getText();
            String imageName2 = jmenubar.getText();

            if (!imageName1.isEmpty()) {
                ImageIcon imageIcon1 = new ImageIcon(imageName1);
                imageLabel1.setIcon(imageIcon1);
            } else {
                imageLabel1.setIcon(null);
            }

            if (!imageName2.isEmpty()) {
                ImageIcon imageIcon2 = new ImageIcon(imageName2);
                imageLabel2.setIcon(imageIcon2);
            } else {
                imageLabel2.setIcon(null);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
