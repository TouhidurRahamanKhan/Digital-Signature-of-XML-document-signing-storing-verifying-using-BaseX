package com.touhid.tabulation.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteFile {

	public static void deleteFile(String filePath) throws IOException{
		Path path = Paths.get(filePath);
		Files.delete(path);
	}
}
