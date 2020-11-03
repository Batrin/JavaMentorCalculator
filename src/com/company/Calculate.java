package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Calculate {
    public void stringAnalysis(String userInput){
        userInput = userInput.toUpperCase();
        char[] strArray = userInput.toCharArray();
        String firstNumberString = "", secondNumberString = "", action = "";
        int firstNumber = 0, secondNumber = 0;
        int firstNumberFlag = 0, secondNumberFlag = 0;
        int iterator = 0, actionPosition = 0;
        while(iterator < strArray.length){
            if(strArray[iterator] == '+' || strArray[iterator] == '-' || strArray[iterator] == '*' || strArray[iterator] == '/'){
                action += strArray[iterator];
                actionPosition = iterator + 1;
                break;
            }
            else{
                firstNumberString += strArray[iterator];
                if(strArray[iterator] >= 48 && strArray[iterator] <= 57){
                    firstNumberFlag = 1;
                }
                else if(strArray[iterator] == 'I' || strArray[iterator] == 'V' || strArray[iterator] == 'X'){
                    firstNumberFlag = 0;
                }
                else{
                    firstNumberFlag = -1;
                }
            }
            iterator++;
        }
        while(actionPosition < strArray.length){
            secondNumberString += strArray[actionPosition];
            if(strArray[actionPosition] >= 48 && strArray[actionPosition] <= 57){
                secondNumberFlag = 1;
            }
            else if(strArray[actionPosition] == 'I' || strArray[actionPosition] == 'V' || strArray[actionPosition] == 'X'){
                secondNumberFlag = 0;
            }
            else{
                secondNumberFlag = -1;
                throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 10");
            }
            actionPosition++;
        }

        if(firstNumberFlag == 1 && secondNumberFlag == 1){
            System.out.println("Оба числа арабские");
            firstNumber = Integer.parseInt(firstNumberString);
            secondNumber = Integer.parseInt(secondNumberString);
            try {
                if((firstNumber > 10 || firstNumber < 1) || (secondNumber > 10 || secondNumber < 1) || (firstNumber % 1 !=0) || (secondNumber % 1 != 0)){
                    throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 10 и должно быть целыми");
                }
                int result = solve(firstNumber,secondNumber,action);
                System.out.println("Результат : " + result);
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        else if(firstNumberFlag == 0 && secondNumberFlag == 0){
            System.out.println("Оба числа римские");
            firstNumber = RomanNumberToArabic(firstNumberString);
            secondNumber = RomanNumberToArabic(secondNumberString);
            try {
                if((firstNumber > 10 || firstNumber < 1) || (secondNumber > 10 || secondNumber < 1)){
                    throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 10");
                }
                int result = solve(firstNumber,secondNumber,action);
                System.out.println("Результат : " + ArabicNumberToRoman(result));
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        else if((firstNumberFlag == 1 && secondNumberFlag == 0) || (firstNumberFlag == 0 && secondNumberFlag == 1)){
            throw new IllegalArgumentException("Нельзя выполнять операции с разными видами чисел!");
        }
    }

    /* Перечисление для перевода из римских цифр в арабские и наоборот */
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    /* Метод для преобразования из римских цифр в арабские */
    public int RomanNumberToArabic(String input){
        String romanNumber = input.toUpperCase();
        int resultNumber = 0, i = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        while(romanNumber.length() > 0 && i < romanNumerals.size()){
            RomanNumeral sym = romanNumerals.get(i);
            if(romanNumber.startsWith(sym.name())){
                resultNumber += sym.getValue();
                romanNumber = romanNumber.substring(sym.name().length());
            }
            else {
                i++;
            }
        }
        return resultNumber;
    }

    /* Метод для преобразования из арабских цифр в римские */
    public String ArabicNumberToRoman(int inputNumber){
        int i = 0;
        String resultString ="";
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        StringBuilder romanBuilder = new StringBuilder();

        while(inputNumber > 0 && i < romanNumerals.size()){
            RomanNumeral current = romanNumerals.get(i);
            if(current.getValue() <= inputNumber){
                romanBuilder.append(current.name());
                inputNumber -= current.getValue();
            }
            else {
                i++;
            }
        }
        resultString = romanBuilder.toString();
        return resultString;
    }
    /* Метод для вычисления  */
    public int solve(int firstNumber, int secondNumber, String action){
        int result = 0;
        switch (action){
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }
        return  result;
    }
}
