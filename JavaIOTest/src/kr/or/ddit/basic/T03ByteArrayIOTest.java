package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class T03ByteArrayIOTest {

	public static void main(String[] args) {
		
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8,9};
		byte[] outSrc = null;
		
		// 스트림 객체 생성하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data = 0;
		
		/*
		   read() 메서드 => byte단위로 데이터를 읽어와 int형으로 반환한다.
		                  더 이상 읽을 데이터가 없으면 -1을 반환한다.
		 */
		int cnt = 0;
		while((data = bais.read()) != -1){
			//System.out.println(cnt++);
			baos.write(data);
			
		}
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
	}
	
}
