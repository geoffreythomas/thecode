package com.myinstitution.myapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Init {

	public static final String PLAIN_FOLDER = ".\\bin\\com\\myinstitution\\myapp\\plainfiles\\";
	private static final String ENCRYPTED_FOLDER = ".\\bin\\com\\myinstitution\\myapp\\encryptedfiles\\";
	private static final String DECRYPTED_FOLDER = ".\\bin\\com\\myinstitution\\myapp\\decryptedfiles\\";

	public static final String PLAIN_FILE = PLAIN_FOLDER + "passwords.data";
	private static final String ENCRYPTED_FILE = ENCRYPTED_FOLDER
			+ "passwords_encrypted.data";
	private static final String DECRYPTED_FILE = DECRYPTED_FOLDER
			+ "passwords_decrypted.data";

	private static final Logger LOGGER = Logger.getLogger(Init.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// BasicConfigurator.configure();
		LOGGER.debug("Welcome to Encryption.");
		System.out.println("Welcome to Encryption.");
		process();
		LOGGER.debug("Exiting main.");
	}

	private static void process() throws FileNotFoundException, IOException {
		LOGGER.debug("Entering process.");
		displayOptions();
		processUserInput();
		LOGGER.debug("Exiting process.");
	}

	private static void displayOptions() {
		LOGGER.debug("Entering displayOptions.");
		System.out.println("Enter your choice:");
		System.out.println("1. Encrypt");
		System.out.println("2. Decrypt");
		System.out.println("9. Exit");
		LOGGER.debug("Exiting displayOptions.");
	}

	private static void processUserInput() throws FileNotFoundException,
			IOException {
		LOGGER.debug("Entering processUserInput.");
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
			} else if (choice.equals("9")) {
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
		LOGGER.debug("Exiting processUserInput.");
	}

	private static void encrypt() throws FileNotFoundException, IOException {
		LOGGER.debug("Entering encrypt.");
		displayEncryptOptions();
		processUserInputForEncryption();
		LOGGER.debug("Exiting encrypt.");
	}

	private static void displayEncryptOptions() {
		LOGGER.debug("Entering displayEncryptOptions.");
		System.out.println("Encrypt all the files in the folder '"
				+ PLAIN_FOLDER + "' ?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		System.out.println("9. Exit");
		LOGGER.debug("Exiting displayEncryptOptions.");
	}

	private static void processUserInputForEncryption() throws IOException {
		LOGGER.debug("Entering processUserInputForEncryption.");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String nextLine = scanner.nextLine();
			String choice = nextLine.trim();
			if (choice.equals("1")) {
				LOGGER.debug("Starting Encryption of all files.");
				System.out.println("Starting Encryption of all files.");
				// encrypt();
				System.out.println("Nothing to do now.");
				System.out.println("Completed Encryption of all files.");
			} else if (choice.equals("2")) {
				LOGGER.debug("Starting Encryption of one file.");
				System.out.println("Starting Encryption of one file.");
				String inputFilepath = PLAIN_FILE;
				String outputfilePath = ENCRYPTED_FILE;
				encrypt(inputFilepath, outputfilePath);
				System.out.println("Completed Encryption of one file.");
			} else if (choice.equals("9")) {
				LOGGER.debug("Exiting.");
				System.out.println("Exiting.");
				displayOptions();
				break;
			} else {
				LOGGER.error("Invalid choice entered.");
				// System.out.print("\033[H\033[2J");
				System.out.println("You have entered an invalid choice.");
				displayEncryptOptions();
			}
		}

		LOGGER.debug("Exiting processUserInputForEncryption.");
	}

	private static void encrypt(String inputFilepath, String outputfilePath)
			throws FileNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(inputFilepath);
		FileOutputStream fileOutputStream = new FileOutputStream(outputfilePath);
		Encrypt encrypt = new Encrypt();
		encrypt.encrypt(fileInputStream, fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
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
