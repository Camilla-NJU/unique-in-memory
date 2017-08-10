package com.honey.storage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class MyCache<K,V> implements BasicCache<K,V>{
	/*
	 * ����
	 */
	private Cache<K,V> cache;
	/*
	 * �ɲ���д������߳���
	 */
	private int concurrencyLevel = 20;
	/*
	 * ����ʱ��͵�λ
	 */
	private long expire = 60;
	private TimeUnit timeUnit = TimeUnit.SECONDS;
	/*
	 * ���û����С���ޣ�������ֵ�Ϳ�ʼLRU�滻
	 */
	private int size = 1000;
	
	public MyCache() {
		init();
	}
	
	//@postConstruct
	public void init() {
		cache = CacheBuilder.newBuilder().concurrencyLevel(concurrencyLevel).expireAfterAccess(expire, timeUnit).maximumSize(size).build();
	}
	
	private V getValue(K key) throws ExecutionException {
		V result = this.cache.get(key, new Callable<V>() {

			public V call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		return result;
	}

	public V get(K key) {
		V result = null;
		try {
			result = getValue(key);
		} catch (ExecutionException e) {
			//guava��������null�����ﲶ���쳣�������κδ�������null
		}
		
		return result;
	}

	public void put(K key, V value) {
		this.cache.put(key, value);
	}
	
	public int getConcurrencyLevel() {
		return concurrencyLevel;
	}

	public long getExpire() {
		return expire;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public int getSize() {
		return size;
	}

	public void setConcurrencyLevel(int concurrencyLevel) {
		this.concurrencyLevel = concurrencyLevel;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
