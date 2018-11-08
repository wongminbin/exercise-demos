package com.wong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
* @author HuangZhibin
* 
* 2018年7月3日 上午8:57:26
*/
public class MapReduce {

	public static void main(String[] args) throws IOException {
		validate();
	}
	
	public static void createShorten() throws IOException {
		File file = new File("d://1.txt");
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int j = 0; j < 100; j++) {
			int _1kw = 10000000;
			for (int i = j*_1kw; i < _1kw*(j+1); i++) {
				String result = "";//shorten("http://erp02.zgps168.com?fkStoreId="+i);
				bw.write(result + " " + i);
				bw.newLine();
				if (i % 1000 == 0) {
					bw.flush();
				}
			}
			bw.flush();
		}
		bw.close();
	}
	
	public static void cut() throws IOException {
		int _100k = 1000000;
		BufferedReader reader = new BufferedReader(new FileReader("d://1.txt"));
		
		List<String> lines = new ArrayList<>(_100k);
		String line = null;
		int num = 0;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
			if(lines.size() == _100k) {
				Collections.sort(lines);
				BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("d://cut//%s.txt", 
						StringUtils.leftPad(String.valueOf(num), 3, '0'))));
				for (String l : lines) {
					writer.write(l);
					writer.newLine();
				}
				writer.flush();
				writer.close();
				
				writer = null;
				lines = new ArrayList<>(_100k);
				num++;
			}
		}
		
		if (CollectionUtils.isNotEmpty(lines)) {
			Collections.sort(lines);
			BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("d://cut//%s.txt", 
					StringUtils.leftPad(String.valueOf(num), 3, '0'))));
			for (String l : lines) {
				writer.write(l);
				writer.newLine();
			}
			writer.flush();
			writer.close();
		}
		
		reader.close();
	}

	public static void map() {
		AtomicInteger num = new AtomicInteger();
		
		while (true) {
			File file = new File("d://cut");
			if (file.isFile() || (file.isDirectory() && file.listFiles().length <= 1)) {
				System.out.println("==========================finished......");
				break;
			}
			
			File[] files = file.listFiles();
			System.out.println("==========================file size " + files.length);
			//ExecutorService service = Executors.newFixedThreadPool(64);
			//List<Future<Boolean>> futures = new ArrayList<>(files.length/2 + 1);
			for (int i = 0; i < files.length - 1; ) {
				final File f1 = files[i++];
				final File f2 = files[i++];
				//Future<Boolean> future = service.submit(() -> {
					try {
						BufferedReader r1 = new BufferedReader(new FileReader(f1));
						BufferedReader r2 = new BufferedReader(new FileReader(f2));
						BufferedReader[] rolls = new BufferedReader[] {r1, r2};
						
						BufferedWriter w1 = new BufferedWriter(new FileWriter(String.format("d://cut//map_%s.txt",
								StringUtils.leftPad(String.valueOf(num.incrementAndGet()), 3, '0'))));
						
						int roll = 0;
						String temp = rolls[roll++].readLine();
						while(true) {
							String str = rolls[roll%2].readLine();
							if (temp == null && str == null) {
								break;
							}
							
							int compare = 0;
							if (temp == null) {
								compare = 1;
							} else if (str == null) {
								compare = -1;
							} else {
								compare = StringUtils.compare(temp, str, true);
							}
							
							// temp > str
							if (compare > 0) {
								w1.write(str);
								w1.newLine();
							} else {
								w1.write(temp);
								w1.newLine();
								
								temp = str;
								roll++;
							}
						}
						w1.close();
						r1.close();
						r2.close();
						
						f1.delete();
						f2.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				//	return true;
				//});
				
				//futures.add(future);
			}
			
			//for (Future<Boolean> f : futures) {
			//	while (!f.isDone()) {}
			//}
		}
	}
	
	public static void validate() throws IOException {
		System.out.println("===================start...");
		File file = new File("d://cut");
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				BufferedReader reader = new BufferedReader(new FileReader(f));
				String temp = reader.readLine();
				String line = null;
				while((line = reader.readLine()) != null) {
					String temp0 = split(temp)[0];
					String line0 = split(line)[0];
					
					// compare>0-->temp0>line0, compare=0-->temp0=line0, compare<0 -->temp0<line0
					int compare = StringUtils.compare(temp0, line0, true);
					if (compare<0) {
						// right
						
					} else if (compare == 0) {
						// shorten error
						System.out.println(compare + " === " +temp + " === " + line);
						
					} else if (compare > 0) {
						// sort error
						System.out.println(compare + " === " +temp + " === " + line);
						
					}
					
					temp = line;
				}
				reader.close();
			}
		}
		
	}
	
	private static String[] split(String str) {
		return str.split(" ");
	}
}
