package com.demo.client;

import java.util.Scanner;

import com.demo.presentation.Presentation;
import com.demo.presentation.PresentationImpl;

public class Client {

	public static void main(String[] args) {
		Presentation presentation = new PresentationImpl();
		Scanner sc = new Scanner(System.in);
		while(true) {
		presentation.loginMenu();
		System.out.println("Enter your choice :");
		int choice = sc.nextInt();
		presentation.performChoice(choice);
		}
		
	}
	
}
