package de.samuelschepp.vokabeltrainer.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SAVocListImporter {

	private static String path = ".";

	private static ArrayList<File> getValidFiles() {
		File folder = new File(path);
		ArrayList<File> allFilesOfDirectory = new ArrayList<File>(Arrays.asList(folder.listFiles()));
		ArrayList<File> validFiles = new ArrayList<File>();
		
		for (int i = 0; i < allFilesOfDirectory.size(); i++) 
		{
			if (allFilesOfDirectory.get(i).isFile()) 
			{
				String currentFile = allFilesOfDirectory.get(i).getAbsolutePath();
				System.out.println("File Found: " + allFilesOfDirectory.get(i).getName());
				if (currentFile.endsWith(".txt") || currentFile.endsWith(".TXT"))
				{
					validFiles.add(allFilesOfDirectory.get(i));
					System.out.println(" - > File is valid");
				}
			}
		}
		return validFiles;
	}
	
	public static ArrayList<String> getFileNames() {
		ArrayList<File> files = getValidFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		
		for(File file : files) {
			fileNames.add(file.getName());
		}
		return fileNames;
	}
	
	public static ArrayList<SAVoc> getVocList(int index) throws IOException {
		ArrayList<File> files = getValidFiles();
		File file = files.get(index);
		ArrayList<SAVoc> vocList = new ArrayList<SAVoc>();
		
		InputStreamReader ir = new InputStreamReader(new FileInputStream(file), "UTF-8"); 
		BufferedReader br = new BufferedReader(ir);
		
		boolean fertig = false;
		try {
			while(!fertig) {
				SAVoc currentVoc = new SAVoc();
				currentVoc.nativeWord = br.readLine();
				currentVoc.foreignWord = br.readLine();
				br.readLine();
				fertig = (currentVoc.nativeWord == null || currentVoc.foreignWord == null);
				if(!fertig) {
					vocList.add(currentVoc);
					System.out.println(currentVoc.toString());
				}
			}
		} catch (Exception ex) { ex.printStackTrace(); }
		
		br.close();
		
		return vocList;
	}
}
