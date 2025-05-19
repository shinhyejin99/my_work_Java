package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
   입출력 성능향상을 위한 보조스트림
   (문자기반의 Buffered스트림 사용예제)
 */
public class T12BufferedIOTest {
	public static void main(String[] args) {
		try (FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11BufferedIOTest.java");
			
			BufferedReader br = new BufferedReader(fr);
				) {
					/*
					 * int data = 0; while ((data = fr.read()) != -1) { System.out.println((char)
					 * data); }
					 */
			String str = "";
			int cnt =1;
			while((str = br.readLine())!= null) {
				System.out.printf("\n%4d : %s", cnt++, str);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
