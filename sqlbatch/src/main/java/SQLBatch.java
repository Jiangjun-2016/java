import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * 维护旧版系统原始数据 第一步：更新xxx表，入库yyy表
 *
 * @author fan 2017-07-20
 */
public class SQLBatch {

	/**
	 * 查询
	 */
	private static String sql = null;
	private static DaoUtil db1 = null;
	private static ResultSet ret = null;

	/**
	 * 插入与更新
	 */

	public static final String url = "jdbc:mysql://127.0.0.1:3306/solr?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "root";

	public static Connection connInsert = null;
	public static Statement pstInsert = null;
	public static Connection connUpdata = null;
	public static Statement pstUpdata = null;

	public static void main(String[] args) {

		sql = "select * from xxx where eventID <= '1702220007001'";
		db1 = new DaoUtil(sql);
		List<String> sqlKnowledgeinfoList = new ArrayList<String>();
		List<String> sqlDocumentinfoList = new ArrayList<String>();

		try {
			ret = db1.pst.executeQuery();
			while (ret.next()) {
				String eventID = ret.getString(1); // eventID
				String noteurl = ret.getString(7); // 文档路径
				String ktype = ret.getString(8); // 知识库分类
				String documentid = ret.getString(26); // 文档id
				// 判断路劲是否为空，为空则不进行文档分析
				String dnoteurl = noteurl;
				if (!"undefined".equals(noteurl)) {
					String documentID = "";
					noteurl = noteurl.replaceAll("/", "\\\\");
					String[] noteUrls = noteurl.split(";");
					String[] dnoteUrls = dnoteurl.split(";");
					for (int j = 1; j < noteUrls.length + 1; j++) {
						if (j == 1) {
							documentID += eventID + getDocumentID(j);
						} else {
							documentID += ";" + eventID + getDocumentID(j);
						}
					}
					/** 组装更新 knowledgeinfo表 SQL语句 */
					String sqlKnowledgeinfo = "UPDATE knowledgeinfo SET documentid = '" + documentID
							+ "' WHERE eventID = '" + eventID + "' ";
					sqlKnowledgeinfoList.add(sqlKnowledgeinfo);

					/** 文档id 关键词，热词，文摘入库 */
					String[] documengIDs = documentID.split(";");
					/** 装配分析文档路劲 */
					for (int i = 0; i < noteUrls.length; i++) {
						String filePath = "C:\\Users\\fan\\Desktop\\知识库管理";
						noteUrls[i] = noteUrls[i].substring(11);
						filePath += noteUrls[i];
						/** 文档分析 start */
						String text = "";
						// 获取文摘
						String phrase = "";
						int phraseLength = phrase.length();
						if (phraseLength > 500) {// 截取前500字符
							phrase = phrase.substring(0, 500);
						}
						// 获取关键词
						Set<String> keywordSet = new HashSet<String>();
						String dkeyword = StringUtils.join(keywordSet.toArray(), ";");
						// 获取热词
						Map<String, Integer> hotwords = new HashMap<String, Integer>();
						String dhotword = StringUtils.join(hotwords.keySet().toArray(), ";");
						/** documentInfo表入库 */
						String insertDocumentinfoSql = "insert into documentinfo (id,dkeyword,dhotword,dnoteurl,dsummary) values('"
								+ documengIDs[i] + "','" + dkeyword + "','" + dhotword + "','" + dnoteUrls[i] + "','"
								+ phrase + "')";
						sqlDocumentinfoList.add(insertDocumentinfoSql);
						/** 文档分析 end */
						// System.out.println(filePath.toString());
						// System.out.println(documengIDs[i]);
					}
				} else {
					// System.out.println("false"+ "\t"+eventID + "\t" +
					// noteurl);
				}
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			Class.forName(name);
			connInsert = DriverManager.getConnection(url, user, password);
			connUpdata = DriverManager.getConnection(url, user, password);
			connInsert.setAutoCommit(false); // 设置手动提交
			connUpdata.setAutoCommit(false); // 设置手动提交
			pstInsert = connInsert.createStatement();
			pstUpdata = connUpdata.createStatement();
			/** 入库documengInfo表*/
			for (int i = 0; i < sqlDocumentinfoList.size(); i++) {
				pstInsert.addBatch(sqlDocumentinfoList.get(i));
			}
			/** 更新knowledgeInfo表*/
			for (int i = 0; i < sqlKnowledgeinfoList.size(); i++) {
				pstUpdata.addBatch(sqlKnowledgeinfoList.get(i));
			}
			int[] documentCounts = pstInsert.executeBatch();
			int[] knowledgeinfoCounts = pstUpdata.executeBatch();
			System.out.println("knowledgeinfoCounts:" + knowledgeinfoCounts.length);
			System.out.println("documentCounts:" + documentCounts.length);
			connInsert.commit();
			connUpdata.commit();
			connInsert.setAutoCommit(true);//在把自动提交打开
			connUpdata.setAutoCommit(true);//在把自动提交打开
		} catch (Exception e) {
			e.printStackTrace();
			/** 两组sql，任意一组出现异常，则均回滚操作*/
			try {
				System.out.println("异常，进行回滚。");
				connInsert.rollback();
				connUpdata.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			/** 关闭连接*/
			try {
				pstInsert.close();
				pstUpdata.close();
				connInsert.close();
				connUpdata.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		// TODO入库
		System.out.println("sqlKnowledgeinfoList:" + sqlKnowledgeinfoList.size());
//		for (int i = 0; i < sqlKnowledgeinfoList.size(); i++) {
//			System.out.println(sqlKnowledgeinfoList.get(i));
//		}
//
//		// TODO入库
//		for (int i = 0; i < sqlDocumentinfoList.size(); i++) {
//			System.out.println(sqlDocumentinfoList.get(i));
//		}
		System.out.println("sqlDocumentinfoList:" + sqlDocumentinfoList.size());
	}

	/**
	 * 组装documentID
	 *
	 * @param j
	 * @return
	 */
	private static String getDocumentID(int j) {
		int len = String.valueOf(j).length();
		String value = "";
		switch (len) {
			case 1:
				value = "00" + j;
				break;
			case 2:
				value = "0" + j;
				break;
			case 3:
				value = "" + j;
				break;
			default:
				break;
		}
		return value;
	}

}
