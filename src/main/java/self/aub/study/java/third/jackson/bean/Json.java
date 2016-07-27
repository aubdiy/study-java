package self.aub.study.java.third.jackson.bean;

import java.util.Date;
import java.util.List;

public class Json {

	private int i;
	private boolean b;
	private String s;
	private Date date;
	private SubEnum subEmun;

	private SubA subA;
	private List<SubB> subBs;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SubEnum getSubEmun() {
		return subEmun;
	}

	public void setSubEmun(SubEnum subEmun) {
		this.subEmun = subEmun;
	}

	public SubA getSubA() {
		return subA;
	}

	public void setSubA(SubA subA) {
		this.subA = subA;
	}

	public List<SubB> getSubBs() {
		return subBs;
	}

	public void setSubBs(List<SubB> subBs) {
		this.subBs = subBs;
	}

}
