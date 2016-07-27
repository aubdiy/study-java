package self.aub.study.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public final class CompressUtil {
	private static final int BUFFERED_SIZE = 1024;

	private CompressUtil() {
	}

	/**
	 * <B>compress file to gz</B><br>
	 * 
	 * @author liujinxin
	 * @param srcFile
	 * @param desFile
	 * @return true is compress file to gz success. false otherwise.
	 * @throws IOException
	 *             if an IO error occurs while checking the files.
	 */
	public static boolean compress2Gz(String srcFile, String desFile) throws IOException {
		checkNullParam(srcFile, desFile);
		return compress2Gz(new File(srcFile), new File(desFile));
	}

	/**
	 * <B>compress file to gz</B><br>
	 * 
	 * @author liujinxin
	 * @param srcFile
	 * @param desFile
	 * @return true is compress file to gz success. false otherwise.
	 * @throws IOException
	 *             if an IO error occurs while checking the files.
	 */
	public static boolean compress2Gz(File srcFile, File desFile) throws IOException {
		checkNullParam(srcFile, desFile);
		checkCompress2GzParam(srcFile, desFile);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(srcFile));
			bos = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(desFile)));
			final byte[] bufferedByteArr = new byte[BUFFERED_SIZE];
			int readIndex;
			while ((readIndex = bis.read(bufferedByteArr)) != -1) {
				bos.write(bufferedByteArr, 0, readIndex);
			}
		} finally {
			closeQuietly(bis);
			closeQuietly(bos);
		}
		return true;
	}

	/**
	 * <B>file to gz </B><br>
	 * 
	 * @author liujinxin
	 * @param srcFile
	 * @param desFile
	 * @param checksum
	 * @return checksum value
	 * @throws IOException
	 *             if an IO error occurs while checking the files.
	 */
	public static long compress2Gz(String srcFile, String desFile, Checksum checksum) throws IOException {
		checkNullParam(srcFile, desFile, checksum);
		return compress2Gz(new File(srcFile), new File(desFile), checksum);
	}

	/**
	 * <B>file to gz</B><br>
	 * 
	 * @author liujinxin
	 * @param srcFile
	 * @param desFile
	 * @param checksum
	 * @return checksum value
	 * @throws IOException
	 *             if an IO error occurs while checking the files.
	 */
	public static long compress2Gz(File srcFile, File desFile, Checksum checksum) throws IOException {
		checkNullParam(srcFile, desFile, checksum);
		checkCompress2GzParam(srcFile, desFile);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		CheckedOutputStream cos = null;
		long hecksumValue;
		try {
			bis = new BufferedInputStream(new FileInputStream(srcFile));
			cos = new CheckedOutputStream(new FileOutputStream(desFile), checksum);
			bos = new BufferedOutputStream(new GZIPOutputStream(cos));
			final byte[] bufferedByteArr = new byte[BUFFERED_SIZE];
			int readIndex;
			while ((readIndex = bis.read(bufferedByteArr)) != -1) {
				bos.write(bufferedByteArr, 0, readIndex);
			}
			hecksumValue = cos.getChecksum().getValue();
		} finally {
			closeQuietly(bis);
			closeQuietly(cos);
			closeQuietly(bos);
		}
		return hecksumValue;
	}

	public static void compress2Zip(String srcFile, String desFile) throws IOException {
		checkNullParam(srcFile, desFile);
		compress2Zip(new File(srcFile), new File(desFile));
	}

	public static void compress2Zip(File srcFile, File desFile) throws IOException {
		checkNullParam(srcFile, desFile);
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("srcFile '" + srcFile + "' does not exist");
		}
		if (desFile.exists()) {
			if (!desFile.isFile()) {
				throw new IllegalArgumentException("desFile '" + desFile + "' is not a file");
			}
		} else {
			if (!mkParentDirs(desFile)) {
				throw new SecurityException("desFile '" + desFile + "' parent dirs could not be created");
			}
		}
		ZipOutputStream zipOps = null;
		try {
			zipOps = new ZipOutputStream(new FileOutputStream(desFile));
			if (srcFile.isFile()) {
				compressFile2Zip(srcFile, "", zipOps);
			} else {
				compressDir2Zip(srcFile, "", zipOps);
			}
		} finally {
			closeQuietly(zipOps);
		}
	}

	private static void compressDir2Zip(File srcFile, String zipDir, ZipOutputStream zipOps) throws IOException {
		final File[] files = srcFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				compressFile2Zip(file, zipDir.concat(file.getName()), zipOps);
			} else {
				final String zipDirTmp = zipDir.concat(file.getName()).concat("/");
				final ZipEntry zipEntry = new ZipEntry(zipDirTmp);
				zipOps.putNextEntry(zipEntry);
				zipOps.closeEntry();
				compressDir2Zip(file, zipDirTmp, zipOps);
			}
		}
	}

	private static void compressFile2Zip(File file, String zipDir, ZipOutputStream zipOps) throws IOException {
		BufferedInputStream bis = null;
		final byte[] bufferedByteArr = new byte[BUFFERED_SIZE];
		int readIndex;
		final ZipEntry zipEntry = new ZipEntry(zipDir);
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			zipOps.putNextEntry(zipEntry);
			while ((readIndex = bis.read(bufferedByteArr)) != -1) {
				zipOps.write(bufferedByteArr, 0, readIndex);
			}
			zipOps.closeEntry();
		} finally {
			closeQuietly(bis);
		}
	}

	public static boolean decompressGz(String srcFile, String desFile) throws IOException {
		checkNullParam(srcFile, desFile);
		return decompressGz(new File(srcFile), new File(desFile));
	}

	public static boolean decompressGz(File srcFile, File desFile) throws IOException {
		checkNullParam(srcFile, desFile);
		checkDecompressGzParam(srcFile, desFile);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new GZIPInputStream(new FileInputStream(srcFile)));
			bos = new BufferedOutputStream(new FileOutputStream(desFile));
			final byte[] bufferedByteArr = new byte[BUFFERED_SIZE];
			int readIndex;
			while ((readIndex = bis.read(bufferedByteArr)) != -1) {
				bos.write(bufferedByteArr, 0, readIndex);
			}
		} finally {
			closeQuietly(bis);
			closeQuietly(bos);
		}
		return true;
	}

	public static boolean decompressGz(String srcFile, String desFile, Checksum checksum, long checksumValue)
			throws IOException {
		checkNullParam(srcFile, desFile, checksum);
		return decompressGz(new File(srcFile), new File(desFile), checksum, checksumValue);
	}

	public static boolean decompressGz(File srcFile, File desFile, Checksum checksum, long checksumValue)
			throws IOException {
		checkNullParam(srcFile, desFile, checksum);
		checkDecompressGzParam(srcFile, desFile);
		CheckedInputStream cis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			cis = new CheckedInputStream(new FileInputStream(srcFile), checksum);
			bis = new BufferedInputStream(new GZIPInputStream(cis));
			bos = new BufferedOutputStream(new FileOutputStream(desFile));
			final byte[] bufferedByteArr = new byte[BUFFERED_SIZE];
			int readIndex;
			while ((readIndex = bis.read(bufferedByteArr)) != -1) {
				bos.write(bufferedByteArr, 0, readIndex);
			}
			if (checksumValue == cis.getChecksum().getValue()) {
				return true;
			}
		} finally {
			closeQuietly(cis);
			closeQuietly(bis);
			closeQuietly(bos);
		}
		return false;
	}

	public static void decompressZip(String srcFile, String desDir) throws IOException {
		// TODO 校验
		checkNullParam(srcFile, desDir);
		decompressZip(new File(srcFile), desDir);
	}

	public static void decompressZip(File srcFile, String desDir) throws IOException {
		// TODO 校验
		if (!desDir.endsWith(File.separator)) {
			desDir = desDir.concat(File.separator);
		}
		final ZipFile zipFile = new ZipFile(srcFile);
		final Enumeration<? extends ZipEntry> zipEntryEnum = zipFile.entries();
		while (zipEntryEnum.hasMoreElements()) {
			final ZipEntry zipEntry = zipEntryEnum.nextElement();
			final String targetPath = desDir.concat(zipEntry.getName());
			final File targetFile = new File(targetPath);
			if (zipEntry.isDirectory()) {
				mkDirs(targetFile);
				continue;
			}
			mkParentDirs(targetFile);
			// 打开文件输出流
			final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
			// 从ZipFile对象中打开entry的输入流
			final BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
			final byte[] bufferedByteArr = new byte[BUFFERED_SIZE];
			int readIndex;
			while ((readIndex = bis.read(bufferedByteArr)) != -1) {
				bos.write(bufferedByteArr, 0, readIndex);
			}
			closeQuietly(bis);
			closeQuietly(bos);
		}

	}

	private static void checkNullParam(Checksum checksum) {
		if (checksum == null) {
			throw new NullPointerException("checksum must not be null");
		}
	}

	private static void checkNullParam(String srcFile, String desFile) {
		if (srcFile == null) {
			throw new NullPointerException("srcFile must not be null");
		}
		if (desFile == null) {
			throw new NullPointerException("desFile must not be null");
		}
	}

	private static void checkNullParam(String srcFile, String desFile, Checksum checksum) {
		checkNullParam(srcFile, desFile);
		checkNullParam(checksum);
	}

	private static void checkNullParam(File srcFile, File desFile) {
		if (srcFile == null) {
			throw new NullPointerException("srcFile must not be null");
		}
		if (desFile == null) {
			throw new NullPointerException("desFile must not be null");
		}
	}

	private static void checkNullParam(File srcFile, File desFile, Checksum checksum) {
		checkNullParam(srcFile, desFile);
		checkNullParam(checksum);
	}

	private static void checkCompress2GzParam(File srcFile, File desFile) {
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException("srcFile '" + srcFile + "' is not a file");
		}
		if (desFile.exists()) {
			if (!desFile.isFile()) {
				throw new IllegalArgumentException("desFile '" + desFile + "' is not a file");
			}
		} else {
			if (!mkParentDirs(desFile)) {
				throw new SecurityException("desFile '" + desFile + "' parent dirs could not be created");
			}
		}
	}

	private static void checkDecompressGzParam(File srcFile, File desFile) {
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException("srcFile '" + srcFile + "' is not a file");
		}
		if (!mkParentDirs(desFile)) {
			throw new SecurityException("desFile '" + desFile + "' parent dirs could not be created");
		}
	}

	private static boolean mkDirs(File file) {
		if (file.exists()) {
			return true;
		}
		return file.mkdirs();
	}

	private static boolean mkParentDirs(File file) {
		final File parent = file.getParentFile();
		if (parent.exists()) {
			return true;
		}
		return parent.mkdirs();
	}

	private static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException ioe) {
			// ignore
		}
	}

	public static void main(String[] args) throws IOException {
		final String srcGzFile = "D:/AAA/gz/a.txt";
		final String compress2GzFile = "D:/AAA/gz/a2gz.gz";
		final String decompressGzFile = "D:/AAA/gz/gz2a.txt";

//		final String srcZipFile = "D:/WorkSpace/tmp/zip/src/1.txt";
		final String srcZipDir = "D:/WorkSpace/tmp/zip/src/";
		final String compress2ZipFile = "D:/WorkSpace/tmp/zip/compress/1.zip";
		final String decompressZipFile = "D:/WorkSpace/tmp/zip/decompress/";

		final long checksumValue = compress2Gz(srcGzFile, compress2GzFile, new Adler32());
		System.out.println("checksumValue: " + checksumValue);
		final boolean result = decompressGz(compress2GzFile, decompressGzFile, new CRC32(), checksumValue);
		System.out.println(result);
		compress2Gz(srcGzFile, compress2GzFile);
		decompressGz(compress2GzFile, decompressGzFile);

		compress2Zip(srcZipDir, compress2ZipFile);
		decompressZip(compress2ZipFile, decompressZipFile);
	}
}
