package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  파일출력 예제
 */
public class T06FileStreamTest {
	public static void main(String[] args) {

		FileOutputStream fos = null;

		try {
			// 출력용 OutputStream 객체 생성하기
			fos = new FileOutputStream("d:/D_Other/out.txt", true);
			
			for (char ch = 'a'; ch <= 'z'; ch++) {
				fos.write(ch);

			}
			System.out.println("파일 쓰기작업 완료....");

		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		////////////////////////////////

		// try-with-resource 문법
		try (FileInputStream fis = new FileInputStream("d:/D_Other/out.txt")) {
			int data = 0;
			while ((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
			System.out.println("출력 끝....");
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}
}
