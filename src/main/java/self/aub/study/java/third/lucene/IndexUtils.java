package self.aub.study.java.third.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.wltea.analyzer.lucene.IKAnalyzer;

public class IndexUtils {
	/*private static Logger log = LoggerFactory.getLogger(IndexUtils.class);

	public static boolean createIndex(String srcDir, String indexDir) {
		IndexWriter indexWriter = createIndexWriter(indexDir);
		File[] srcFiles = new File(srcDir).listFiles();
		for (File srcFile : srcFiles) {
			if (srcFile.isDirectory() || !srcFile.getPath().endsWith(".txt")) {
				continue;
			}
			if (!createIndex(srcFile, indexWriter)) {
				closeIndexWriter(indexWriter);
				return false;
			}
		}
		closeIndexWriter(indexWriter);
		return true;
	}

	private static IndexWriter createIndexWriter(String indexDir) {
		try {
			FSDirectory fsDirectory = FSDirectory.open(new File(indexDir));
			IKAnalyzer ikAnalyzer = new IKAnalyzer();
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, ikAnalyzer);
			indexWriterConfig.setOpenMode(OpenMode.CREATE);
			return new IndexWriter(fsDirectory, indexWriterConfig);
		} catch (Exception e) {
			log.error("Exception : ", e);
			return null;
		}
	}

	private static void closeIndexWriter(IndexWriter indexWriter) {
		if (indexWriter != null) {
			try {
				indexWriter.close();
			} catch (Exception e) {
				log.error("Exception : ", e);
			}
		}
	}

	private static boolean createIndex(File srcFile, IndexWriter indexWriter) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(srcFile));
			String line;
			while ((line = br.readLine()) != null) {
//				String[] lineArr = line.split("\\s");
//				if (lineArr.length < 2) {
//					continue;
//				}
				Document document = new Document();
//				document.add(new Field("phone", lineArr[0], Field.Store.YES, Field.Index.NOT_ANALYZED));
//				document.add(new Field("province", lineArr[1], Field.Store.YES, Field.Index.NOT_ANALYZED));
				document.add(new Field("phone", line, Field.Store.YES, Field.Index.NOT_ANALYZED));
				indexWriter.addDocument(document);
			}
			br.close();
			return true;
		} catch (Exception e) {
			log.error("exception : ", e);
			return false;
		}
	}

	public static void main(String[] args) {
//		String src = "E:/tmp/lucene/src/";
//		String indexDir = "E:/tmp/lucene/index/";
//		String src = "D:/WorkSpace/tmp/zwblack/black/";
		String src = "D:/1/1/";
		String indexDir = "D:/WorkSpace/tmp/zwblack/blackIndex/";
		log.info("create index start");
		log.info("waiting...");

		boolean result = createIndex(src, indexDir);

		log.info("create result : {}", result);
		log.info("create index end");

	}*/
}
