
public class Entry {
	private String date;//日付
	private String classification;//区分
	private int amount;//金額
	private String note;//メモ

	//===コンストラクタ===//
	public Entry(String date, String classfication, int amount, String note) {
		this.date = date;//日付
		this.classification = classfication;//区分
		this.amount = amount;//金額
		this.note = note;//メモ
	}
@Override
	public String toString() {
		return String.format("%s %s %d %s", date, classification, amount, note);
	}

}
