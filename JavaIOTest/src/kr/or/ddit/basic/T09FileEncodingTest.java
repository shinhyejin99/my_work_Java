package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class T09FileEncodingTest {
	public static void main(String[] args) {
		
		/*
		 * OutputStream객체 => 바이트기반 스트림을 문자기반 스트림으로 
		 *                   변환해 주기 위한 보조스트림 객체
		 *           이 객체도 출력할 때 '인코딩 방식'을 지정해서 출력할 수 있다.
		 */
		
		/*
		 * 키보드로 입력한 내용을 파일로 저장하는데
		 *  out_utf8.txt 파일은 'UTF-8' 인코딩 방식으로, 
		 *  out_ansi.txt 파일은 'MS949' 인코딩 방식으로 저장한다.
		 */
		
		try(InputStreamReader isr = new InputStreamReader(System.in);
				OutputStreamWriter osw1 = new OutputStreamWriter(new FileOutputStream("d:/D_Other/out_utf8.txt"),"UTF-8");
				OutputStreamWriter osw2 = new OutputStreamWriter(new FileOutputStream("d:/D_Other/out_ansi.txt"),"MS949")) {
			System.out.println("아무거나 입력하세요");

				int data = 0;
				while((data = isr.read())!= -1) {
					osw1.write(data);
					osw2.write(data);
					
				}
				System.out.println("작업 완료...");
	} catch (IOException ex) {
		ex.printStackTrace();
		}
	}
}

