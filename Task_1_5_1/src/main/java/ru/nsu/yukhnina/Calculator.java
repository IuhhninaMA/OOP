package ru.nsu.yukhnina;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
* Class to calculate expressions in poland notation.
*/
public class Calculator {

    ArrayDeque<String> operations;
    ArrayDeque<Double> currNum;

    /**
     * Operations it is all current expression.
     * currNum it is stack contains only numbers to calculate expression.
     */
    public Calculator() {
        operations = new ArrayDeque<>();
        currNum = new ArrayDeque<>();
    }

    /**
     * I think that bad practice write all in main,
     * I'll fix it in future.
     * Stopped in user input "stop".
     */
    public static void main(String[] args) {
        Calculator bulbulyator = new Calculator();
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        while (!str.equals("stop")) {
            String[] expression = str.split(" ", 0);
            for (String s : expression) {
                bulbulyator.operations.push(s);
            }
            while (!bulbulyator.operations.isEmpty()) {
                switch (bulbulyator.operations.peek()) {
                    case ("+"): {
                        bulbulyator.currNum
                                .push(Plus.calc(bulbulyator.currNum.pop()
                                        , bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("-"): {
                        bulbulyator.currNum
                                .push(Minus.calc(bulbulyator.currNum.pop()
                                        , bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("*"): {
                        bulbulyator.currNum
                                .push(Mult.calc(bulbulyator.currNum.pop()
                                        , bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("/"): {
                        bulbulyator.currNum
                                .push(Div.calc(bulbulyator.currNum.pop()
                                        , bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("sin"): {
                        bulbulyator.currNum.push(Sin.calc(bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("cos"): {
                        bulbulyator.currNum.push(Cos.calc(bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("log"): {
                        bulbulyator.currNum.push(Log.calc(bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("pow"): {
                        bulbulyator.currNum
                                .push(Pow.calc(bulbulyator.currNum.pop(), bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    case ("sqrt"): {
                        bulbulyator.currNum.push(Sqrt.calc(bulbulyator.currNum.pop()));
                        bulbulyator.operations.pop();
                        break;
                    }
                    default: {
                        bulbulyator.currNum.push(Double.parseDouble(bulbulyator.operations.pop()));
                    }
                }
            }
            System.out.println(bulbulyator.currNum.pop());
            str = in.nextLine();
        }
        System.out.println("Calculator is off");
    }
}