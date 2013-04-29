package com.myinstitution.myapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Init {

	public static final String PLAIN_FOLDER_INPUT = ".\\resources\\com\\myinstitution\\myapp\\input\\plainfiles\\";
	private static final String ENCRYPTED_FOLDER_INPUT = ".\\resources\\com\\myinstitution\\myapp\\input\\encryptedfiles\\";
	private static final String ENCRYPTED_FOLDER_OUTPUT = ".\\bin\\com\\myinstitution\\myapp\\output\\encryptedfiles\\";
	private static final String DECRYPTED_FOLDER_OUTPUT = ".\\bin\\com\\myinstitution\\myapp\\output\\decryptedfiles\\";

	public static final String PLAIN_FILE_INPUT = PLAIN_FOLDER_INPUT
			+ "passwords.data";
	private static final String ENCRYPTED_FILE_INPUT = ENCRYPTED_FOLDER_INPUT
			+ "passwords_encrypted.data";
	private static final String ENCRYPTED_FILE_OUTPUT = ENCRYPTED_FOLDER_OUTPUT
			+ "passwords_encrypted.data";
	private static final String DECRYPTED_FILE_OUTPUT = DECRYPTED_FOLDER_OUTPUT
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
				+ PLAIN_FOLDER_INPUT + "' ?");
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
				File file = new File(PLAIN_FOLDER_INPUT);
				String[] files = file.list();
				for (int i = 0; i < files.length; i++) {
					String inputFilepath = PLAIN_FOLDER_INPUT + files[i];
					LOGGER.debug("Starting Encryption of file: '"
							+ inputFilepath + "'");
					System.out.println("Starting Encryption of file: '"
							+ inputFilepath + "'");
					String outputfilePath = ENCRYPTED_FOLDER_OUTPUT + files[i];
					encrypt(inputFilepath, outputfilePath);
					System.out.println("Completed Encryption of file: '"
							+ inputFilepath + "'");
				}
				System.out.println("Completed Encryption of all files.");
			} else if (choice.equals("2")) {
				String inputFilepath = PLAIN_FILE_INPUT;
				LOGGER.debug("Starting Encryption of file: '" + inputFilepath
						+ "'");
				System.out.println("Starting Encryption of file: '"
						+ inputFilepath + "'");
				String outputfilePath = ENCRYPTED_FILE_OUTPUT;
				encrypt(inputFilepath, outputfilePath);
				System.out.println("Completed Encryption of file: '"
						+ inputFilepath + "'");
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
		String inputFilepath = ENCRYPTED_FILE_INPUT;
		FileInputStream fileInputStream = new FileInputStream(inputFilepath);
		String outputfilePath = DECRYPTED_FILE_OUTPUT;
		FileOutputStream fileOutputStream = new FileOutputStream(outputfilePath);
		Decrypt decrypt = new Decrypt();
		decrypt.decrypt(fileInputStream, fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
		LOGGER.debug("Exiting decrypt.");
	}
}
