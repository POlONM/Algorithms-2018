package lesson1;

import java.util.ArrayList;

class SortTask {

    ArrayList<Double> sort(ArrayList<Double> array) {
        int i = 0;
        int goodPair = 0;
        while (true) {
            if (array.size() < 2) break;
            if (array.get(i) > array.get(i + 1)) {
                double min = array.get(i + 1);
                array.set(i + 1, array.get(i));
                array.set(i, min);
                goodPair = 0;
            } else goodPair++;
            i++;
            if (i == array.size() - 1) {
                i = 0;
            }
            if (goodPair == array.size() - 1) break;
        }
        return array;
    }
}
