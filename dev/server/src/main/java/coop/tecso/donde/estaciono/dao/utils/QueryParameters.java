package coop.tecso.donde.estaciono.dao.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class QueryParameters implements Map<String, Object> {

	private Map<String, Object> queryParameters;

	private QueryParameters() {
		queryParameters = new HashMap<String, Object>();
	}

	public static QueryParameters getInstance() {
		return new QueryParameters();
	}

	@Override
	public int size() {
		return this.queryParameters.size();
	}

	@Override
	public boolean isEmpty() {
		return this.queryParameters.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return this.queryParameters.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return this.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return this.queryParameters.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return this.queryParameters.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return this.queryParameters.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		this.queryParameters.putAll(m);
	}

	@Override
	public void clear() {
		this.queryParameters.clear();
	}

	@Override
	public Set<String> keySet() {
		return this.queryParameters.keySet();
	}

	@Override
	public Collection<Object> values() {
		return this.queryParameters.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return this.queryParameters.entrySet();
	}

}
