package OpenCV_cam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SyncList<T> implements Iterable<T>, List<T>{
     private final List<T> col;
     public SyncList()
     {
         col =  Collections.synchronizedList(new ArrayList<>());
     }
     
     public T get(int index){
         return col.get(index);
     }

    @Override
    public Iterator<T> iterator() {
        return col.iterator();
    }
    
    @Override
    public int size() {
        return col.size();
    }

    @Override
    public boolean isEmpty() {
        return col.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return col.contains(o);
    }

    @Override
    public Object[] toArray() {
        return col.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return col.toArray(ts);
    }

    @Override
    public boolean add(T e) {
        return col.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return col.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        return col.containsAll(clctn);
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        return col.addAll(clctn);
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> clctn) {
        return col.addAll(i, clctn);
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        return col.removeAll(clctn);
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        return col.retainAll(clctn);
    }

    @Override
    public void clear() {
        col.clear();
    }

    @Override
    public T set(int i, T e) {
        return col.set(i, e);
    }

    @Override
    public void add(int i, T e) {
        col.add(i,e);
    }

    @Override
    public T remove(int i) {
        return col.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return col.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return col.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return col.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return col.listIterator(i);
    }

    @Override
    public List<T> subList(int i, int i1) {
        return col.subList(i, i1);
    }
}
