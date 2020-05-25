package ua.testing.linkedList.myLinkedList;

/**
 * <h1>Collections – LinkedList (MyLinkedList)</h1>
 *
 * @author Svitlana Berezhna
 * @version 1.0
 * @since 2020-02-29
 */
public interface Linked<E> {
    void addLast(E e);

    void addFirst(E e);

    int size();

    E getElementByIndex(int counter);
}
