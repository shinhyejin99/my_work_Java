package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class T10FileEncodingTest {

	/*
	 * 한글 인토딩 방식에 대하여...
	 * 
	 * 한글 인코딩 방식은 크게 UTF-8 과 EUC_KR 방식 두가지로 나누어 볼 수 있다. 원래 한글윈도우즈는 CP949방식을 사용했는데,
	 * 윈도우를 개발한 마이크로 소프트에서 EUC_KR방식에서 확장하였기 때문에 MS949라고도 부른다. 한글 윈도우즈의 메모장에서 말하는
	 * ANSI 인코딩이란 CP040(Code Page 949)를 말한다. - MS949 => 윈도우즈의 기본 한글 인코딩 방식(ANSI 계열)
	 * - UTF-8 => 유니코드 UTF-8 인코딩 방식 - US-ASCII => 영문 전영 인코딩 방식
	 * 
	 * ANSI는 영어를 표기하기 위해만든 코드로 규격 자체에 한글이 없었다가 나중에 여기에 한글이 추가되면서 EUC-KR, CP949 등으로
	 * 인코딩 방식이 등장하였음.
	 * 
	 * 참고) ASCII => extended ASCII(ISO 8859-1) => 조합형, 완성형(KSC 5601)
	 * 
	 * => 윈도우계열: CP949(확장완성형) 유닉스계열: EUC-KR(확장 유닉스 코드)
	 * 
	 * => ANSI 계열 => EUC_KR
	 * 
	 * => 유니코드(UTF-8)
	 */
	public static void main(String[] args) {

		try (FileInputStream fis = new FileInputStream("d:/D_Other/test_ansi.txt");
				FileInputStream fis2 = new FileInputStream("d:/D_Other/test_UTF8.txt");
				// 파일 인코딩 정보를 이용하여 파일 읽기
				// new InputStreamReader(바이트기반스트림객체, 인코딩방식)
				InputStreamReader isr = new InputStreamReader(fis2, "UTF-8")) {
			
			FileReader fr = new FileReader("d:/D_Other/test_ansi.txt");
					
			int data = 0;
			while ((data = isr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
			System.out.println("출력 끝...");
		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}
}
