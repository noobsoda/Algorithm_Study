package com.ssafy.corona.virus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class VirusMgrImpl implements VirusMgr {
	private Virus[] virus;
	private int index;
	
	private VirusMgrImpl() {
		virus=new Virus[100];		
	}
	
	//싱글톤으로 구현하세요
	private static VirusMgrImpl vm = new VirusMgrImpl();
	public static VirusMgrImpl getInStance(){
		return vm;
	}
	
	@Override
	public void add(Virus v) throws DuplicatedException{
		try {
			Virus search = search(v.getName());
			if(search.equals(v)) {
				throw new DuplicatedException();
			}
		} catch (NotFoundException e) {
			virus[index++]=v;
		}
	}
	@Override
	public Virus[] search() {
		Virus[] tmp = new Virus[index];
		System.arraycopy(virus, 0,tmp,  0, index);
		return tmp;
	}
	@Override
	public Virus search(String name) throws NotFoundException{
		for(int i=0; i<index; i++) {
			if(virus[i].getName().equals(name)) return virus[i];
		}
		throw new NotFoundException(name+": 미등록 바이러스입니다.");
	}

	//세이브, 로드, 정렬을  구현하세요

	@SuppressWarnings("resource")
	@Override
	public void save() throws FileNotFoundException, IOException {
		ArrayList<Virus> tmp = new ArrayList<>();
		for (int i = 0; i < index; i++) {
			tmp.add(virus[i]);
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("자바공부/Sample_daejeon_6_honggilsoon/virus.obj"));
		oos.writeObject(tmp);
	}

	@SuppressWarnings("resource")
	@Override
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("자바공부/Sample_daejeon_6_honggilsoon/virus.obj"));
		ArrayList<Virus> tmp = (ArrayList<Virus>)ois.readObject();
		virus = new Virus[tmp.size()];
		tmp.toArray(virus);

	}

	@Override
	public void sort() {
		
		Arrays.sort(virus);
	}

	
}
