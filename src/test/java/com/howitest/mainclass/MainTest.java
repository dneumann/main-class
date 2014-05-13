package com.howitest.mainclass;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class MainTest {
	private ByteArrayOutputStream fakeOutput;
	private Main mainClass;
	private Converter converterMock;
	
	@Before
	public void beforeEachTest() throws Exception {
		mainClass = new Main();
		
		fakeOutput = new ByteArrayOutputStream();
		PrintStream newOut = new PrintStream(fakeOutput);
		mainClass.redirectOutputTo(newOut);
		
		converterMock = mock(Converter.class);
		mainClass.setConverter(converterMock);
	}

	@Test
	public void shouldPrintHelp() {
		mainClass.execute(new String[]{"-help"});
		String capturedOutput = new String(fakeOutput.toByteArray());
		
		assertThat(capturedOutput, containsString("Usage: java -jar"));
	}
	
	@Test
	public void shouldConvertSuccessfully() throws IOException {
		mainClass.execute(new String[]{
				"-infile", "test.txt", "-outfile", "test.pdf"});
		String capturedOutput = new String(fakeOutput.toByteArray());
		
		verify(converterMock).convertTxtToPdf("test.txt", "test.pdf");
		assertThat(capturedOutput, containsString("Finished conversion."));
	}

	@Test
	public void shouldComplainAboutArguments() {
		mainClass.execute(new String[]{"-infile", "test.txt"});
		String capturedOutput = new String(fakeOutput.toByteArray());
		
		assertThat(capturedOutput, containsString("incorrect number"));
	}

	@Test
	public void shouldCatchException() throws IOException {
		doThrow(new IOException("File not found")).
			when(converterMock).convertTxtToPdf(anyString(), anyString());
		mainClass.execute(new String[]{
			"-infile", "test.txt", "-outfile", "test.pdf"});
		String capturedOutput = new String(fakeOutput.toByteArray());
		
		assertThat(capturedOutput, containsString("Error: File not found"));
	}

}
