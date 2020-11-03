package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string of number, action and second number : ");
	    String userInput = scanner.nextLine();
	    Calculate calculate = new Calculate();
	    calculate.stringAnalysis(userInput);
    }
}
