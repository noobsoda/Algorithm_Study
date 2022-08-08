package com.ssafy.corona.virus;

import java.io.Serializable;

public class Virus implements Serializable, Comparable<Virus>{
	private String name;
	private int level;

	public Virus() {}
	public Virus(String name, int level) {
		setName(name);
		setLevel(level);
	}

	//equals 오버라이딩하여 바이러스 이름을 통해 비교하는 코드를 구현하세요
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Virus){
			Virus v = (Virus)obj;
			if(v.name.equals(this.name))
				return true;
		}
		return false;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(getName()).append("\t")
		  .append(getLevel());
		return sb.toString();
	}
	@Override
	public int compareTo(Virus o) {
		if(this.level == o.level){
			return this.name.length()-o.name.length();
		}
		return this.level - o.level;
	}


	//compareTo 메소드를 이용하여 level 순서로 정렬한 후 level이 같다면 이름 길이가 짧은 순서로 정렬하세요 
	
	

	
	

}
