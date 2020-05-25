package ua.testing.map;

/**
 * <h1>Collections – Map</h1>
 *
 * @author Svitlana Berezhna
 * @version 1.0
 * @since 2020-02-29
 */
public interface Book<K, V> extends Iterable<V> {
    boolean insert(K key, V value);

    boolean delete(K key);

    V get(K key);

    int size();
}
