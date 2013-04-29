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
			+ "passwords.data";
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
				displayOptions();
			} else if (choice.equals("2")) {
				LOGGER.debug("Starting Decryption.");
				System.out.println("Starting Decryption.");
				decrypt();
				System.out.println("Completed Decryption.");
				displayOptions();
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
		System.out.println("2. No - Encrypt only the default file");
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
				displayEncryptOptions();
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
				displayEncryptOptions();
			} else if (choice.equals("9")) {
				LOGGER.debug("Exiting.");
				System.out.println("Exiting.");
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
		LOGGER.debug("Entering encrypt.");
		FileInputStream fileInputStream = new FileInputStream(inputFilepath);
		FileOutputStream fileOutputStream = new FileOutputStream(outputfilePath);
		Encrypt encrypt = new Encrypt();
		encrypt.encrypt(fileInputStream, fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
		LOGGER.debug("Exiting encrypt.");
	}

	private static void decrypt() throws IOException {
		LOGGER.debug("Entering decrypt.");
		displayDecryptOptions();
		processUserInputForDecryption();
		LOGGER.debug("Exiting decrypt.");
	}

	private static void displayDecryptOptions() {
		LOGGER.debug("Entering displayDecryptOptions.");
		System.out.println("Decrypt all the files in the folder '"
				+ ENCRYPTED_FOLDER_INPUT + "' ?");
		System.out.println("1. Yes");
		System.out.println("2. No - Decrypt only the default file");
		System.out.println("9. Exit");
		LOGGER.debug("Exiting displayDecryptOptions.");
	}

	private static void processUserInputForDecryption() throws IOException {
		LOGGER.debug("Entering processUserInputForDecryption.");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String nextLine = scanner.nextLine();
			String choice = nextLine.trim();
			if (choice.equals("1")) {
				LOGGER.debug("Starting Decryption of all files.");
				System.out.println("Starting Decryption of all files.");
				File file = new File(ENCRYPTED_FOLDER_INPUT);
				String[] files = file.list();
				for (int i = 0; i < files.length; i++) {
					String inputFilepath = ENCRYPTED_FOLDER_INPUT + files[i];
					LOGGER.debug("Starting Decryption of file: '"
							+ inputFilepath + "'");
					System.out.println("Starting Decryption of file: '"
							+ inputFilepath + "'");
					String outputfilePath = DECRYPTED_FOLDER_OUTPUT + files[i];
					decrypt(inputFilepath, outputfilePath);
					System.out.println("Completed Decryption of file: '"
							+ inputFilepath + "'");
				}
				System.out.println("Completed Decryption of all files.");
				displayDecryptOptions();
			} else if (choice.equals("2")) {
				String inputFilepath = ENCRYPTED_FILE_INPUT;
				LOGGER.debug("Starting Decryption of file: '" + inputFilepath
						+ "'");
				System.out.println("Starting Decryption of file: '"
						+ inputFilepath + "'");
				String outputfilePath = DECRYPTED_FILE_OUTPUT;
				decrypt(inputFilepath, outputfilePath);
				System.out.println("Completed Decryption of file: '"
						+ inputFilepath + "'");
				displayDecryptOptions();
			} else if (choice.equals("9")) {
				LOGGER.debug("Exiting.");
				System.out.println("Exiting.");
				break;
			} else {
				LOGGER.error("Invalid choice entered.");
				// System.out.print("\033[H\033[2J");
				System.out.println("You have entered an invalid choice.");
				displayDecryptOptions();
			}
		}
		LOGGER.debug("Exiting processUserInputForDecryption.");
	}

	private static void decrypt(String inputFilepath, String outputfilePath)
			throws IOException {
		LOGGER.debug("Entering decrypt.");
		FileInputStream fileInputStream = new FileInputStream(inputFilepath);
		FileOutputStream fileOutputStream = new FileOutputStream(outputfilePath);
		Decrypt decrypt = new Decrypt();
		decrypt.decrypt(fileInputStream, fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
		LOGGER.debug("Exiting decrypt.");
	}
}
