import java.util.ArrayList;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		ArrayList<Entry> list =new ArrayList<>();
		Scanner stdIn = new Scanner(System.in);

		//===家計簿===//
		System.out.println("1.追加");
		System.out.println("2.一覧");
		System.out.println("3.削除");
		System.out.println("4.月別集計");
		System.out.println("5.保存して終了");
		
		int choice = stdIn.nextInt();
		System.out.println("選んだ番号：" + choice);
		switch (choice) {
		case 1: System.out.println("追加を選びました");break;
		case 2: System.out.println("一覧を選びました");break;
		case 3: System.out.println("削除を選びました");break;
		case 4: System.out.println("月別集計を選びました");break;
		case 5: System.out.println("保存して終了を選びました");break;
		default: System.out.println("1~5の番号を入力してください");break;
		}
		
		Entry e = new Entry("1900-01-01", "支出", 1000, "昼食");

		System.out.println(e);
	}
}
