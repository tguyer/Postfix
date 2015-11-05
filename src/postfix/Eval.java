/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postfix;

import java.util.Stack;
import java.util.Scanner;
/**
 *
 * @author Travis
 */
public class Eval {
    
    private final static char ADD = '+';
        private final static char SUBTRACT = '-';
        private final static char MULTIPLY = '*';
        private final static char DIVIDE = '/';
        Stack<Integer> evaluator;
       
    /**
     * 
     * manages all operations on expressions that have been entered.
     */    
    public void solve(){
        
        int op1, op2, result = 0;
        
        evaluator = new Stack<Integer>();
        
        String expression = getExpression();
        String token;
        Scanner parser = new Scanner(expression);
        
        while (parser.hasNext()){
            
            token = parser.next();
            if (isOperator(token)){
                op2 = (evaluator.pop()).intValue();
                op1 = (evaluator.pop()).intValue();
                result = evaluateSingleOperator(token.charAt(0), op1, op2);
                evaluator.push(new Integer(result));
            }
            else
                evaluator.push(new Integer(Integer.parseInt(token)));
        }
        
        int errorCode = isValid(evaluator);
        
        if (errorCode != 0)
            giveError(errorCode);
        else
            updateGui(result);
    
}
    /**
     * 
     * @return a String which is gathered from the text in the jTextField1.
     */
    private String getExpression(){

            return Gui.jTextField1.getText();     
    }
    /**
     * 
     * @param token
     * @return a boolean which tells the program if the given String is an operator
     */
    private boolean isOperator(String token){
        
        return ( token.equals("+") || token.equals("-") ||
                 token.equals("*") || token.equals("/"));
    }
    /**
     * 
     * @param operation 
     * @param op1
     * @param op2
     * @return result an integer with the final result of the expression.
     */
    private int evaluateSingleOperator(char operation, int op1, int op2){
        
        
        int result = 0;
        switch (operation){
            case ADD:
                result = op1 + op2;
                break;
            case SUBTRACT:
                result = op1 - op2;
                break;
            case MULTIPLY:
                result = op1 * op2;
                break;
            case DIVIDE:
                result = op1 / op2;
        }
        return result;
    }
    /**
     * 
     * @param result 
     */
    private void updateGui(int result){
        
        Gui.jLabel3.setText(""+ result);
    }
    /**
     * 
     * @param errorCode 
     */
    private void giveError(int errorCode){
        
        if (errorCode == 1)
         Gui.jLabel3.setText("ERROR: More operands than operations");
       
        if (errorCode == 2)
         Gui.jLabel3.setText("ERROR: Please type something in...");
        
    }
    /**
    * 
    * @param evaluator
    * @return valid an integer that determines what type of error the program
    * is running into.
    * 
    */
    private int isValid(Stack<Integer> evaluator){
        int stackSize = 0;
        int valid = 0;
        
        for (int count : evaluator)
            ++stackSize;
        
        if (stackSize > 1)
            valid = 1;
        
        
        return valid;
}
}