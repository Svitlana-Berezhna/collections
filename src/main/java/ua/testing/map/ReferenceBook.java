package ua.testing.map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <h1>Collections – Map</h1>
 *
 * @author Svitlana Berezhna
 * @version 1.0
 * @since 2020-02-29
 */
public class ReferenceBook<K, V> implements Book<K, V> {
    public static void main(String[] args) {
        ReferenceBook<String, String> strings = new ReferenceBook<>();
//        strings.insert("a", "b");

/*        System.out.println(strings.get("a"));
        strings.insert("a", "c");
        System.out.println(strings.get("a"));*/

//        strings.insert("d", "c");
//        System.out.println(strings.size());

       /* System.out.println(strings.get("a"));
        strings.delete("a");
        System.out.println(strings.get("a"));*/

        strings.insert("1", "1");
        strings.insert("2", "2");
        strings.insert("3", "3");
        strings.insert("4", "4");
        strings.insert("5", "5");
        strings.insert("6", "6");
        strings.insert("7", "7");
        strings.insert("8", "8");
        strings.insert("9", "9");
        strings.insert("10", "10");
        strings.insert("11", "11");
        strings.insert("12", "12");
        strings.insert("13", "13");
        strings.insert("14", "14");
//        strings.insert("15", "15");
        strings.insert("15", "hello");
        strings.insert("16", "16");
        strings.insert("17", "17");
        System.out.println(strings.size());
//        System.out.println(strings.get("17"));
        System.out.println(strings.get("15"));
        System.out.println(strings.hashTable.length);

        for (String s : strings) {
            System.out.println(s);
        }
    }

    private Node<K, V>[] hashTable;
    private int size = 0;
    private float threshold;

    public ReferenceBook() {
        hashTable = new Node[16];
        threshold = hashTable.length * 0.75f;
    }

    @Override
    public boolean insert(final K key, final V value) {
        if (size + 1 >= threshold) {
            threshold *= 2;
            arrayDoubling();
        }

        Node<K, V> newNode = new Node<>(key, value);
//        int index = newNode.hash();
        int index = hash(key);

        if (hashTable[index] == null) {
            return simpleAdd(index, newNode);
        }

        List<Node<K, V>> nodeList = hashTable[index].getNodes();

        for (Node<K, V> node : nodeList) {
            if (keyExistButValueNew(node, newNode, value) ||
                    collisionProcessing(node, newNode, nodeList)
            ) {
                return true;
            }
        }
        return false;
    }

    private boolean simpleAdd(int index, Node<K, V> newNode) {
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNodes().add(newNode);
        size++;
        return true;
    }

    private boolean keyExistButValueNew(
            final Node<K, V> nodeFromList,
            final Node<K, V> newNode,
            final V value) {
        if (newNode.getKey().equals(nodeFromList.getKey()) &&
                !newNode.getValue().equals(nodeFromList.getValue())
        ) {
            nodeFromList.setValue(value);
            return true;
        }
        return false;
    }

    private boolean collisionProcessing(
            final Node<K, V> nodeFromList,
            final Node<K, V> newNode,
            final List<Node<K, V>> nodes) {
        if (newNode.hashCode() == nodeFromList.hashCode() &&
                !Objects.equals(newNode.key, nodeFromList.key) &&
                !Objects.equals(newNode.value, nodeFromList.value)
        ) {
            nodes.add(newNode);
            size++;
            return true;
        }
        return false;
    }

    private void arrayDoubling() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length * 2];
        size = 0;
        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> n : node.getNodes()) {
                    insert(n.key, n.value);
                }
            }
        }
    }

    @Override
    public boolean delete(final K key) {
        int index = hash(key);
        if (hashTable[index] == null)
            return false;

        if (hashTable[index].getNodes().size() == 1) {
//            hashTable[index].getNodes().remove(0);
            hashTable[index] = null;
            return true;
        }

        List<Node<K, V>> nodeList = hashTable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                nodeList.remove(node);
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(final K key) {
        int index = hash(key);
        if (index < hashTable.length &&
                hashTable[index] != null) {
            if (hashTable[index].getNodes().size() == 1) {
                return hashTable[index].getNodes().get(0).getValue();
            }
            List<Node<K, V>> list = hashTable[index].getNodes();
            for (Node<K, V> node : list) {
                if (key.equals(node.getKey())) {
                    return node.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private int hash(final K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            //            int counterArray = 0;
            int counterArray = -1;
            int valueCounter = 0;
            Iterator<Node<K, V>> subIterator = null;

            @Override
            public boolean hasNext() {
                if (valueCounter == size)
                    return false;
                if (subIterator == null || !subIterator.hasNext()) {
                    if (moveToNextCell()) {
                        subIterator = hashTable[counterArray].getNodes().iterator();
                    } else {
                        return false;
                    }
                }
                return subIterator.hasNext();
            }

            private boolean moveToNextCell() {
                counterArray++;
//                while (hashTable[counterArray] == null) {
                while (counterArray < hashTable.length && hashTable[counterArray] == null) {
                    counterArray++;
                }
//                return hashTable[counterArray] != null;
                return counterArray < hashTable.length && hashTable[counterArray] != null;
            }

            @Override
            public V next() {
                valueCounter++;
                return subIterator.next().getValue();
            }
        };
    }

    private class Node<K, V> {
        private List<Node<K, V>> nodes;
        private int hash;
        private K key;
        private V value;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<Node<K, V>>();
        }

        private List<Node<K, V>> getNodes() {
            return nodes;
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj instanceof Node) {
                Node<K, V> node = (Node) obj;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }
            return false;
        }
    }
}
