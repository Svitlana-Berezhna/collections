package ua.testing.arrayListTutorial.simpleList;

/**
 * <h1>Collections – ArrayListTutorial (SimpleList)</h1>
 *
 * @author Svitlana Berezhna
 * @version 1.0
 * @since 2020-02-29
 */
public interface Simple<E> extends Iterable<E> {
    boolean add(E e);

    void delete(int index);

    E get(int index);

    int size();

    void update(int index, E e);
}
