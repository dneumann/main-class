package com.howitest.mainclass;

import java.io.IOException;
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
		
		if ("-help".equals(args[0])) {
			out.println(
				"Usage: java -jar converter.jar -infile <txt-file> -outfile <pdf-file>");
			return;
		}
		if (args.length != 4) {
			out.println("Error: incorrect number of arguments.");
			return;
		}
		String txtInput = args[1];
		String pdfOutput = args[3];
		try {
			out.println("Starting conversion.");
			converter.convertTxtToPdf(txtInput, pdfOutput);
			out.println("Finished conversion.");
		} catch (IOException e) {
			out.println("Error: " + e.getMessage());
		}
	}


}
