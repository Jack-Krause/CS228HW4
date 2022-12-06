package edu.iastate.cs228.hw4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CodeMap<String, Character> {
	private List<MapEntry> list = new LinkedList<MapEntry>();
	
	public CodeMap() {
		
	}
	
	protected class MapEntry {
		public String key;
		public Character value;
		
		public MapEntry(String key, Character value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public Character put(String key, Character value) {
		if (key==null) {
			throw new IllegalArgumentException();
		}
		for (MapEntry entry : list) {
			if (entry.key.equals(key)) {
				Character ret = entry.value;
				entry.value = value;
				return ret;
			}
		}
		list.add(new MapEntry(key, value));
		return null;
	}
	
	public Character get(String key) {
		for (MapEntry entry : list) {
			if (entry.key.equals(key)) {
				return entry.value;
			}
		}
		return null;
	}
	
	public Character remove(String key) {
		Iterator<MapEntry> iter = list.iterator();
		while (iter.hasNext()) {
			MapEntry entry = iter.next();
			if (entry.key.equals(key)) {
				Character ret = entry.value;
				iter.remove();
				return ret;
			}
		}
		return null;
	}
	
	public boolean containsKey(String key) {
		for (MapEntry entry : list) {
			if (entry.key.equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	public int size() {
		return list.size();
	}
	
	public Iterator<String> keyIterator() {
		return new KeyIterator();
	}
	private class KeyIterator implements Iterator<String> {
		private Iterator<MapEntry> iter = list.iterator();

		@Override
		public boolean hasNext() {
			return iter.hasNext();
		}

		@Override
		public String next() {
			return iter.next().key;
		}
		
		@Override
		public void remove() {
			iter.remove();
		}
		
		
	}
}
