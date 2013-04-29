package com.myinstitution.myapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Decrypt {
	private static final Logger LOGGER = Logger.getLogger(Decrypt.class);

	public void decrypt(FileInputStream fileInputStream,
			FileOutputStream fileOutputStream) throws IOException {
		LOGGER.debug("Entering decrypt.");
		int read = fileInputStream.read();
		while (read != -1) {
			fileOutputStream.write(read - 1);
			read = fileInputStream.read();
		}
		LOGGER.info("Successfully Completed Decryption.");
		LOGGER.debug("Exiting decrypt.");
	}
}
