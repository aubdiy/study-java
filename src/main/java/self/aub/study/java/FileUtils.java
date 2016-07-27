package self.aub.study.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	/**
	 * 
	 * @param path
	 *            要建立的目录路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-6 下午06:52:41
	 * @comment
	 */
	public static void createAbsoluteDir(String path) {
		File f = null;
		int dirlen1 = path.lastIndexOf("\\");
		int dirlen2 = path.lastIndexOf("/");

		if (dirlen1 != -1) {
			f = new File(path.substring(0, dirlen1 + 1));
			f.mkdirs();
		}
		if (dirlen2 != -1) {
			f = new File(path.substring(0, dirlen2 + 1));
			f.mkdirs();
		}

	}

	/**
	 * 
	 * @param filePath
	 *            要读取的文件路径
	 * @return byte[]
	 * @author LJX
	 * @date 2010-4-6 下午04:45:14
	 * @comment 根据文件绝对路径读取文件，返回byte[]
	 */
	public static byte[] readFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile()) {
			return readFile(file);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param file
	 *            要读取的文件
	 * @return byte[]
	 * @author LJX
	 * @date 2010-4-6 下午04:49:52
	 * @comment 根据文件绝对路径读取文件，返回byte[]
	 */
	public static byte[] readFile(File file) {
		byte bf[] = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
			// TODO 如果文件过大int类型不够会有异常，
			/**
			 * 用750M的rmvb文件测试int类型未报异常 new byte[len]出现异常
			 */
			int len = fs.available();
			bf = new byte[len];
			fs.read(bf);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (null != fs) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return bf;
	}

	/**
	 * 
	 * @param b
	 *            字节数组
	 * @param dinPath
	 *            路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-6 下午06:53:50
	 * @comment 用字节数组向指定的路径文件中写入数据,原有内容被覆盖
	 */
	public static void writeFile(byte[] b, String dinPath) {
		BufferedOutputStream bos = null;
		try {
			createAbsoluteDir(dinPath);

			bos = new BufferedOutputStream(new FileOutputStream(dinPath));
			bos.write(b);
			bos.flush();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != bos) {
					bos.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 
	 * @param originalPath
	 *            原始路径
	 * @param destinationPath
	 *            目标路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 上午12:00:54
	 * @comment 拷贝文件
	 */
	public static void copyFile(String originalPath, String destinationPath) {
		File originalFile = new File(originalPath);
		File destinationFile = new File(destinationPath);
		copyFile(originalFile, destinationFile);
	}

	/**
	 * 
	 * @param originalFile
	 *            原始文件
	 * @param destinationPath
	 *            目标路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午03:22:00
	 * @comment 拷贝文件
	 */
	public static void copyFile(File originalFile, String destinationPath) {
		File destinationFile = new File(destinationPath);
		copyFile(originalFile, destinationFile);
	}

	/**
	 * 
	 * @param originalFile
	 *            原始文件
	 * @param destinationFile
	 *            目标文件
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午03:19:40
	 * @comment 拷贝文件
	 */
	public static void copyFile(File originalFile, File destinationFile) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(originalFile));
			createAbsoluteDir(destinationFile.getAbsolutePath());
			bos = new BufferedOutputStream(new FileOutputStream(destinationFile));
			byte[] b = new byte[5120];// 1024*5
			int len = 0;
			while ((len = bis.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param originalFolder
	 *            原始文件夹路径
	 * @param destinationFolder
	 *            目标文件夹路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午06:07:18
	 * @comment 拷贝文件夹
	 */
	public static void copyFolder(String originalFolder, String destinationFolder) {
		File originalFolderFile = new File(originalFolder);
		if (!originalFolderFile.exists()) {
			throw new RuntimeException("original Folder File is not exists");
		} else if (!originalFolderFile.isDirectory()) {
			throw new RuntimeException("original Folder File is not a directory");
		}
		File destinationFolderFile = new File(destinationFolder);
		destinationFolderFile.mkdirs();
		String[] tempFileNames = originalFolderFile.list();
		File tempOriginalFile = null;
		File tempDestinationFile = null;
		for (String temFileName : tempFileNames) {
			tempOriginalFile = new File(originalFolderFile.getAbsolutePath() + File.separator + temFileName);
			tempDestinationFile = new File(destinationFolderFile.getAbsolutePath() + File.separator + temFileName);
			if (tempOriginalFile.isFile()) {
				// 拷贝文件
				copyFile(tempOriginalFile, tempDestinationFile);
			} else if (tempOriginalFile.isDirectory()) {
				// 递归拷贝目录
				copyFolder(tempOriginalFile.getAbsolutePath(), tempDestinationFile.getAbsolutePath());
			}
		}
	}

	/**
	 * 
	 * @param filePath
	 *            文件路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午03:49:56
	 * @comment 删除文件
	 */
	public static void delFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 
	 * @param folderPath
	 *            文件夹路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午04:30:29
	 * @comment 删除文件夹
	 */
	public static void delFolder(String folderPath) {
		// 删除文件夹里面所有内容
		delAllFile(folderPath);
		File file = new File(folderPath);
		// 删除空文件夹
		file.delete();
	}

	/**
	 * 
	 * @param path
	 *            文件夹路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午04:32:23
	 * @comment 删除文件夹内所有内容
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		} else if (!file.isDirectory()) {
			return;
		} else {
			String[] tempFileNames = file.list();
			File temFile = null;
			for (String tempFileName : tempFileNames) {
				temFile = new File(file.getAbsolutePath() + File.separator + tempFileName);
				if (temFile.isFile()) {
					temFile.delete();
				} else if (temFile.isDirectory()) {
					// 递归删除文件夹
					delFolder(temFile.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 
	 * @param originalPath
	 *            源文件路径
	 * @param destinationPath
	 *            目标文件路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午06:59:57
	 * @comment 移动文件
	 */
	public static void moveFile(String originalPath, String destinationPath) {
		copyFile(originalPath, destinationPath);
		delFile(originalPath);
	}

	/**
	 * 
	 * @param originalFolder
	 *            源文件夹路径
	 * @param destinationFolder
	 *            目标文件夹路径
	 * @return void
	 * @author LJX
	 * @date 2010-4-7 下午07:00:49
	 * @comment 移动文件夹
	 */
	public static void moveFolder(String originalFolder, String destinationFolder) {
		copyFolder(originalFolder, destinationFolder);
		delFolder(originalFolder);
	}

	/**
	 * 
	 * @param fileName
	 *            文件名
	 * @return String 文件扩展名
	 * @author LJX
	 * @date 2010-4-7 下午07:13:04
	 * @comment 根据文件名返回文件扩展名
	 */
	public static String getSuffix(String fileName) {
		if (fileName != null && fileName.lastIndexOf(".") > 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param filePath
	 *            文件的绝对路径
	 * @return String 文件所在目录
	 * @author LJX
	 * @date 2010-4-7 下午07:21:24
	 * @comment 根据文件的绝对路径获得文件所在目录
	 */
	public static String getFolder(String filePath) {
		if (filePath != null) {
			if ((filePath.lastIndexOf("/") > 0) || (filePath.lastIndexOf("\\") > 0)) {
				if (filePath.lastIndexOf("\\") > filePath.lastIndexOf("/")) {
					return filePath.substring(0, filePath.lastIndexOf("\\") + 1);
				} else {
					return filePath.substring(0, filePath.lastIndexOf("/") + 1);
				}
			}
		}
		return "";
	}

	/**
	 * 
	 * @param filePath
	 *            文件路径
	 * @return String
	 * @author LJX
	 * @date 2010-4-7 下午08:03:16
	 * @comment 根据文件路径得到短文件名
	 */
	public static String getShortName(String filePath) {
		return filePath.substring(getFolder(filePath).length());
	}

	/**
	 * 
	 * @param filePath
	 *            文件路径
	 * @return String 短文件名
	 * @author LJX
	 * @date 2010-4-7 下午08:02:01
	 * @comment 根据文件路径得到文件的没有后缀名的短文件名
	 */
	public static String getShortNameNoSuffix(String filePath) {
		if (null != filePath) {
			int folderLen = getFolder(filePath).length();
			String suffix = getSuffix(filePath);
			if (null == suffix) {
				return filePath.substring(folderLen);
			} else {
				return filePath.substring(folderLen, filePath.length() - suffix.length() - 1);
			}
		}
		return "";
	}

	/**
	 * 
	 * @param filePath
	 *            文件
	 * @return int 文件大小
	 * @author LJX
	 * @date 2010-4-7 下午08:08:27
	 * @comment 获取文件大小
	 */
	public static int getFileSize(String filePath) {
		File file = new File(filePath);
		return getFileSize(file);
	}

	/**
	 * 
	 * @param file
	 *            文件
	 * @return int 文件大小
	 * @author LJX
	 * @date 2010-4-7 下午08:08:27
	 * @comment 获取文件大小
	 */
	public static int getFileSize(File file) {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			// TODO int类型大小有限
			int size = bis.available();
			return size;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != bis) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String readFileForChar(String filePath) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String s;
			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String readFileForChar(File fileName) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String s;
			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {

		writeFile("1\r2".getBytes(), "c:/2.txt");
		// String string = new String(readFile("c:/1.txt"));
		// System.out.println(string.equals("1\n2"));
		// System.out.println(new String(readFile("c:/1.txt")));
	}
}
