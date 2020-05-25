package ua.testing.tree;

import java.util.List;

/**
 * <h1>Collections – Tree</h1>
 *
 * @author Svitlana Berezhna
 * @version 1.0
 * @since 2020-02-29
 */
public interface Tree<E> extends Iterable<E> {
    boolean add(E e);

    List<E> get();

    int size();

    SimpleTree.Leaf find(E e);
}
