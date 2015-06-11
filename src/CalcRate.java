import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CalcRate {
	private static String outputstr, KEY;
	private static ArrayList<String> arylist = new ArrayList<String>();
	private static List<String> CheckaryList = new ArrayList<String>();
	static int Totalday = 0, NHI = 0, ownExpense_1900 = 0, ownExpense_3500 = 0,
			ownExpense_000D = 0, CUD = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Parmaters parmater = new Parmaters();
		ReadFromClassFile(parmater.ClassC_Class_Detail);
		ReadID(parmater.ClassC_Class_Detail);
		
		  HashSet<String> set = new HashSet<String>(arylist); arylist.clear();
		  arylist.addAll(set);
		
		ReWriteForCalcRate();
	}

	private static void ReWriteForCalcRate() throws Exception {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(Parmaters.RoomRatefile);
		for (int i = 0; i < arylist.size(); i += 1) {
			for (int j = 0; j < CheckaryList.size(); j += 1) {
				String[] afterSplitRow = CheckaryList.get(j).split("\t");
				 //System.out.println(arylist.get(i));
				if ((arylist.get(i)).equals(afterSplitRow[0])) // Same Index
				{
					// System.out.println(KEY + "\t" + afterSplitRow[0]);
					int ownExpense = Integer.valueOf(afterSplitRow[4].trim());
					int NHIExpense = Integer.valueOf(afterSplitRow[3].trim());

					if (NHIExpense == 0) {
						outputstr += afterSplitRow[2] + "|" + afterSplitRow[6]
								+ "\t";
						ownExpense_000D += Integer.valueOf(afterSplitRow[6]);
					}
					if (ownExpense == 0 && NHIExpense == 537) {
						outputstr += afterSplitRow[2] + "|" + afterSplitRow[6]
								+ "\t";
						NHI += Integer.valueOf(afterSplitRow[6]);
					}

					if (ownExpense == 0 && NHIExpense == 2560) {
						outputstr += afterSplitRow[2] + "|" + afterSplitRow[6]
								+ "\t";
						CUD += Integer.valueOf(afterSplitRow[6]);
					}

					if (ownExpense >= 1700 && ownExpense < 3500
							&& NHIExpense >= 537) {
						outputstr += afterSplitRow[2] + "|" + afterSplitRow[6]
								+ "\t";
						ownExpense_1900 += Integer.valueOf(afterSplitRow[6]);
					}

					if (ownExpense >= 3500 && NHIExpense >= 537) {
						outputstr += afterSplitRow[2] + "|" + afterSplitRow[6]
								+ "\t";
						ownExpense_3500 += Integer.valueOf(afterSplitRow[6]);
					}

				}
				
			}
			Totalday = NHI + ownExpense_1900 + ownExpense_3500 + CUD
					+ ownExpense_000D;

			System.out.println(arylist.get(i) + "\t" + NHI + "\t" + ownExpense_1900 + "\t"
					+ ownExpense_3500 + "\t" + CUD + "\t" + ownExpense_000D
					+ "\t" + Totalday + "\t" + outputstr);
			fw.write(arylist.get(i) + "\t" + NHI + "\t" + ownExpense_1900 + "\t"
					+ ownExpense_3500 + "\t" + CUD + "\t" + ownExpense_000D
					+ "\t" + Totalday + "\t" + outputstr + "\n");

			Totalday = 0;
			NHI = 0;
			ownExpense_1900 = 0;
			ownExpense_3500 = 0;
			ownExpense_000D = 0;
			CUD = 0;
			outputstr = "";
			
		}
		fw.close();
	}

	private static void initKey(String[] afterSplitRow) {
		// TODO Auto-generated method stub
		KEY = afterSplitRow[0];
	}

	private static void ReadID(String indexfile) throws IOException {
		// TODO Auto-generated method stub
		String line = null;
		String[] line2;
		FileReader fr = new FileReader(indexfile);
		BufferedReader br = new BufferedReader((fr));
		while ((line = br.readLine()) != null) {
			line2 = line.split("\t");
			 //System.out.println(line2[0]);
			 arylist.add(line2[0]);

		}
		br.close();

	}

	private static void ReadFromClassFile(String indexfile) throws IOException {
		// TODO Auto-generated method stub
		String line = null;
		FileReader fr = new FileReader(indexfile);
		BufferedReader br = new BufferedReader((fr));
		while ((line = br.readLine()) != null) {

			// System.out.println("method2   "+line);
			// arylist.add(ID[0]);
			CheckaryList.add(line);
		}
		br.close();

	}

}
