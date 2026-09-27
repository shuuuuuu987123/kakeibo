import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		

		//===家計簿===//
		System.out.println("1.追加");
		System.out.println("2.一覧");
		System.out.println("3.削除");
		System.out.println("4.月別集計");
		System.out.println("5.保存して終了");
		
		int choice = stdIn.nextInt();
		System.out.println("選んだ番号：" + choice);

		Entry e = new Entry("1900-01-01", "支出", 1000, "昼食");

		System.out.println(e);
	}
}
