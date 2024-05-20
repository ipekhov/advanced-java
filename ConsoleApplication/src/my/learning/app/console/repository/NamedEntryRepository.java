package my.learning.app.console.repository;

import java.util.HashSet;
import java.util.Set;

import my.learning.app.console.domain.Named;

public class NamedEntryRepository<T extends Named> {
	
	private final Set<T> entries = new HashSet<>();
	
	public T findByName(String name) {
		for(T entry : this.entries) {
			if(entry.getName().equals(name)) {
				return entry;
			}
		}
		return null;
	}
	
	public Set<T> getAll() {
		 return this.entries;
	}
	
	public void add(T entry) throws RepositoryAlreadyExistsException {
		if(this.entries.contains(entry)) {
			throw new RepositoryAlreadyExistsException();
		}
		this.entries.add(entry);
	}

}
