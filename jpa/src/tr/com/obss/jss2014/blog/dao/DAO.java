package tr.com.obss.jss2014.blog.dao;

public interface DAO<K, E> {
	
	public void persist(E entity);
	public E findById(K key);

}
