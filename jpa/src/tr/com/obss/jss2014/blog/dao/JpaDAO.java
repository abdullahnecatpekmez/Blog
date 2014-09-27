package tr.com.obss.jss2014.blog.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class JpaDAO<K, E> implements DAO<K, E> {
	
	protected Class<E> entityClass;
	
	@SuppressWarnings("unchecked")
	public JpaDAO(){
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}
	
	@PersistenceContext(unitName = "pUnit")
	protected EntityManager em;

	@Override
	public void persist(E entity) {
		em.persist(entity);
	}

	@Override
	public E findById(K key) {
		return em.find(entityClass, key);
	}

}
