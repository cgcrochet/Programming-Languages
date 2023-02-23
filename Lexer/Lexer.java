package Lexer;

// Cameron Crochet
// 892681239

// Lexical analyzer for CSC 4101 (Programming Languages at LSU)
// A lexical analyzer converts characters in the source code into lexical
//  units/tokens as the first stage of compilation
// Compiled languages execute faster and more efficiently than interpreted
//  languages

// The language analyzed here was created by Dr. Nash Mahmoud
// All code written by Cameron Crochet | crochetcameron@gmail.com

// To test, uncomment the main method, and upload your source program in a file
//  named testcase.txt

// Developed with the NetBeans IDE

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashMap;

public class Lexer {
    
    public static void Tokenize(String fileName) throws IOException {
        // Set up lookup table for Symbol-Token key-value pairs
        // Commented out entries represent special cases that a HashMap alone 
        //  cannot support. They are left here to help first time readers
        //  understand the language
        
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("if", "IF");
        tokenMap.put("for", "FOR");
        tokenMap.put("while", "WHILE");
        tokenMap.put("procedure", "PROC");
        tokenMap.put("return", "RETURN");
        tokenMap.put("int", "INT");
        tokenMap.put("else", "ELSE");
        tokenMap.put("do", "DO");
        tokenMap.put("break", "BREAK");
        tokenMap.put("end", "END");
        //tokenMap.put("=", "ASSIGN");
        //tokenMap.put("+", "ADD_OP");
        tokenMap.put("-", "SUB_OP");
        tokenMap.put("*", "MUL_OP");
        tokenMap.put("/", "DIV_OP");
        tokenMap.put("%", "MOD_OP");
        //tokenMap.put(">", "GT");
        //tokenMap.put("<", "LT");
        //tokenMap.put(">=", "GE");
        //tokenMap.put("<=", "LE");
        //tokenMap.put("++", "INC");
        tokenMap.put("(", "LP");
        tokenMap.put(")", "RP");
        tokenMap.put("{", "LB");
        tokenMap.put("}", "RB");
        tokenMap.put("|", "OR");
        tokenMap.put("&", "AND");
        //tokenMap.put("==", "EE");
        tokenMap.put("!", "NEG");
        tokenMap.put(",", "COMMA");
        tokenMap.put(";", "SEMI");
        
        // Read in one character at a time
        File f = new File(fileName);
        FileReader fr = new FileReader(f);  
        BufferedReader br = new BufferedReader(fr);
        
        int c;
        String lexString = "";
        // lastChar represents the previous character
        char lastChar = ' ';
        // lastLastChar represents the character before the previous character
        char lastLastChar = ' ';
        
        while ((c = br.read()) != -1) {
            char curChar = (char) c;
            boolean isLetterOrDigit = Character.isLetterOrDigit(curChar);
            boolean isWhitespace = Character.isWhitespace(curChar);
            
            // Evalute special cases that are excluded from the HashMap
            if (lastLastChar == '+' && lastChar == '+' && curChar != '+') {
                System.out.println("INC");
            }
            
            if (lastLastChar != '+' && lastChar == '+' && curChar != '+') {
                System.out.println("ADD_OP");
            }
            
            if (lastLastChar == '=' && lastChar == '=' && curChar != '=') {
                System.out.println("EE");
            }
                
            if (lastChar == '>' && curChar == '=') {
                System.out.println("GE");
            }
                    
            if (lastChar == '>' && curChar != '='){
                System.out.println("GT");
            }
                
            if (lastChar == '<' && curChar == '=') {
                System.out.println("LE");
            }
                
            if (lastChar == '<' && curChar != '=') {
                System.out.println("LT");
            }
            
            if ((lastLastChar != '>' && lastLastChar != '<' && lastLastChar != '=') && lastChar == '=' && curChar != '=') {
                System.out.println("ASSIGN");
            }
            
            if (!isLetterOrDigit) {
                // currChar must be a symbol or a whitespace here.
                // In either case, we want to evaluate if the current lexString
                //  is a valid token.
                if (tokenMap.containsKey(lexString)) {
                    System.out.println(tokenMap.get(lexString));
                } else if (!lexString.isEmpty()) {
                    // Determine if number or identifier
                    boolean isFirstDigit = Character.isDigit(lexString.charAt(0));
                    
                    // Check for invalid identifiers since beginning an
                    //  identifier with a digit is not permitted.
                    if (isFirstDigit) {
                        boolean valid = true;
                        for (int i = 1; i < lexString.length()-1; i++) {
                            if (Character.isLetter(lexString.charAt(i))) {
                                valid = false;
                            }
                        }
                        
                        if (!valid && curChar != '+') {
                            System.out.println("SYNTAX ERROR: INVALID IDENT NAME");
                            System.exit(0);
                        } else {
                            System.out.println("INT_CONST");
                        }
                    } else {
                        System.out.println("IDENT");
                    }
                }
                
                String myCharToString = Character.toString(curChar);
                // If currChat is not a whitespace, a letter, or a digit, it
                //  must be a symbol
                if (!isWhitespace) {
                    if (tokenMap.containsKey(myCharToString)) {
                        System.out.println(tokenMap.get(myCharToString));
                    }
                }
                lexString = "";
                
            } else {
                lexString += curChar;
            }
            
            lastLastChar = lastChar;
            lastChar = curChar;
        }
    }
    
//    Driver code for testing
//    public static void main(String[] args) throws IOException {
//        Tokenize("testcase.txt");
//    }
    
}
