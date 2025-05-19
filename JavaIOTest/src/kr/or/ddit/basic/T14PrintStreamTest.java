package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/*
  프린터기능 제공 보조스트림
 */

public class T14PrintStreamTest {
	public static void main(String[] args) {

		try (FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
				PrintStream out = new PrintStream(fos);) {

			// PrintStream은 모든 자료형의 데이터를 출력할 수 있는 기능을
			// 제공하는 스트림 클래스이다.

			out.print("안녕하세요. PrintStream입니다.\n");
			out.println("안녕하세요. PrintStream입니다2.");
			out.println("안녕하세요. PrintStream입니다3.");
			out.println(out);
			out.println(3.14);

			System.out.println("출력작업 완료...");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		//////////////////////////////////////////////

		/*
		 * 향상된 기능의 PrintWriter가 PrintStream 보다 다양한 인코딩 방식으로 처리하는데 적합하지만 기존에 사용되던
		 * PrintStream 객체가 계속 사용되고 있음.
		 */

		try (FileOutputStream fos = new FileOutputStream("d:/D_Other/print2.txt");
				PrintWriter out = new PrintWriter(new OutputStreamWriter(fos, "CP949"));) {
			
			out.print("안녕하세요. PrintWriter입니다.\n");
			out.println("안녕하세요. PrintWriter입니다2.");
			out.println("안녕하세요. PrintWriter입니다3.");
			out.println(out);
			out.println(3.14);
			System.out.println("출력작업 완료2...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
