package de.samuelschepp.vokabeltrainer.engine;

public class SAVoc {

	public String foreignWord;
	public String nativeWord;
	
	public SAVoc() {
		foreignWord = "";
		nativeWord = "";
	}
	
	public String toString() {
		return "Voc: " + nativeWord + " " + foreignWord;
	}
}
