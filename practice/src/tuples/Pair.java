package tuples;

import java.util.Collections;

public class Pair<T> extends Tuple<T> {
	
	public Pair(T[] objArr) {
		super(objArr);
		if(this.SIZE != 2) {
			tuple = null;
			throw new UnsupportedOperationException("Size of Pair must be 2!");
		}
	}
	
	public T first() {
		return tuple.get(0);
	}
	
	public T second() {
		return tuple.get(1);
	}
	
	public void flip() {
		Collections.reverse(tuple);
	}
	
	@Override
	public String toString() {
		return "Pair: " + tuple.toString();
	}
}
