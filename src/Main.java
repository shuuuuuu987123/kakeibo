import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<Entry> list = new ArrayList<>();
		Scanner stdIn = new Scanner(System.in);
		boolean running = true;

		try (Scanner fileIn = new Scanner(new File("kakeibo.csv"), "UTF-8")) {
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				if (line.isEmpty())
					continue;
				String[] parts = line.split(",");
				String d = parts[0];
				String c = parts[1];
				int a = Integer.parseInt(parts[2]);
				String n = parts[3];
				list.add(new Entry(d, c, a, n));
			}
		} catch (FileNotFoundException e) {
			//
		}
		//===家計簿===//
		while (running) {
			System.out.println("1.追加");
			System.out.println("2.一覧");
			System.out.println("3.削除");
			System.out.println("4.編集");
			System.out.println("5.月別集計");
			System.out.println("6.保存して終了");

			int choice = stdIn.nextInt();
			System.out.println("選んだ番号：" + choice);

			switch (choice) {
			case 1:
				System.out.print("日付：");
				String date = stdIn.next();
				System.out.print("区分：");
				String classification = stdIn.next();
				System.out.print("金額：");
				int amount = stdIn.nextInt();
				System.out.print("メモ：");
				String note = stdIn.next();
				list.add(new Entry(date, classification, amount, note));
				System.out.println("追加しました");
				break;
			case 2:
				if (list.isEmpty()) {
					System.out.println("データがありません");
				} else {
					for (Entry entry : list) {
						System.out.println(entry);
					}
					System.out.println("件数：" + list.size());
				}
				break;
			case 3:
				if (list.isEmpty()) {
					System.out.println("データがありません");
					break;
				}
				for (int i = 0; i < list.size(); i++) {
					System.out.println((i + 1) + "：" + list.get(i));
				}
				System.out.print("削除する番号：");
				int no = stdIn.nextInt();
				if (no >= 1 && no <= list.size()) {
					list.remove(no - 1);
					System.out.println("削除を選びました");
				} else {
					System.out.println("その番号はありません");
				}
				break;
			case 4:
				if (list.isEmpty()) {
					System.out.println("データがありません");
					break;
				}
				for (int i = 0; i < list.size(); i++) {
					System.out.println((i + 1) + ":" + list.get(i));
				}
				System.out.print("編集する番号：");
				int editNo = stdIn.nextInt();
				if (editNo >= 1 && editNo <= list.size()) {
					Entry target = list.get(editNo - 1);
					System.out.print("新しい日付（今：" + target.getDate() + "）：");
					String nd = stdIn.next();
					System.out.print("新しい区分（今：" + target.getClassification() + "）：");
					String nc = stdIn.next();
					System.out.print("新しい金額（今：" + target.getAmount() + "）：");
					int na = stdIn.nextInt();
					System.out.print("新しいメモ（今：" + target.getNote() + "）：");
					String nn = stdIn.next();
					list.set(editNo - 1, new Entry(nd, nc, na, nn));
					System.out.println("編集しました");
				} else {
					System.out.println("その番号はありません");
				}
				break;
			case 5:
				System.out.print("集計する年月：");
				String month = stdIn.next();
				int income = 0;
				int expense = 0;
				for (Entry entry : list) {
					if (entry.getDate().startsWith(month)) {
						if (entry.getClassification().equals("収入")) {
							income += entry.getAmount();
						} else {
							expense += entry.getAmount();
						}
					}
				}
				System.out.println("収入：" + income + "円");
				System.out.println("支出：" + expense + "円");
				System.out.println("差引：" + (income - expense) + "円");
				break;
			case 6:
				try (PrintWriter out = new PrintWriter("kakeibo.csv", "UTF-8")) {
					for (Entry entry : list) {
						out.println(entry.getDate() + ","
								+ entry.getClassification() + ","
								+ entry.getAmount() + ","
								+ entry.getNote());
					}
				} catch (IOException e) {
					System.out.println("保存に失敗しました：" + e.getMessage());
				}
				System.out.println("保存して終了します");
				running = false;
				break;
			default:
				System.out.println("1~6の番号を入力してください");
				break;
			}

		}
	}
}
