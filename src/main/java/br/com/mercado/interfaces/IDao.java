package br.com.mercado.interfaces;

public interface IDao<E> {
	public boolean save(E elemento);
	public E find(Integer id);
	public boolean delete(Integer id);
	public boolean update(E elemento);
}
