import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> unitSelector;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null); // Center the window

        inputField = new JTextField(10);
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitSelector = new JComboBox<>(units);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        add(new JLabel("Enter Temperature: "));
        add(inputField);
        add(unitSelector);
        add(convertButton);
        add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double temp = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitSelector.getSelectedItem();
            double celsius, fahrenheit, kelvin;

            switch (selectedUnit) {
                case "Celsius":
                    celsius = temp;
                    fahrenheit = (celsius * 9 / 5) + 32;
                    kelvin = celsius + 273.15;
                    break;
                case "Fahrenheit":
                    fahrenheit = temp;
                    celsius = (fahrenheit - 32) * 5 / 9;
                    kelvin = celsius + 273.15;
                    break;
                case "Kelvin":
                    kelvin = temp;
                    celsius = kelvin - 273.15;
                    fahrenheit = (celsius * 9 / 5) + 32;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid unit");
            }
            
            resultLabel.setText(String.format("C: %.2f | F: %.2f | K: %.2f", celsius, fahrenheit, kelvin));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConverter converter = new TemperatureConverter();
            converter.setVisible(true);
        });
    }
}
