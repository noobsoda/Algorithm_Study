package com.ssafy.corona.virus;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface VirusMgr {
	void add(Virus v) throws DuplicatedException;
	Virus[] search();
	Virus search(String name) throws NotFoundException;
	
	void save() throws FileNotFoundException, IOException;
	void load() throws FileNotFoundException, IOException, ClassNotFoundException;
	void sort();
}