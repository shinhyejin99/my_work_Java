package kr.or.ddit.basic;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 객체 입출력 예제(직렬화와 역직렬화)
 */
public class T15ObjestSteamTest {
	public static void main(String[] args) {

		Member mem1 = new Member("김주희", 20, "대전");
		Member mem2 = new Member("임가영", 23, "부산");
		Member mem3 = new Member("송태호", 30, "광주");
		Member mem4 = new Member("윤서현", 33, "대구");

		try (ObjectOutput oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/memObj.bin"));) {
			// 객체를 파일로 저장하기

			oos.writeObject(mem1);  //직렬화
			oos.writeObject(mem2);  //직렬화
			oos.writeObject(mem3);  //직렬화
			oos.writeObject(mem4);  //직렬화

			System.out.println("객체 저장작업 완료...");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		/////////////////////////////////////////
		
		try(
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/D_Other/memObj.bin")))
		{
			//저장한 객체를 읽어와 내용 출력하기
			
			Object obj = null;
			
			while((obj = ois.readObject()) != null) {
				
				Member mem = (Member) obj;
				
				System.out.println("이름 : " + mem.getName());  //역직렬화
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				
				System.out.println("----------------------------");
				
			}
		}catch(EOFException ex) {
			System.out.println("객체 읽기 작업 완료....");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}

// 회원정보를 담기위한 VO클래스
class Member implements Serializable {
	// 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화 할수 있도록
	// 제한하고 있임
	
	/*
	  transient(일시적인) => 직렬화가 되지 않을 멤버변수에 지정한다.
	  			          직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	  			          (참조형변수 : null, 숫자형변수 : 0)
	  			          * static 필드도 직렬화가 대상이 아니다.
	*/
	
	transient private String name;
	transient private int age;
	private String addr;

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", addr=" + addr + "]";
	}

}
