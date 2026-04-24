package io.github.defective4.linux.jmpris.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class DBusArray extends DBusObject implements List<DBusObject> {
    private final List<DBusObject> items;

    public DBusArray() {
        this(Collections.emptyList());
    }

    public DBusArray(List<DBusObject> items) {
        this.items = List.copyOf(items);
    }

    @Override
    public boolean add(DBusObject e) {
        return items.add(e);
    }

    @Override
    public void add(int index, DBusObject element) {
        items.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends DBusObject> c) {
        return items.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends DBusObject> c) {
        return items.addAll(index, c);
    }

    @Override
    public void addFirst(DBusObject e) {
        items.addFirst(e);
    }

    @Override
    public void addLast(DBusObject e) {
        items.addLast(e);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean equals(Object o) {
        return items.equals(o);
    }

    @Override
    public void forEach(Consumer<? super DBusObject> action) {
        items.forEach(action);
    }

    @Override
    public DBusObject get(int index) {
        return items.get(index);
    }

    @Override
    public DBusObject getFirst() {
        return items.getFirst();
    }

    public List<DBusObject> getItems() {
        return items;
    }

    @Override
    public DBusObject getLast() {
        return items.getLast();
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public int indexOf(Object o) {
        return items.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Iterator<DBusObject> iterator() {
        return items.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return items.lastIndexOf(o);
    }

    @Override
    public ListIterator<DBusObject> listIterator() {
        return items.listIterator();
    }

    @Override
    public ListIterator<DBusObject> listIterator(int index) {
        return items.listIterator(index);
    }

    @Override
    public Stream<DBusObject> parallelStream() {
        return items.parallelStream();
    }

    @Override
    public DBusObject remove(int index) {
        return items.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public DBusObject removeFirst() {
        return items.removeFirst();
    }

    @Override
    public boolean removeIf(Predicate<? super DBusObject> filter) {
        return items.removeIf(filter);
    }

    @Override
    public DBusObject removeLast() {
        return items.removeLast();
    }

    @Override
    public void replaceAll(UnaryOperator<DBusObject> operator) {
        items.replaceAll(operator);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public List<DBusObject> reversed() {
        return items.reversed();
    }

    @Override
    public DBusObject set(int index, DBusObject element) {
        return items.set(index, element);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void sort(Comparator<? super DBusObject> c) {
        items.sort(c);
    }

    @Override
    public Spliterator<DBusObject> spliterator() {
        return items.spliterator();
    }

    @Override
    public Stream<DBusObject> stream() {
        return items.stream();
    }

    @Override
    public List<DBusObject> subList(int fromIndex, int toIndex) {
        return items.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return items.toArray(generator);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return items.toArray(a);
    }

    @Override
    public String toString() {
        return "DBusArray [items=" + items + "]";
    }

}
