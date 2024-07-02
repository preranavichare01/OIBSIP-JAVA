import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class NumberGuessingGameUI extends JFrame {
    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel rangeLabel;
    private JButton guessButton;
    private JButton resetButton;
    private int numberToGuess;
    private int numberOfAttempts;
    private final int maxAttempts = 5;
    private int lowerBound = 1;
    private int upperBound = 100;

    public NumberGuessingGameUI() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setBounds(50, 20, 300, 20);
        panel.add(instructionLabel);

        rangeLabel = new JLabel("Range: 1 to 100");
        rangeLabel.setBounds(50, 50, 300, 20);
        panel.add(rangeLabel);

        guessField = new JTextField();
        guessField.setBounds(50, 80, 150, 25);
        panel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(50, 120, 100, 25);
        panel.add(guessButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(160, 120, 100, 25);
        panel.add(resetButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 160, 300, 25);
        panel.add(messageLabel);

        guessButton.addActionListener(new GuessButtonListener());
        resetButton.addActionListener(new ResetButtonListener());

        startNewGame();
    }

    private void startNewGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfAttempts = 0;
        lowerBound = 1;
        upperBound = 100;
        guessField.setText("");
        messageLabel.setText("");
        rangeLabel.setText("Range: " + lowerBound + " to " + upperBound);
        guessButton.setEnabled(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guessText = guessField.getText();
            try {
                int guess = Integer.parseInt(guessText);
                numberOfAttempts++;

                if (numberOfAttempts > maxAttempts) {
                    messageLabel.setText("No attempts left! The number was " + numberToGuess);
                    guessButton.setEnabled(false);
                    return;
                }

                if (guess < lowerBound || guess > upperBound) {
                    messageLabel.setText("Please enter a number between " + lowerBound + " and " + upperBound + ".");
                } else if (guess < numberToGuess) {
                    lowerBound = guess + 1;
                    messageLabel.setText("Too low! Attempts left: " + (maxAttempts - numberOfAttempts));
                } else if (guess > numberToGuess) {
                    upperBound = guess - 1;
                    messageLabel.setText("Too high! Attempts left: " + (maxAttempts - numberOfAttempts));
                } else {
                    messageLabel
                            .setText("Congratulations! You guessed the number in " + numberOfAttempts + " attempts.");
                    guessButton.setEnabled(false);
                }
                rangeLabel.setText("Range: " + lowerBound + " to " + upperBound);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input! Please enter a valid number.");
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameUI().setVisible(true);
            }
        });
    }
}
