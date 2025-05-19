package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
   입출력 성능향상을 위한 보조스트림 예제1
   (바이트기반의 Buffered스트림)
 */
public class T11BufferedIOTest {
	public static void main(String[] args) {
		
		try (FileOutputStream fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
	
				//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가
				//8192byte(8KB)로 설정된다.
				
				//버퍼의 크기가 5인 보조스트림 객체 생성
				BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
				){
			for(char ch='1'; ch<='9'; ch++) {
				fos.write(ch);
			}
			bos.flush(); // 작업을 종료하기 전에 버퍼에 남아있는 데이터를 
			              // 모두 출력시킨다. (close() 호출시 자동 호출됨)
			
			System.out.println("작업 끝...");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
