import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirtualCalculator {

    private JFrame frame;
    private JTextField textField;
    private double num1, num2, result;
    private char operator;

    public VirtualCalculator() {
        initialize();
    }

    private void initialize() {
        // Frame settings
        frame = new JFrame("Virtual Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        // Text field
        textField = new JTextField();
        textField.setBounds(20, 20, 340, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);
        frame.add(textField);

        // Buttons
        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "C", "0", "=", "+"
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 100, 340, 340);
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = ((JButton) e.getSource()).getText();

            switch (command) {
                case "C":
                    textField.setText("");
                    num1 = num2 = result = 0;
                    break;
                case "=":
                    num2 = Double.parseDouble(textField.getText());
                    switch (operator) {
                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '*': result = num1 * num2; break;
                        case '/': result = num1 / num2; break;
                    }
                    textField.setText(String.valueOf(result));
                    break;
                case "+": case "-": case "*": case "/":
                    num1 = Double.parseDouble(textField.getText());
                    operator = command.charAt(0);
                    textField.setText("");
                    break;
                default:
                    textField.setText(textField.getText() + command);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new VirtualCalculator();
    }
}