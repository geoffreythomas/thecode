package com.myinstitution.myapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Init {

	public static final String PLAIN_FILE = ".\\bin\\com\\myinstitution\\myapp\\passwords.data";
	private static final String ENCRYPTED_FILE = ".\\bin\\com\\myinstitution\\myapp\\passwords_encrypted.data";
	private static final String DECRYPTED_FILE = ".\\bin\\com\\myinstitution\\myapp\\passwords_decrypted.data";

	public static final String PLAIN_FOLDER = ".\\bin\\com\\myinstitution\\myapp\\";
	private static final String ENCRYPTED_FOLDER = ".\\bin\\com\\myinstitution\\myapp\\";
	private static final String DECRYPTED_FOLDER = ".\\bin\\com\\myinstitution\\myapp\\";

	private static final Logger LOGGER = Logger.getLogger(Init.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// BasicConfigurator.configure();
		LOGGER.debug("Welcome to Encryption.");
		System.out.println("Welcome to Encryption.");
		displayUI();
		LOGGER.debug("Exiting main.");
	}

	private static void displayUI() throws FileNotFoundException, IOException {
		LOGGER.debug("Entering displayUI.");
		displayOptions();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String nextLine = scanner.nextLine();
			String choice = nextLine.trim();
			if (choice.equals("1")) {
				LOGGER.debug("Starting Encryption.");
				System.out.println("Starting Encryption.");
				encrypt();
				System.out.println("Completed Encryption.");
			} else if (choice.equals("2")) {
				LOGGER.debug("Starting Decryption.");
				System.out.println("Starting Decryption.");
				decrypt();
				System.out.println("Completed Decryption.");
			} else if (choice.equals("3")) {
				LOGGER.debug("Exiting.");
				System.out.println("Exiting.");
				System.exit(0);
			} else {
				LOGGER.error("Invalid choice entered.");
				// System.out.print("\033[H\033[2J");
				System.out.println("You have entered an invalid choice.");
				displayOptions();
			}
		}
		LOGGER.debug("Exiting displayUI.");
	}

	private static void displayOptions() {
		LOGGER.debug("Entering displayOptions.");
		System.out.println("Enter your choice:");
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		System.out.println("3. Exit");
		LOGGER.debug("Exiting displayOptions.");
	}

	private static void encrypt() throws FileNotFoundException, IOException {
		LOGGER.debug("Entering encrypt.");
		String inputFilepath = PLAIN_FILE;
		FileInputStream fileInputStream = new FileInputStream(inputFilepath);
		String outputfilePath = ENCRYPTED_FILE;
		FileOutputStream fileOutputStream = new FileOutputStream(outputfilePath);
		Encrypt encrypt = new Encrypt();
		encrypt.encrypt(fileInputStream, fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
		LOGGER.debug("Exiting encrypt.");
	}

	private static void decrypt() throws IOException {
		LOGGER.debug("Entering decrypt.");
		String inputFilepath = ENCRYPTED_FILE;
		FileInputStream fileInputStream = new FileInputStream(inputFilepath);
		String outputfilePath = DECRYPTED_FILE;
		FileOutputStream fileOutputStream = new FileOutputStream(outputfilePath);
		Decrypt decrypt = new Decrypt();
		decrypt.decrypt(fileInputStream, fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
		LOGGER.debug("Exiting decrypt.");
	}
}
