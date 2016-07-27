package self.aub.study.java.third.lucene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.LineIterator;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearcherUtils {
	private static Logger log = LoggerFactory.getLogger(SearcherUtils.class);
	// private static IndexSearcher indexSearcher =
	// createIndexSearcher("E:/tmp/lucene/index/");
//	private static IndexSearcher indexSearcher = createIndexSearcher("D:/WorkSpace/tmp/zwblack/blackIndex/");
	// private static String[] fields = { "phone", "province", "city" };
	// private static QueryParser queryParser = new
	// MultiFieldQueryParser(Version.LUCENE_35, fields, new IKAnalyzer());
//	private static QueryParser queryParser = new QueryParser(Version.LUCENE_35, "phone", new StandardAnalyzer(
//			Version.LUCENE_35));

	/*public static String search(String str) {
		try {
			Query query = queryParser.parse(str);
			TopDocs topDocs = indexSearcher.search(query, null, 1);
			ScoreDoc[] scoreDoc = topDocs.scoreDocs;
			if (scoreDoc.length > 0) {
				Document doc = indexSearcher.doc(scoreDoc[0].doc);
				// return doc.get("province");
				return doc.get("phone");
				// log.info("phone : {}, pro : {}",doc.get("phone"),doc.get("province"));
			}

		} catch (Exception e) {
			log.error("Exception : ", e);
		}
		return "";
	}

	private static IndexSearcher createIndexSearcher(String indexDir) {
		try {
			Directory directory = FSDirectory.open(new File(indexDir));
			RAMDirectory ramDirectory = new RAMDirectory(directory);
			IndexReader indexReader = IndexReader.open(ramDirectory);
			return new IndexSearcher(indexReader);
		} catch (Exception e) {
			log.error("Exception : ", e);
			return null;
		}
	}

	private static void createSql() throws Exception {
//		final File file = new File("D:/WorkSpace/tmp/zwblack/zwBlack/2012_06_18_In_Black.txt");
//		final File sqlFile = new File("D:/WorkSpace/tmp/zwblack/sql/2012_06_18_In_Black.sql");
//		final File balckFile = new File("D:/WorkSpace/tmp/zwblack/black/2012_06_18_add.txt");
		final File file = new File("D:/1/2012-06.txt");
		final File sqlFile = new File("D:/1/2012-06.sql");
		final File balckFile = new File("D:/1/2012-06_add.txt");
		final BufferedWriter bfwSql = new BufferedWriter(new FileWriter(sqlFile));
		final BufferedWriter bfwTxt = new BufferedWriter(new FileWriter(balckFile));
		bfwSql.write("INSERT INTO black_list(phone,channel_id) VALUES");
		final LineIterator li = FileUtils.lineIterator(file);
		while (li.hasNext()) {
			final String phone = li.next();
			String result = search(phone);
			if ("".equals(result)) {
				bfwSql.write("('".concat(phone).concat("','21110'),"));
				bfwTxt.write(phone.concat("\n"));
			}
		}
		li.close();
		bfwSql.close();
		bfwTxt.close();
	}

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		// for (int i = 0; i < 10000; i++) {
		// String s = search("1590118");
		// System.out.println(s);
		//
		// }

		createSql();

		long end = System.currentTimeMillis();
		log.info("共用时 ：{} ms", end - start);
		// log.info("共有 : {} 条", topDocs.totalHits);
	}*/
}
