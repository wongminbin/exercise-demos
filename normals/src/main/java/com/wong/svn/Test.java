package com.wong.svn;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNProperty;
import org.tmatesoft.svn.core.io.SVNRepository;

/**
 * @author Huang zhibin
 * 
 *         2017年9月14日 下午3:23:23
 */
public class Test {

	/*
	 * 相关变量赋值
	 */
	String url = "https://10.10.0.198/svn/apps/04_%E7%B3%BB%E7%BB%9F%E9%87%8D%E6%9E%84/04_%E6%BA%90%E7%A0%81/trunk/esl-projects/esl-statics";
	String relativePath = "src/main/webapp/statics/";
	String name = "huangzhibin";
	String password = "123456";
	
	String downloadRoot = "C:\\Users\\Administrator\\Desktop\\";

	SVNClient svn = new SVNClient(url, relativePath, name, password);

	SVNRepository repository = svn.getRepository();
	
	private Date lastedDate;

	private void mkdir(File dirFile) {
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
	}
	
	private void downFile(String filePath) throws Exception {
		File file = new File(downloadRoot + filePath);
		
		mkdir(file.getParentFile());
		
		// 此变量用来存放要查看的文件的属性名/属性值列表。
		SVNProperties fileProperties = new SVNProperties();
		// 此输出流用来存放要查看的文件的内容。
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 获取要查看文件的内容和属性，结果保存在baos和fileProperties变量中。
		repository.getFile(filePath, -1, fileProperties, baos);

		// 获取文件的mime-type
		String mimeType = fileProperties.getStringValue(SVNProperty.MIME_TYPE);
		// 判断此文件是否是文本文件
		SVNProperty.isTextMimeType(mimeType);
		
		/*
		 * 显示文件的所有属性
		 */
		fileProperties.asMap().forEach((k, v) -> {
			System.out.println(k + "=" + v);
		});

		baos.writeTo(new FileOutputStream(file));
		
		baos.close();
		
	}
	
	@SuppressWarnings("unchecked")
	private void loopDir(String path) throws Exception {
		// 此变量用来存放要查看的文件的属性名/属性值列表。
		SVNProperties fileProperties = new SVNProperties();
		Collection<SVNDirEntry> dirs = repository.getDir(path, -1, fileProperties, new ArrayList<>());
		
		for (SVNDirEntry dir : dirs) {
			if (dir.getDate().before(lastedDate)) {
				continue;
			}
			System.out.println("enter load step : " + dir.getRelativePath());
			if (dir.getKind().equals(SVNNodeKind.FILE)) {
				downFile(path + dir.getRelativePath());
			} else if(dir.getKind().equals(SVNNodeKind.DIR)) {
				loopDir(path + dir.getRelativePath() + "/");
			}
			
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public void test() throws Exception {

		lastedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2017-09-14 09:00");
		// 获得版本库中文件的类型状态（是否存在、是目录还是文件），参数-1表示是版本库中的最新版本。
		SVNNodeKind nodeKind = repository.checkPath(relativePath, -1);
		
		if (SVNNodeKind.NONE.equals(nodeKind)) {
			System.err.println("要查看的文件在 '" + url + "'中不存在.");
			System.exit(1);
		}

		if (SVNNodeKind.DIR.equals(nodeKind)) {
			System.out.println("要查看对应版本的条目在 '" + url + "'中是一个目录., 开始遍历目录层级");
			loopDir(relativePath);
		}

		if (SVNNodeKind.FILE.equals(nodeKind)) {
			downFile(relativePath);
		}

		System.out.println("");
		/*
		 * 获得版本库的最新版本号。
		 */
		long latestRevision = -1;
		try {
			latestRevision = repository.getLatestRevision();

			List<SVNLogEntry> entries = new ArrayList<SVNLogEntry>();
			try {
				repository.log(new String[] { "" }, // 为过滤的文件路径前缀，为空表示不进行过滤
						entries, -1, // -1代表最新的版本号，初始版本号为0
						-1, true, true);
			} catch (SVNException e) {
				e.printStackTrace();
			}
			System.out.println("当前log信息数量:" + entries.size());
			if (entries.size() > 0) {
				String message = entries.get(0).getMessage().toString();
				System.out.println("提交的message信息：" + message);
			}

		} catch (SVNException svne) {
			System.err.println("获取最新版本号时出错: " + svne.getMessage());
			System.exit(1);
		}
		System.out.println("");
		System.out.println("---------------------------------------------");
		System.out.println("版本库的最新版本号: " + latestRevision);
		System.exit(0);

	}
	
	public static void main(String[] args) throws Exception {
		Test test = new Test();
		test.test();
	}
}
