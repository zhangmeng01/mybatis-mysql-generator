package com.syswin.xwtoon.db.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapInfiniteLoop {
	private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);
	private List<String> tempList = new ArrayList<String>();
	// final List<Integer> piDigits =[1,2,3,4,5,8];

	final List<String> piDigits = Arrays.asList("", "");

	public static void main(String[] args) {
		map.put(5, "C");

		new Thread("Thread1") {
			public void run() {
				map.put(7, "B");
				System.out.println(map);
				;
				System.out.println("map.get(11)=》" + map.get(11));
			};
		}.start();
		new Thread("Thread2") {
			public void run() {
				map.put(3, "A");
				System.out.println(map);
			};
		}.start();
	}
}
