package Prohaska_350P1;
/*
 * Rhea Prohaska
 * CMSC 350 Project 1
 * The class uses the input of the expression, tokenizing the string and placing 
 * the results in the correct stack. The calculations and precedence check are 
 * maintained within their own method to avoid repetition of code.
 */

import java.util.*;
import javax.swing.JOptionPane;

class InfixEvaluation {
 // Stack variables
  private Stack<String> operand = new Stack<>();
  private Stack<String> operator = new Stack<>();

    //Tokenize the string containing the expression, while more tokens, get next
    private List<String> tokenizer(String input) {
    List<String> tokens = new ArrayList<>();
    tokens.add(Character.toString(input.charAt(0)));
    // Iterates through string to add to tokens
    for (int i = 1; i < input.length(); i++) {
      char first = input.charAt(i);
      char last = input.charAt(i - 1);
      if (Character.isDigit(first) && Character.isDigit(last)) {
        int single = (tokens.size() - 1);
        tokens.set(single, tokens.get(single) + first);
      } else { 
        tokens.add(Character.toString(first));
      }
    }
    return tokens;
  }

  String evaluation(String input) throws DivisionException{
    List<String> tokens = tokenizer(input);

    for (String token : tokens) {

      // If it is an operand
      if (token.matches("[0-9]+")) {
        // push to operand stack
        operand.push(token);
      }
      // If it is a left parenthesis
      else if (token.equals("(")) {
        // push to the operator stack
        operator.push(token);
      }
      // If it is a right parenthesis
      else if (token.equals(")")) {
        // while top of operator is not a left parenthesis
        while (!operator.peek().equals("(")) {
          // Pop two operands & operator, perform calculation push into operand
          operand.push(calculation(operand.pop(), operator.pop(), operand.pop()));
        }
        operator.pop();
      }

      // If it is an operator
      else if (token.matches("[*/+\\-]")){
        // while operator stack is not empty, and precedence is checked
        while (!operator.empty() && precedence(operator.peek()) >= precedence(
            token)) {
            //Pop two operands & operator, perform calculation and push into stack
            operand.push(calculation(operand.pop(), operator.pop(), operand.pop()));
        }
        // Adds to operator stack
        operator.push(token);
      }
    }

    // While operator stack is not empty, calculates until complete
    while (!operator.empty()) {
      operand.push(calculation(operand.pop(), operator.pop(), operand.pop()));
    }
    //Final result is at the top.
    return operand.pop();
  }
  /* Using a switch statement, the calcualtion is performed depending on the 
  operator string.
  */
  private String calculation(String num2, String operator, String num1) throws DivisionException {
    int x = Integer.parseInt(num1);
    int y = Integer.parseInt(num2);
    int result = 0;

    switch (operator) {
      case "+":
        result = x + y;
        break;
      case "-":
        result = x - y;
        break;
      case "*":
        result = x * y;
        break;
      case "/":
        if (y == 0) {
          JOptionPane.showMessageDialog(null, "Cannot Divide by Zero!", "Division"
                  + "Error", JOptionPane.ERROR_MESSAGE);
        }
        result = x / y;
        break;
    }
    return Integer.toString(result);
  }
    /* The following method is used to determine precedence or the priority of
  the calculations. Multiplication and division first, addition and substraction 
  last*/
    private int precedence(String operator) {
        int precedence = 0;

        switch (operator) {
            case "+":
            case "-":
                precedence = 1;
            break;
            case "*":
            case "/":
                precedence = 2;
        }
        return precedence;
    }

    }


    




