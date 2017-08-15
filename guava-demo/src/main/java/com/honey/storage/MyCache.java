package com.honey.storage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public abstract class MyCache<K,V>{
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
	private long expire = 60l;
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
	
	/**
	 * 
	 * @param key
	 * @return ������Ϊnull���߼���������guava�쳣
	 * @throws ExecutionException
	 */
	protected abstract V fetchData(K key) throws ExecutionException;
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	protected abstract boolean isNull(V value);
	
	public V getValue(K key) {
		V result = null;
		try {
			result = this.fetchData(key);
			if(isNull(result)) {
				result = null;
			}
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void putValue(K key, V value) {
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
	
	protected Cache<K,V> getCache(){ 
		return cache; 
	}
	
	

}
