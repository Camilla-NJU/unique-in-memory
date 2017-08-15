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
	 * 
	 * @param key
	 * @param value
	 * @return false ��ʾ��Ϣ������
	 * 			true ��ʾִ����Ϣ����
	 */
	public boolean put(K key, V value);
	
}
