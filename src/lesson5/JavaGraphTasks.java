package lesson5;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     * <p>
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ:
     * <p>
     * G    H
     * |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     * <p>
     * Дан граф без циклов (получатель), например
     * <p>
     * G -- H -- J
     * |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     * <p>
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     * <p>
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     * <p>
     * В данном случае ответ (A, E, F, D, G, J)
     * <p>
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     * <p>
     * Задача решена на основе алгоритма в статье Википедии "Задача о независимом множестве".
     * Сложность- O(V + E), Ресурсоемкость- O(V).
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        Set<Graph.Vertex> grandchild = new HashSet<>();
        Set<Graph.Vertex> child = new HashSet<>();
        Graph.Vertex parent = graph.getVertices().iterator().next();
        grandchild.add(parent);
        addVertices(parent, graph, grandchild, child);
        if (grandchild.size() >= child.size()) return grandchild;
        else return child;
    }

    private static void addVertices(Graph.Vertex vertex, Graph graph,
                                    Set<Graph.Vertex> grandchildren, Set<Graph.Vertex> child) {
        for (Graph.Vertex neighbour : graph.getNeighbors(vertex)) {
            if (!grandchildren.contains(neighbour) && !child.contains(neighbour)) {
                if (grandchildren.contains(vertex)) child.add(neighbour);
                else grandchildren.add(neighbour);
                addVertices(neighbour, graph, grandchildren, child);
            }
        }
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     * <p>
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     *
     * Сложность- , Ресурсоемкость- O(V).
     */
    public static Path longestSimplePath(Graph graph) {
        List<Graph.Vertex> way = new ArrayList<>();
        List<Graph.Vertex> res = new LinkedList<>();
        Graph.Vertex start = graph.getVertices().iterator().next();
        way.add(start);
        searchWay(way, res, graph, start);
        Path path = new Path(res.get(0));
        for (int i = 1; i < res.size(); i++) {
            path = new Path(path, graph, res.get(i));
        }
        return path;
    }

    private static void searchWay(List<Graph.Vertex> way, List<Graph.Vertex> res, Graph graph, Graph.Vertex vertex) {
        for (Graph.Vertex neighbour : graph.getNeighbors(vertex)) {
            if (!way.contains(neighbour)) {
                way.add(neighbour);
                if (way.size() != graph.getVertices().size()) {
                    searchWay(way, res, graph, neighbour);
                }
            }
        }
        if (way.size() <= graph.getVertices().size()) {
            if (way.size() > res.size()) {
                res.clear();
                res.addAll(way);
            }
            way.remove(vertex);
        }
    }
}
