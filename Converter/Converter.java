
package converter;

import java.util.Scanner;

public class Converter {
    
    public static StringBuilder IEEE754(float originNum) {
        String signBit;
        
        int wholeNum = (int) originNum;
        float decimalNum = originNum - wholeNum;
        
        // #1: Decide sign bit
        if (wholeNum >= 0) {
            signBit = "0";
        } else {
            signBit = "1";
            wholeNum = Math.abs(wholeNum);
        }
        
        // #2: Find binary representation of whole number
        StringBuilder wholeNumBinary = new StringBuilder();
        if (wholeNum > 1) {
            while (wholeNum != 0) {
                int remainder = wholeNum % 2;
                // System.out.println("Remainder:" + remainder);
                wholeNum = wholeNum / 2;
                wholeNumBinary.append(String.valueOf(remainder));
            }
        }
        StringBuilder reverseWholeNumBinary = wholeNumBinary.reverse();
        System.out.println(reverseWholeNumBinary);
        
        // #3: Find binary representation of decimal part
        
        
        StringBuilder finalResult = new StringBuilder();
        // append sign bit, exponential part, and mantissa
        finalResult.append(signBit);
        finalResult.append(reverseWholeNumBinary);
        return finalResult;
    }
    
    public static void main(String[] args) {
        Scanner thisScanner = new Scanner(System.in);
        float userNumber = thisScanner.nextFloat();
        System.out.println("Input: " + userNumber);
        StringBuilder result = IEEE754(userNumber);
        System.out.println("Main result: " + result);
    }
    
}
