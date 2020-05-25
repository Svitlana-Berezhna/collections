package ua.testing.linkedList.myLinkedList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * <h1>Collections – LinkedList (MyLinkedList)</h1>
 *
 * @author Svitlana Berezhna
 * @version 1.0
 * @since 2020-02-29
 */
public class LinkedContainer<E> implements Linked<E>, Iterable<E>, DescendingIterator<E> {
    public static void main(String[] args) {
        LinkedContainer<String> stringLinked = new LinkedContainer<>();

/*        stringLinked.addLast("abc");
        stringLinked.addLast("de");*/

//        stringLinked.addFirst("abc");
//        stringLinked.addFirst("de");

//        System.out.println(stringLinked.size());
//        System.out.println(stringLinked.getElementByIndex(0));
//        System.out.println(stringLinked.getElementByIndex(1));
//        System.out.println(stringLinked.getElementByIndex(5));

/*        stringLinked.addFirst("a");
        stringLinked.addFirst("ab");
        stringLinked.addFirst("c");
        stringLinked.addFirst("abc");*/

        stringLinked.addLast("a");
        stringLinked.addLast("ab");
        stringLinked.addLast("c");
        stringLinked.addLast("abc");

/*        for (String s : stringLinked) {
            System.out.println(s);
        }*/

        Iterator iterator = stringLinked.descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private Node<E> fstNode;
    private Node<E> lstNode;
    private int size = 0;

//     null <- prevElement [fstNode( e = null)] nextElement -> <- prevElement [lstNode( e = null)] nextElement -> null
//     null <- prevElement [fstNode( e = null)] nextElement -> <- prevElement [currNode( e = val)] nextElement ->
//     <- prevElement [lstNode( e = null)] nextElement -> null

    public LinkedContainer() {
        lstNode = new Node<E>(null, fstNode, null);
        fstNode = new Node<E>(null, null, lstNode);
    }

    @Override
    public void addLast(E e) {
        Node<E> prev = lstNode;
        prev.setCurrentElement(e);
        lstNode = new Node<E>(null, prev, null);
        prev.setNextElement(lstNode);
        size++;
    }

    @Override
    public void addFirst(E e) {
        Node<E> next = fstNode;
        next.setCurrentElement(e);
        fstNode = new Node<E>(null, null, next);
        next.setPrevElement(fstNode);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getElementByIndex(int counter) {
        Node<E> target = fstNode.getNextElement();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return getElementByIndex(counter++);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            int counter = size - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public E next() {
                return getElementByIndex(counter--);
            }
        };
    }

    private class Node<E> {
        private E currentElement;
        private Node<E> prevElement;
        private Node<E> nextElement;

        public Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }

        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }
    }
}
