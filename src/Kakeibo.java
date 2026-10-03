import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Kakeibo {
    private ArrayList<Entry> list = new ArrayList<>();

    public void add(Entry e) {
        list.add(e);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public Entry get(int index) {
        return list.get(index);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void set(int index, Entry e) {
        list.set(index, e);
    }

    public void save() throws IOException {
        try (PrintWriter out = new PrintWriter("kakeibo.csv", "UTF-8")) {
            for (Entry entry : list) {
                out.println(entry.getDate() + ","
                        + entry.getClassification() + ","
                        + entry.getAmount() + ","
                        + entry.getNote());
            }
        }
    }

    public void load() {
        try (Scanner fileIn = new Scanner(new File("kakeibo.csv"), "UTF-8")) {
            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                if (line.isEmpty())
                    continue;
                String[] parts = line.split(",");
                list.add(new Entry(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
            }
        } catch (FileNotFoundException e) {
            //
        }
    }

    // 指定した年月の {収入, 支出} を返す
    public int[] summary(String month) {
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
        return new int[] { income, expense };
    }
}
