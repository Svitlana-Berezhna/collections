package ua.testing.arrayListTutorial.simpleList;

import java.util.Iterator;

/**
 * <h1>Collections – ArrayListTutorial (SimpleList)</h1>
 *
 * @author Svitlana Berezhna
 * @version 1.0
 * @since 2020-02-29
 */
public class ArrayIterator<E> implements Iterator<E> {
    private int index = 0;
    private E[] values;

    public ArrayIterator(E[] value) {
        this.values = value;
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public E next() {
        return values[index++];
    }
}
