package com.wong.svn;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNClient {

	// 定义svn版本库的URL。
	private SVNURL repositoryURL;
	
	// 定义版本库。
	private SVNRepository repository;

	private String svnRootUrl;

	private String path;

	private String name;

	private String password;

	public SVNClient(String svnRootUrl, String path, String name, String password) {

		this.svnRootUrl = svnRootUrl;
		this.path = path;
		this.name = name;
		this.password = password;

		// 初始化库。 必须先执行此操作。具体操作封装在setupLibrary方法中。
		setupLibrary();

		initSVN();
	}
	
	public String getSvnRootUrl() {
		return svnRootUrl;
	}
	
	public String getPath() {
		return path;
	}

	public SVNURL getRepositoryURL() {
		return repositoryURL;
	}

	public SVNRepository getRepository() {
		return repository;
	}

	/*
	 * 初始化库
	 */
	private void setupLibrary() {
		/*
		 * For using over http:// and https://
		 */
		DAVRepositoryFactory.setup();
		/*
		 * For using over svn:// and svn+xxx://
		 */
		SVNRepositoryFactoryImpl.setup();

		/*
		 * For using over file:///
		 */
		FSRepositoryFactory.setup();
	}

	private void initSVN() {
		try {
			// 获取SVN的URL。
			repositoryURL = SVNURL.parseURIEncoded(svnRootUrl);
			// 根据URL实例化SVN版本库。
			repository = SVNRepositoryFactory.create(repositoryURL);
		} catch (SVNException svne) {
			/*
			 * 打印版本库实例创建失败的异常。
			 */
			System.err.println("创建版本库实例时失败，版本库的URL是 '" + svnRootUrl + "': " + svne.getMessage());
			System.exit(1);
		}

		/*
		 * 对版本库设置认证信息。
		 */
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(null, name,
				password.toCharArray(), false);
		repository.setAuthenticationManager(authManager);
	}
}
