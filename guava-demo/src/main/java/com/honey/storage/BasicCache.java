package com.honey.storage;

/**
 * 
 * @author H249365
 * ���ػ���ӿ�
 * @param <K>
 * @param <V>
 */
public interface BasicCache<K,V> {
	/**
	 * ��ȡkey��Ӧ�ļ�¼
	 * @param key
	 * @return V value
	 */
	public V get(K key);
	
	/**
	 * �򻺴�������¼�¼
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);
}
