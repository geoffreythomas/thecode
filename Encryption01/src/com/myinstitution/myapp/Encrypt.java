package com.myinstitution.myapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Encrypt {

	private static final Logger LOGGER = Logger.getLogger(Encrypt.class);

	public void encrypt(FileInputStream fileInputStream,
			FileOutputStream fileOutputStream) throws IOException {
		LOGGER.debug("Entering encrypt.");
		int read = fileInputStream.read();
		while (read != -1) {
			fileOutputStream.write(read + 1);
			read = fileInputStream.read();
		}
		LOGGER.info("Successfully Completed Encryption.");
		LOGGER.debug("Exiting encrypt.");
	}
}
