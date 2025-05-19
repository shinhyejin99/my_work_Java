package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16NonSerializableParentTest {

	/*
	   부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
	   부모객체의 필드값 처리 방법에 대하여....
	   
	   1. 부모 클래스가 Serializable 인터페이스를 구현하도록 한다.
	   
	   2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여
	      부모 클래스의 필드값을 처리할 수 있도록 직접 구현한다
	*/
	public static void main(String[] args) {
		
		try(
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/nonSerializeTest.bin")) 
				){
			Child child = new Child();
			child.setParentName("부모");
			child.setChildName("자식");
			
			oos.writeObject(child);
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		/////////////////////////////////////////////
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/nonSerializeTest.bin")))) {
			Child child = (Child) ois.readObject();
			
			System.out.println("parentName : " + child.getParentName());

			System.out.println("childName : " + child.getChildName());
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

    // 부모클래스
class Parent { 
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}

// 자식 클래스, 부모클래스 상속
class Child extends Parent implements Serializable{
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	/*
		직렬화 될 때 자동으로 호출됨
		(접근제한자가 private이 아니면 자동호출되지 않음)
		@param oos
		@throws IOException
		
	*/
	private void writeObject(ObjectOutputStream oos) throws IOException{
		
		
		oos.writeUTF(getParentName());
		oos.defaultWriteObject(); //기본 직렬화 작업 수행
		//System.out.println("writeObject() 호출됨.");
}

	/*
	 * 역직렬화 될 때 자동으로 호출됨.
	 * (접근제한자가 private이 아니면 자동호출되지 않음)
		@param ois
		@throws IOException
		@throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream ois ) throws IOException, ClassNotFoundException{
		
		this.setParentName(ois.readUTF());
		ois.defaultReadObject(); // 기본 역직렬화 작업 수행
		//System.out.println("readObject()호출됨.");
	}
}

























