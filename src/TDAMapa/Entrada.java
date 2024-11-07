package TDAMapa;
import java.util.Map.Entry;

public class Entrada<K, V> implements Entry<K,V> {
protected K key;
protected V value;

	public Entrada(K _key, V _value) {
		key = _key;
		value = _value;
	}

	@Override
	public K getKey() {
	
		return key;
	}

	@Override
	public V getValue() {
		
		return value;
	}

	@Override
	public V setValue(V value) {

		V anterior = this.value;
		this.value = value;
		
		return anterior;
	}

}
