package self.aub.study.java.third.fastjson;

import java.util.Date;

public class BaseObject {
	private int age;
	private String name;
	private Date createTime;
	private SubObject subObject;
	private String[] phones;

	public BaseObject() {
	}

	public BaseObject(int age, String name, Date createTime) {
		this.age = age;
		this.name = name;
		this.createTime = createTime;
	}

	public BaseObject(int age, String name, Date createTime, SubObject subObject) {
		this.age = age;
		this.name = name;
		this.createTime = createTime;
		this.subObject = subObject;
	}

	@Override
	public String toString() {
		return "BaseObject [age=" + age + ", name=" + name + ", createTime=" + createTime + ", subObject=" + subObject + ",phones=" + phones + "]";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SubObject getSubObject() {
		return subObject;
	}

	public void setSubObject(SubObject subObject) {
		this.subObject = subObject;
	}

	public String[] getPhones() {
		return phones;
	}

	public void setPhones(String[] phones) {
		this.phones = phones;
	}

}
