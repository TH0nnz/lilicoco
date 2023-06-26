package com.tomjheng.lilicoco.TEST_OVERRIDE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SORT {
	public static void main(String[] args) {

		List<String> stringList = new ArrayList<>();
		stringList.add("ZZZZ");
		List<SORT.Data> dataList = new ArrayList<>();
		dataList.add(new SORT.Data(stringList, "USD"));
		stringList.add("ZZZYY");
		dataList.add(new SORT.Data(stringList, "NTD"));
		stringList.add("YY");
		dataList.add(new SORT.Data(stringList, "NAD"));
		stringList.add("fsdgY");
		dataList.add(new SORT.Data(stringList, "NAA"));
		stringList.add("aaaa");
		dataList.add(new SORT.Data(stringList, "JPD"));
		stringList.add("fgrt");
		dataList.add(new SORT.Data(stringList, "RMB"));
		stringList.add("gfhert");
		dataList.add(new SORT.Data(stringList, "RB"));
		stringList.add("hert");
		dataList.add(new SORT.Data(stringList, "台幣"));
		stringList.add("fdghi");
		dataList.add(new SORT.Data(stringList, "台新"));
		stringList.add("qruo");
		dataList.add(new SORT.Data(stringList, "美金"));
		stringList.add("nnhy");
		dataList.add(new SORT.Data(stringList, "越南盾"));
		stringList.add("iihgg");
		dataList.add(new SORT.Data(stringList, "12"));
		stringList.add("ppuf");
		dataList.add(new SORT.Data(stringList, "16"));
		stringList.add("eetuo");
		dataList.add(new SORT.Data(stringList, "02"));
		stringList.add("eehhf");
		//use Collator
//		Comparator<Object> com = Collator.getInstance(Locale.ENGLISH);
//
//		dataList.sort((o1, o2) -> com.compare(o1.getCurrency(), o2.getCurrency()));
//		System.out.println(dataListS);

		//不用帶語言

		List<Data> dataListS = dataList.stream().sorted(Comparator.comparing((Data::getCurrency))).collect(Collectors.toList());
		System.out.println(dataListS);
	}

	public static class Data {

		/**
		 * 貨幣種名稱
		 */
		private String currency;

		private List<String> stringList;

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public List<String> getStringList() {
			return stringList;
		}

		public void setStringList(List<String> stringList) {
			this.stringList = stringList;
		}


		public Data(List<String> stringList, String currency) {
			this.stringList = stringList;
			this.currency = currency;
		}

	}
}
