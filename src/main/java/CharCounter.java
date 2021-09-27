import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CharCounter {
	public static void main(String[] args) {

		File file = new File("src/main/resources/input.txt");

		Map<Character, Integer> charMap = new HashMap<Character, Integer>();

		try {
			Scanner in = new Scanner(file);
			while (in.hasNext()) {
				String inputString = in.nextLine().toLowerCase();
				inputString = inputString.replace(" ", "");
				char[] strArray = inputString.toCharArray();
				for (char c : strArray) {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
