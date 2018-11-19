package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     * Сложность - O(h), Ресурсоемкость - O(n)
     */
    @Override
    public boolean remove(Object o) {
        T element = (T) o;
        Node<T> result = spRemove(root, element);
        size--;
        return (result != null);
    }

    private Node<T> spRemove(Node<T> root, T element) throws NullPointerException, IllegalArgumentException {
        Node<T> node = root;
        int compare = element.compareTo(node.value);
        if (compare < 0) {
            node.left = spRemove(node.left, element);
        } else if (compare > 0) {
            node.right = spRemove(node.right, element);
        } else if (node.right != null) {
            node.value = leftNode(node.right).value;
            node.right = spRemove(node.right, node.value);
        } else {
            if (node.left != null) {
                node.value = rightNode(node.left).value;
                node.left = spRemove(node.left, node.value);
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private Node<T> leftNode(Node<T> node) {
        if (node.left == null) return node;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<T> rightNode(Node<T> node) {
        if (node.right == null) return node;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;
        private Stack<Node<T>> s = new Stack<>();
        private Node<T> r = null;

        private BinaryTreeIterator(Node<T> node) {
            Node<T> n = node;
            r = node;
            while (n != null) {
                s.push(n);
                n = n.left;
            }
        }

        /**
         * Поиск следующего элемента
         * Средняя
         * Сложность - O(h), Ресурсоемкость - O(n).
         */
        private Node<T> findNext() {
            Node<T> n = s.pop();
            Node<T> r = n;
            if (n.right != null) {
                n = n.right;
                while (n != null) {
                    s.push(n);
                    n = n.left;
                }
            }
            return r;
        }

        @Override
        public boolean hasNext() {
            return !s.isEmpty();
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         * Сложность - O(h), Ресурсоемкость - O(n).
         */
        @Override
        public void remove() {
            if (current == null) return;
            spRemove(root, current.value);
            size--;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator(root);
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
