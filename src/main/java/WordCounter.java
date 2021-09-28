import java.io.*;
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
				// Вызываем метод запписи в файл
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

			while (scanner.hasNextLine()) {
				String input = scanner.nextLine();

				if (input.length() != 0) {
					counter += input.trim().split("\\P{L}+").length;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return counter;
	}

	// Метод запись в файл результатов подсчета слов
	private static void writeToFile(File f) {
		File file = new File("src/main/resources/OUT/ResultOfCountingWords.txt");
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(file, true);
			fileWriter.write(f.getName() + " : количество слов " + countWords(f) + "\n");
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
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
