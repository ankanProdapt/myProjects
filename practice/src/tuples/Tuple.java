package tuples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Tuple<T> implements Collection<T> {
	
	protected List<T> tuple = new ArrayList<T>();
	protected final int SIZE;
	
	public Tuple(T[] objArr) {
		for(T obj: objArr) {
			tuple.add(obj);
		}
		SIZE = tuple.size();
	}

	@Override
	public boolean add(T arg0) {
		throw new UnsupportedOperationException("Adding is unsuported");
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		throw new UnsupportedOperationException("Adding is unsuported");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Removing is unsuported");
	}

	@Override
	public boolean contains(Object arg0) {
		return tuple.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		for(Object obj: arg0) {
			if(!tuple.contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return tuple.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return tuple.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException("Removing is unsuported");
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Removing is unsuported");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return SIZE;
	}

	@Override
	public Object[] toArray() {
		return tuple.toArray();
	}

	@Override
	public <T2> T2[] toArray(T2[] arg0) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return "Tuple: " + tuple.toString();
	}
}