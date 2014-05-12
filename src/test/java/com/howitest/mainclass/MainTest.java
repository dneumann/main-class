package com.howitest.mainclass;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class MainTest {
	private ByteArrayOutputStream baos;
	private Main mainClass;
	
	@Before
	public void setUp() throws Exception {
		mainClass = new Main();
		
		baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		mainClass.redirectOutputTo(out);
		
		Converter converterMock = mock(Converter.class);
		mainClass.setConverter(converterMock);
	}

	@Test
	public void test() {
		mainClass.execute(new String[]{"-help"});
	}

}
