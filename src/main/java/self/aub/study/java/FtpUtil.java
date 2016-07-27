package self.aub.study.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Administrator
 *
 */
public class FtpUtil {
	private static final Logger LOG = LoggerFactory.getLogger(FtpUtil.class);

	/**
	 *@param hostName
	 *@param port
	 *@param userName
	 *@param password
	 *@return FTPClient
	 *@author LJX
	 *@date 2010-8-11 下午05:30:27
	 *@comment  创建ftp的链接 
	 */
	private  static FTPClient ftpConnect(String hostName, Integer port,
			String userName, String password) {
		FTPClient ftpClient = new FTPClient();
		try {
			//建立连接
			ftpClient.connect(hostName, port);
			//登陆ftp
			ftpClient.login(userName, password);
			//设置缓冲区大小
			ftpClient.setBufferSize(1024);  
			//设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			//设置文件传输为二进制类型
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
		
		} catch (IOException e) {
			ftpClient = null;
			LOG.info("fail to get gonnect from  ftp server.");
			e.printStackTrace();
		}
		return ftpClient;

	} 
	
	/**
	 *  upload single file
	 * @param ftpClient
	 * @param remoteFileName : remote file name
	 * @param remoteFilePath : remote file path(does not contain filename)
	 * @param localFilePath : local file path(contain filename)
	 * @return
	 */
	private static boolean uploadFile(FTPClient ftpClient,
			String remoteFilePath, String remoteFileName, String localFilePath) {
		boolean result = false;
		try {
			FTPFile[] remotefiles = ftpClient.listFiles(remoteFilePath.concat(
					"/").concat(remoteFileName));
			if (remotefiles.length == 0) {
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(localFilePath));
				ftpClient.changeWorkingDirectory(remoteFilePath);
				ftpClient.storeFile(remoteFileName, bis);
				bis.close();
				result = true;
			} else {
				RandomAccessFile raf = new RandomAccessFile(localFilePath, "r");
				raf.seek(remotefiles[0].getSize());
				BufferedOutputStream bot = new BufferedOutputStream(ftpClient
						.appendFileStream(remoteFilePath.concat("/").concat(
								remoteFileName)));
				
				byte[] b = new byte[1024];
				int len;
				while ((len = raf.read(b)) != -1) {
					bot.write(b, 0, len);
				}
				bot.flush();
				bot.close();
				raf.close();
				result = true;
			}
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 *@param ftpClient
	 *@param localFilePath	
	 *@param remoteFilePath	remote file path:(does not contain file name)
	 *@param remoteFileName	remote file name(when you upload file ,then if  you wantn't  to rename file ,keep this param  null )
	 *@return boolean
	 *@author LJX
	 *@date 2010-8-13 下午04:39:53
	 *@comment 
	 */
	private static boolean upload(FTPClient ftpClient, String localFilePath,
			String remoteFilePath, String remoteFileName) {
		boolean result = true;
		try {
			File localFile = new File(localFilePath);
			if (!localFile.exists()) {
				result = true;
			} else {
				if (localFile.isDirectory()) {
					remoteFilePath = remoteFilePath.concat("/").concat(
							localFile.getName());
					ftpClient.makeDirectory(remoteFilePath);
					File[] oriFiles = localFile.listFiles();
					for (File file : oriFiles) {
						if (file.isDirectory()) {
							result &= upload(ftpClient, localFilePath.concat(
									"/").concat(file.getName()),
									remoteFilePath, remoteFileName);
						} else {
							result &= uploadFile(ftpClient, remoteFilePath,
									file.getName(), localFilePath.concat("/")
											.concat(file.getName()));
						}
					}
				} else {
					if (StringUtil.isEmpty(remoteFileName)) {
						remoteFileName = localFile.getName();
					}
					result &= uploadFile(ftpClient, remoteFilePath,
							remoteFileName, localFilePath);
				}
			}
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	/**
	 *  download single file
	 * @param ftpClient
	 * @param remoteFilePath : remote file path(contain filename)
	 * @param localFilePath : local file path(contain filename)
	 * @return
	 */
	private static boolean downloadFile(FTPClient ftpClient,
			String remoteFilePath, String localFilePath) {
		boolean result = false;
		BufferedOutputStream bos = null;
		try {
			File localFile = new File(localFilePath);
			if (localFile.exists()) {
				long localFileLength = localFile.length();
				ftpClient.setRestartOffset(localFileLength);
				bos = new BufferedOutputStream(new FileOutputStream(
						localFilePath, true));
				result = ftpClient.retrieveFile(remoteFilePath, bos);

			} else {
				FileUtils.createAbsoluteDir(localFilePath);
				bos = new BufferedOutputStream(new FileOutputStream(
						localFilePath));
				result = ftpClient.retrieveFile(remoteFilePath, bos);
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			return result;
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		}

		return result;
	}
	
	/**
	 * download file/folder from ftp server
	 * 
	 * @param ftpClient
	 * @param remoteFilePath
	 *            : remote file path
	 * @param localFilePath
	 *            : loacal file path(does not contain file name)
	 */
	private static boolean download(FTPClient ftpClient, String remoteFilePath,
			String localFilePath) {
		if (ftpClient == null || remoteFilePath == null
				|| localFilePath == null) {
			return false;
		}
		boolean result = true;
		try {
			FileUtils.createAbsoluteDir(localFilePath);
			FTPFile[] remotefiles = ftpClient.listFiles(remoteFilePath);
			if (remotefiles == null || remotefiles.length == 0) {
				result = true;
			} else if (remotefiles.length == 1) {
				if (remotefiles[0].isDirectory()) {
					result &= download(ftpClient, remoteFilePath.concat("/")
							.concat(remotefiles[0].getName()), localFilePath
							.concat("/").concat(remotefiles[0].getName())
							.concat("/"));
				} else {
					FTPFile[] remotefilesTemp = ftpClient
							.listFiles(remoteFilePath.concat("/").concat(
									remotefiles[0].getName()));
					if (remotefilesTemp == null || remotefilesTemp.length == 0) {
						result &= downloadFile(ftpClient, remoteFilePath,
								localFilePath.concat("/").concat(
										remotefiles[0].getName()));
					} else {
						result &= downloadFile(ftpClient, remoteFilePath
								.concat("/").concat(remotefiles[0].getName()),
								localFilePath.concat("/").concat(
										remotefiles[0].getName()));
					}
				}
			} else {
				for (FTPFile ftpFile : remotefiles) {
					if (ftpFile.isDirectory()) {
						download(ftpClient, remoteFilePath.concat("/").concat(
								ftpFile.getName()), localFilePath.concat("/")
								.concat(ftpFile.getName()).concat("/"));
					} else {
						result &= downloadFile(ftpClient, remoteFilePath
								.concat("/").concat(ftpFile.getName()),
								localFilePath.concat("/").concat(
										ftpFile.getName()));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return result;

	}
	
	/**
	 * download file/folder from ftp server
	 * @param ip
	 * @param port
	 * @param userName
	 * @param password
	 * @param remoteFilePath : remote file path
	 * @param localFilePath : loacal file path(does not contain file name)
	 * @return
	 */
	public static boolean download(String ip, int port, String userName,
			String password, String remoteFilePath, String localFilePath) {
		FTPClient ftpClient = ftpConnect(ip, port, userName, password);
		boolean result = download(ftpClient, remoteFilePath, localFilePath);
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			LOG.info("fail to disconnect with ftp server.");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * upload file/folder to ftp server
	 *@param ip
	 *@param port
	 *@param userName
	 *@param password
	 *@param remoteFilePath	remote file path:(does not contain file name)
	 *@param remoteFileName	remote file name:(when you upload file ,then if  you wantn't  to rename file, keep this param  null )
	 *@param localFilePath
	 *@return boolean
	 *@author LJX
	 *@date 2010-8-13 下午04:41:21
	 *@comment 
	 */
	public static boolean upload(String ip,int port,String userName,String password,String remoteFilePath,String remoteFileName,String localFilePath){
		FTPClient ftpClient = ftpConnect(ip, port, userName, password);
		boolean result = upload(ftpClient, localFilePath, remoteFilePath, remoteFileName);
		try {
			if(ftpClient.isConnected()){
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			LOG.info("fail to disconnect with ftp server.");
			e.printStackTrace();
		}
		return result;
	}
	 
	 
	  /**
	   * rename ftp file
	 *@param ftpClient
	 *@param remoteFilePath
	 *@param oldFileName
	 *@param newFileName
	 *@return boolean
	 *@author LJX
	 *@date 2010-8-25 下午03:00:47
	 *@comment 
	 */
	private  static boolean renameFile(FTPClient ftpClient,String remoteFilePath,String oldFileName,String newFileName){
		  boolean result = true;
	        try{
	        	ftpClient.changeWorkingDirectory(remoteFilePath);
	        	result &= ftpClient.deleteFile(newFileName);
	        	result &= ftpClient.rename(oldFileName, newFileName);
	         }catch(IOException ioe){
	        	 result = false;
	             ioe.printStackTrace();
	         }
	         return result;
	     }
	  
	  
	/** upload file and rename   ******kkupdate use
	 *@param ip
	 *@param port
	 *@param userName
	 *@param password
	 *@param remoteFilePath
	 *@param remoteFileName
	 *@param localFile
	 *@return boolean
	 *@author LJX
	 *@date 2010-8-25 下午03:28:31
	 *@comment 
	 */
	public static boolean uploadForKKupdate(String ip,int port,String userName,String password,String remoteFilePath,String remoteFileName,String localFile){
		FTPClient ftpClient = ftpConnect(ip, port, userName, password);
		boolean result = true;
		result &= upload(ftpClient, localFile, remoteFilePath, null);
		String oldFileName = FileUtils.getShortName(localFile);
		result &= renameFile(ftpClient, remoteFilePath, oldFileName, remoteFileName);
		try {
			if(ftpClient.isConnected()){
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			LOG.info("fail to disconnect with ftp server.");
			e.printStackTrace();
		}
		return result;
	}
	  
	
	
	  
	public static void main(String[] args) throws IOException {
		//uploadByFtp("192.168.0.127",21,"targtime","targtime","C:/ftp/upload/1/1.zip","/kaakoo/product/pc/main/file/","3.zip");
		//download("192.168.0.127",21,"targtime","targtime", "/kaakoo/product/pc/main/file/3.zip", "c:/ftp/download");
		//upload("192.168.0.127",21,"targtime","targtime", "/kaakoo/product/pc/main/file/",null,"C:/ftp/upload/1/");		
		
//		boolean b = uploadForKKupdate("192.168.0.23",21,"targtime","targtime", "/targtime/ftp/","kk.exe","C:/ftp/upload/1/1.exe");
//		System.out.println(b);
		boolean b = uploadForKKupdate("59.151.100.204",8021,"targtime","targtime", "e:/KaaKoo/zip","2.exe","C:/ftp/kaakoo.exe");
		System.out.println(b);
		
		//FileUtils.writeFile("2.3.0.0.3".getBytes(), "c:/index.html");
	}
}
