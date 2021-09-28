import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class WordCounter {
	public static void main(String[] args) {

		String dir = "src/main/resources/IN";// будем искать в папке IN
		String ext = ".txt";// в этой папке будем искать файлы с расширением .txt

		findFiles(dir, ext);// вызываем метод поиска файлов с расширением .txt в папке IN

	}

	// Метод поиска файлов
	private static void findFiles(String dir, String ext) {
		File file = new File(dir);
		if (!file.exists())
			System.out.println(dir + " папка не существует");
		File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
		if (listFiles.length == 0) {
			System.out.println(dir + " не содержит файлов с расширением " + ext);
		} else {
			for (File f : listFiles) {
				System.out.println("Файл: " + dir + "/" + f.getName());
				System.out.println(countWords(f));

				writeToFile(f);
			}
		}
	}

	// Метод подсчета слов в файле
	private static int countWords(File f) {
		Scanner scanner = null;
		int counter = 0;
		try {
			scanner = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();
			counter += input.trim().split("\\s+").length;
		}
		return counter;
	}

	// Метод запись в файл результатов подсчета слов
	private static void writeToFile(File f) {
		File file = new File("src/main/resources/OUT/ResultOfCountingWords.txt");
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		printWriter.write("test row " + countWords(f));

		printWriter.write("the text");

		printWriter.close();
	}

	// Реализация интерфейса FileNameFilter
	public static class MyFileNameFilter implements FilenameFilter {

		private String ext;

		public MyFileNameFilter(String ext) {
			this.ext = ext.toLowerCase();
		}

		@Override
		public boolean accept(File dir, String name) {
			return name.toLowerCase().endsWith(ext);
		}
	}
}
