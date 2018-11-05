package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

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
     * Сложность - O(n*log(n)), Ресурсоемкость - O(n).
     */
    static public void sortTimes(String inputName, String outputName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        List<Double> seconds = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(":");
            double time = Double.valueOf(split[0]) * 3600
                    + Double.valueOf(split[1]) * 60
                    + Double.valueOf(split[2]);
            seconds.add(time);
            line = reader.readLine();
        }
        reader.close();
        Collections.sort(seconds);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        for (Double second : seconds) {
            double h, m, s, t;
            t = second;
            h = t / 3600;
            m = (t % 3600) / 60;
            s = t % 60;
            writer.write(String.format("%02d:%02d:%02d", (int) h, (int) m, (int) s));
            writer.newLine();
        }
        writer.close();
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
     * Ресурсоемкость - O(n), Сложность - O(n*log(n))
     */
    static public void sortTemperatures(String inputName, String outputName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        List<Double> temp = new ArrayList<>();
        String line = reader.readLine();
        Pattern reg = Pattern.compile("(-)?[0-9]+.[0-9]");
        while (line != null) {
            if (!reg.matcher(line).matches()) throw new IllegalArgumentException();
            temp.add(Double.valueOf(line));
            line = reader.readLine();
        }
        reader.close();
        Collections.sort(temp);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        for (double line2 : temp) {
            writer.write(String.valueOf(line2));
            writer.newLine();
        }
        writer.close();
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
     * Сложность - O(n*log(n)), Ресурсоемкость - O(n).
     */
    static public void sortSequence(String inputName, String outputName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        List<Integer> lines = new ArrayList<>();
        String line = reader.readLine();
        Pattern reg = Pattern.compile("[1-9][0-9]*");
        while (line != null) {
            if (!reg.matcher(line).matches()) throw new IllegalArgumentException();
            lines.add(Integer.valueOf(line));
            line = reader.readLine();
        }
        reader.close();
        List<Integer> result = new ArrayList<>(lines);
        Collections.sort(lines);
        int maxRepeat = 1;
        int repeat = 1;
        int maxRepNum = lines.get(0);
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).equals(lines.get(i - 1))) {
                repeat++;
                if (i == lines.size() - 1 && maxRepeat < repeat) {
                    maxRepeat = repeat;
                    maxRepNum = lines.get(i - 1);
                } else {
                    if (maxRepeat < repeat) {
                        maxRepeat = repeat;
                        maxRepNum = lines.get(i - 1);
                    }
                }
            } else repeat = 1;
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).equals(maxRepNum)) {
                writer.write(String.valueOf(result.get(i)));
                writer.newLine();
            }
        }
        for (int i = maxRepeat; i > 0; i--) {
            writer.write(String.valueOf(maxRepNum));
            writer.newLine();
        }
        writer.close();
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
