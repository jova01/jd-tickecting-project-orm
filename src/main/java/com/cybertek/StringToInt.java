package com.cybertek;

public class StringToInt {

    public static void main(String[] args) {

        int number = stringToint("-");

        System.out.println(number);
    }

    public static int stringToint(String str) {
        boolean strDefined = str != null && str.length() > 0;
        int num = 0;
        boolean negativeNumber = false;

        if (strDefined) {
            if (str.charAt(0) == '-') {
                negativeNumber = true;
                str = str.substring(1);
            }
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i))) {
                    num *= 10;
                    num += str.charAt(i) - '0';
                }else {
                    throw new ArithmeticException("All characters should be a number!");
                }
            }
        }

        if (negativeNumber) num = -num;

        return num;
    }
}
