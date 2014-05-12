package com.howitest.mainclass;

import java.io.PrintStream;


public class Main {

	private PrintStream out = System.out;
	private Converter converter = new Converter();
	
	// for unit tests
	void redirectOutputTo(PrintStream newOut) {
		out = newOut;
	}
	// for unit tests
	void setConverter(Converter newConverter) {
		converter = newConverter;
	}

	public static void main(String[] args) {
		new Main().execute(args);
	}

	void execute(String[] args) {
		
		
	}


}
