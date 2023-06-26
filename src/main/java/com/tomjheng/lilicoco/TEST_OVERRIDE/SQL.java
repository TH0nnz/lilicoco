package com.tomjheng.lilicoco.TEST_OVERRIDE;

public class SQL {

	public static void main(String[] args) {
		String str = " SELECT toString(web_site_type)||user_id as userId, web_site_type AS webSiteType, currency,  affiliate AS agentUserId  FROM  game_tx_summary WHERE agentuserid2 = ? AND affiliate IS NOT NULL AND affiliate <> parent_user_id  AND ( (biz_date >= ? AND biz_date < ?   AND platform IN (?,?,?,?,?,?,?,?,?,?,?,?,?)) OR  (biz_date >= ? AND biz_date < ?   AND platform IN (?,?)))  and agentuserid1=?  group by toString(web_site_type)||user_id, web_site_type, currency, affiliate";
				String[] Arr = {"qa", "2023-06-16 12:00:00.0", "2023-06-19 12:00:00.0", "BTC", "SA", "SEXYBCRT", "VENUS", "FACHAI", "JDB", "JILI", "KINGMAKER", "POCKET", "PLAYSTAR", "PP", "SPADE", "OTHER", "2023-06-16 12:00:00.0", "2023-06-19 12:00:00.0", "CRICKET", "WOS", "ius"
				};
		String str1 = null;
		for (int i = 0; i < Arr.length; i++) {
			if (i == 0) {
				str1 = str.replaceFirst("\\?", "'" + Arr[i] + "'");
			} else {
				str1 = str1.replaceFirst("\\?", "'" + Arr[i] + "'");
			}
//			System.out.println(str1);
		}

//		String SQL="XXXX";
//		SQL.replace("X","q");
//		System.out.println(SQL);

//		String str = "Thi? websit? providing free tutorials";
		//Only Replace first 's' with '9'
//		String str1 = str.replaceFirst("\\?", "9");
		System.out.println(str1);
	}


}
