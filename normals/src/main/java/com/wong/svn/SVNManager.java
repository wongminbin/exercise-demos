package com.wong.svn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
* @author Huang zhibin
* 
* 2017年9月14日 下午2:10:46
*/
public class SVNManager {
	private String url;
    private String username;
    private String password;
    private SVNRepository repository;
    
    public SVNManager(String url, String username, String password) {
        super();
        this.url = url;
        this.username = username;
        this.password = password;
        initialize();
    }
    /**
     * 初始化操作
     * @throws SVNException
     */
    private void initialize() {
        FSRepositoryFactory.setup();
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        try {
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password.toCharArray());
            repository = SVNRepositoryFactoryImpl.create(SVNURL.parseURIEncoded(url));
            repository.setAuthenticationManager(authManager);
        } catch (SVNException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 从SVN服务器获取最新版本的文件
     * @param filePath 相对于仓库根目录的路径
     * @param outputStream 要输出的目标流，可以是文件流 FileOutputStream
     * @return 返回checkout文件的版本号
     * @throws Exception 可以自定义Exception
     */
    public long getFileFromSVN(String filePath, String outFileName) throws SVNCheckoutException {
        return getFileFromSVN(filePath, outFileName, 0);
    }
    
    /**
     * 从SVN服务器获取文件
     * @param filePath 相对于仓库根目录的路径
     * @param outputStream 要输出的目标流，可以是文件流 FileOutputStream
     * @param version 要checkout的版本号，当传入的版本号为0时，默认获取最新版本
     * @return 返回checkout文件的版本号
     * @throws Exception 可以自定义Exception
     */
    public long getFileFromSVN(String filePath, String outFileName, long version) throws SVNCheckoutException {
        SVNNodeKind node = null;
        try {
            if(version == 0){
                version = repository.getLatestRevision();
            }
            node = repository.checkPath(filePath, version);
        } catch (SVNException e) {
            throw new SVNCheckoutException("SVN检测不到该文件:" + filePath, e);
        }
        if (node != SVNNodeKind.FILE) {
            throw new SVNCheckoutException(node.toString() + "不是文件");
        }
        SVNProperties properties = new SVNProperties();
        try {
            OutputStream outputStream = new FileOutputStream(outFileName);
            repository.getFile(filePath, version, properties, outputStream);
            outputStream.close();
        } catch (SVNException e) {
            throw new SVNCheckoutException("获取SVN服务器中的" + filePath + "文件失败", e);
        } catch (IOException e) {
            throw new SVNCheckoutException("SVN check out file faild.", e);
        } 
        return Long.parseLong(properties.getStringValue("svn:entry:revision"));
    }
    
    /**
     * 获取目录下的所有文件和子目录
     * @param res 包含目录参数的资源对象.参加{@link Resource#getPath()}
     * @return 资源列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Resource> getChildren(Resource res) throws Exception {
        String path = res.getPath();
        Collection<SVNDirEntry> entries;
        try {
            entries = repository.getDir(path, -1, null, (Collection<SVNDirEntry>) null);
        } catch (SVNException e) {
            throw new Exception("获得" + path + "下级目录失败", e);
        }
        List<Resource> result = new ArrayList<Resource>();
        for (SVNDirEntry entry : entries) {
            if (containsSpecialFile(entry)) {
                Resource resource = new Resource();
                resource.setName(entry.getName());
                resource.setPath(entry.getURL().getPath());
                resource.setFile(entry.getKind() == SVNNodeKind.FILE);
                result.add(resource);
            }
        }
        return result;
    }
    
    /**
     * 判断文件是否存在
     * @param entry 要判断的节点.参加{@link SVNDirEntry}
     * @return 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private boolean containsSpecialFile(SVNDirEntry entry) throws Exception {
        if (entry.getKind() == SVNNodeKind.FILE) {
            return true;
        } else if (entry.getKind() == SVNNodeKind.DIR) {
            Collection<SVNDirEntry> entries;
            String path = entry.getURL().getPath();
            try {
                entries = repository.getDir(path, -1, null, (Collection<SVNDirEntry>) null);
            } catch (SVNException e) {
                throw new Exception("获得" + path + "下级目录失败", e);
            }
            for (SVNDirEntry unit : entries) {
                if (containsSpecialFile(unit)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public static void main(String[] args) {
    	String url = "https://10.10.0.198/svn/apps/04_系统重构/04_源码/trunk/esl-projects/esl-statics";
    	String user = "huangzhibin";
    	String password = "123456";
    	
        SVNManager manager = new SVNManager(url, user, password);
        try {
        	Resource res = new Resource();
        	res.setPath("/04_系统重构/04_源码/trunk/esl-projects/esl-statics");
        	List<Resource> list = manager.getChildren(res);
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
