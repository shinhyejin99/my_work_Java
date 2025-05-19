package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  기본타입 데이터 입출력을 위한 보조 스트림
  
 */
public class T13DataIODStremRTest {
 public static void main(String[] args) {
	
	 try (FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
			 DataOutputStream dos = new DataOutputStream(fos);
			 ){
		 /*DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.*/
		dos.writeUTF("신혜진"); //문자열 데이터 출력(UTF-8)
		dos.writeInt(17);      // 정수형으로 데이터 출력
		dos.writeFloat(3.14f);  //실수형(Float)으로 출력 
		dos.writeDouble(3.14);   //실수형(Double)으로 출력
		dos.writeBoolean(true);   //논리형으로 출력
		
		System.out.println("출력 완료...");
 	} catch (IOException ex) {
		ex.printStackTrace();
	}
	 ///////////////////////////
	 try (FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
			 DataInputStream dis = new DataInputStream(fis);
			 ){
		 System.out.println("문자열 데이터: " + dis.readUTF());
		 System.out.println("정수형 데이터 : " + dis.readInt());
		 System.out.println("실수형(float) 데이터 : " + dis.readFloat());
		 System.out.println("실수형(double) 데이터 : " + dis.readDouble());
		 System.out.println("논리형 데이터 : " + dis.readBoolean());
		
		 System.out.println("------------------------------------");
		 
	} catch (IOException ex) {
		ex.printStackTrace();
	}
}
}
