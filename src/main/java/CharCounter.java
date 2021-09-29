import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CharCounter {
	public static void main(String[] args) {
		String separator = File.separator;
		String path = "src" + separator + "main" + separator + "resources"
				+ separator + "input.txt";
		File file = new File(path);

		Map<Character, Integer> charMap = new HashMap<>();

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNext()) {
				//Переводим в нижний регистр и оставляем только буквы
				String inputString = scanner.nextLine().toLowerCase().replaceAll("[^a-zа-я]", "");

				char[] charArray = inputString.toCharArray();

				for (char c : charArray) {
					if (charMap.containsKey(c)) {
						charMap.put(c, charMap.get(c) + 1); //Если буква уже есть то добавляем 1
					} else {
						charMap.put(c, 1); //Если буквы еще нет, добавляем букву в map со значением 1
					}
				}
			}
			//Сортируем по убыванию
			Map<Character, Integer> sortedCharMap = charMap.entrySet().stream()
					.sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
							(e1, e2) -> e1, LinkedHashMap::new));

			System.out.println(sortedCharMap);

			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
