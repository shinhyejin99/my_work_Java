package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class T08FileReaderTest {
	public static void main(String[] args) {

		try (FileReader fr = new FileReader("d:/D_Other/testChar.txt");
				FileInputStream fis = new FileInputStream("d:/D_Other/testChar.txt")) {

			int data = 0;

			while ((data = fr.read()) != -1) {
				System.out.print((char) data);
			}
			while ((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
