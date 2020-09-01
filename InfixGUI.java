/*
 * Rhea Prohaska
 * CMSC 350 Project 1
 * The class contains the components for the GUI window and actions performed
 */
package Prohaska_350P1;

import java.awt.*;
import static java.awt.FlowLayout.*;
import java.awt.event.* ;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author HOME
 */
public class InfixGUI extends JFrame {
    //GUI Components
    JLabel infix = new JLabel("Enter Infix Expression: ");
    JTextField infixInput = new JTextField(25);
    JButton evaluate = new JButton("Evaluate");
    JLabel result = new JLabel("Result ", SwingConstants.LEFT);
    JTextField resultOutput = new JTextField(25);
    //Variables to get use in InfixEvaluation class
    String equation;
    String evaluationResult;
    //New instance of the infix evaluation class
    InfixEvaluation infixEval = new InfixEvaluation();

    public InfixGUI() {
        super("Infix Expression Evaluator");
        JPanel inputPanel = new JPanel(new FlowLayout(CENTER));
        JPanel outputPanel = new JPanel(new FlowLayout(CENTER));
        setLayout(new GridLayout(2,1,20,20));
        add(inputPanel);
        add(outputPanel);
        inputPanel.add(infix);
        inputPanel.add(infixInput);
        inputPanel.add(evaluate);
        outputPanel.add(result);
        outputPanel.add(resultOutput);
        resultOutput.setEditable(false);

        setVisible(true);
        setSize(450,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    //ActionListener for when evaluate button is pressed. 
    evaluate.addActionListener((ActionEvent e) -> {
        equation = infixInput.getText();
            try {
                //Input of expression textfield is used in the infix class
                evaluationResult = infixEval.evaluation((equation));
            } catch (DivisionException ex) {
                JOptionPane.showMessageDialog(null, "Cannot Divide by Zero!", "Division"
                  + "Error", JOptionPane.ERROR_MESSAGE);
            }
        resultOutput.setText(evaluationResult); //Set the result jtextfield
    });
    }

    public static void main(String[] args) {
        new InfixGUI();
    }  
}

