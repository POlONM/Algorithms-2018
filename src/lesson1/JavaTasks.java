package lesson1;

import kotlin.NotImplementedError;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     * <p>
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputName), StandardCharsets.UTF_8);
        ArrayList<Double> seconds = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(":");
            double time = Double.valueOf(split[0]) * 3600
                    + Double.valueOf(split[1]) * 60
                    + Double.valueOf(split[2]);
            seconds.add(time);
        }
        SortTask st = new SortTask();
        st.sort(seconds);
        for (Double second : seconds) {
            double h, m, s, t;
            t = second;
            h = t / 3600;
            m = (t % 3600) / 60;
            s = t % 60;
            result.add((int)h + ":" + (int)m + ":" + (int)s + "\n");
        }
        Files.write(Paths.get(outputName), result, StandardOpenOption.CREATE);
    }


    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputName), StandardCharsets.UTF_8);
        ArrayList<Double> plus = new ArrayList<>();
        ArrayList<Double> minus = new ArrayList<>();
        for (String line : lines) {
            SortTask st = new SortTask();
            if (Double.valueOf(line) >= 0) {
                plus.add(Double.valueOf(line));
                st.sort(plus);
            } else {
                minus.add(Double.valueOf(line));
                st.sort(minus);
            }
        }
        ArrayList<String> result = new ArrayList<>();
        minus.addAll(plus);
        for (Double num : minus) {
            result.add(num.toString() + "\n");
        }
        Files.write(Paths.get(outputName), result, StandardOpenOption.CREATE);
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputName), StandardCharsets.UTF_8);
        int c = 0, min = 0, num = 0;
        HashMap<Integer, Integer> pairs = new HashMap<>();
        for (String line : lines) {
            int key = Integer.valueOf(line);
            if (key > min) min = key;
            if (pairs.containsKey(line)) {
                pairs.put(Integer.valueOf(line), pairs.get(line) +1);
            } else {
                pairs.put(Integer.valueOf(line), 1);
            }
        }
        for (Integer key : pairs.keySet()) {
            if (pairs.get(key) > c || key < num && pairs.get(key) == c) {
                c = pairs.get(key);
                num = key;
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            if (Integer.valueOf(line) != num){
                result.add(line + "\n");
            }
        }
        for (int i = 0; i < c; i++){
            result.add(String.valueOf(num) + "\n");
        }
        Files.write(Paths.get(outputName), result, StandardOpenOption.CREATE);
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
