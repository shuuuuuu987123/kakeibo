import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Kakeibo kakeibo = new Kakeibo();
		Scanner stdIn = new Scanner(System.in);
		boolean running = true;

		kakeibo.load();
		//===家計簿===//
		while (running) {
			System.out.println("1.追加");
			System.out.println("2.一覧");
			System.out.println("3.削除");
			System.out.println("4.編集");
			System.out.println("5.月別集計");
			System.out.println("6.保存して終了");

			int choice = readInt(stdIn);
			System.out.println("選んだ番号：" + choice);

			switch (choice) {
			case 1:
				System.out.print("日付：");
				String date = readLine(stdIn);
				System.out.print("区分：");
				String classification = readLine(stdIn);
				System.out.print("金額：");
				int amount = readInt(stdIn);
				System.out.print("メモ：");
				String note = readLine(stdIn);
				kakeibo.add(new Entry(date, classification, amount, note));
				System.out.println("追加しました");
				break;
			case 2:
				if (kakeibo.isEmpty()) {
					System.out.println("データがありません");
				} else {
					for (int i = 0; i < kakeibo.size(); i++) {
						System.out.println(kakeibo.get(i));
					}
					System.out.println("件数：" + kakeibo.size());
				}
				break;
			case 3:
				if (kakeibo.isEmpty()) {
					System.out.println("データがありません");
					break;
				}
				for (int i = 0; i < kakeibo.size(); i++) {
					System.out.println((i + 1) + "：" + kakeibo.get(i));
				}
				System.out.print("削除する番号：");
				int no = readInt(stdIn);
				if (no >= 1 && no <= kakeibo.size()) {
					kakeibo.remove(no - 1);
					System.out.println("削除を選びました");
				} else {
					System.out.println("その番号はありません");
				}
				break;
			case 4:
				if (kakeibo.isEmpty()) {
					System.out.println("データがありません");
					break;
				}
				for (int i = 0; i < kakeibo.size(); i++) {
					System.out.println((i + 1) + ":" + kakeibo.get(i));
				}
				System.out.print("編集する番号：");
				int editNo = readInt(stdIn);
				if (editNo >= 1 && editNo <= kakeibo.size()) {
					Entry target = kakeibo.get(editNo - 1);
					System.out.print("新しい日付（今：" + target.getDate() + "）：");
					String nd = readLine(stdIn);
					System.out.print("新しい区分（今：" + target.getClassification() + "）：");
					String nc = readLine(stdIn);
					System.out.print("新しい金額（今：" + target.getAmount() + "）：");
					int na = readInt(stdIn);
					System.out.print("新しいメモ（今：" + target.getNote() + "）：");
					String nn = readLine(stdIn);
					kakeibo.set(editNo - 1, new Entry(nd, nc, na, nn));
					System.out.println("編集しました");
				} else {
					System.out.println("その番号はありません");
				}
				break;
			case 5:
				System.out.print("集計する年月：");
				String month = readLine(stdIn);
				int[] sum = kakeibo.summary(month);
				System.out.println("収入：" + sum[0] + "円");
				System.out.println("支出：" + sum[1] + "円");
				System.out.println("差引：" + (sum[0] - sum[1]) + "円");
				break;
			case 6:
				try {
					kakeibo.save();
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
	static int readInt(Scanner sc) {
	    while (true) {
	        String line = sc.nextLine().trim();
	        if (line.isEmpty())
	            continue;
	        try {
	            return Integer.parseInt(line);
	        } catch (NumberFormatException e) {
	            System.out.print("数字で入力してください：");
	        }
	    }
	}

	static String readLine(Scanner sc) {
	    while (true) {
	        String line = sc.nextLine().trim();
	        if (!line.isEmpty())
	            return line;
	        System.out.print("入力してください：");
	    }
	}
}
