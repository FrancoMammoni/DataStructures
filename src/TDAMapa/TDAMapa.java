package TDAMapa;
import java.util.Iterator;
import java.util.Map.Entry;

import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.TDALista;

public class TDAMapa<K,V> implements Map<K,V> {
	public static final float fc=0.9f;
	protected PositionList<Entrada<K, V>>[] Bucket;
	protected int cant;
	protected int N;
	@SuppressWarnings("unchecked")
	public TDAMapa() {
		N=17;
		cant=0;
		Bucket=new TDALista[17];
		for(int i=0; i<17;i++) {
			Bucket[i]= new TDALista<Entrada<K,V>>();
		}
	}
	@Override
	public int size() {	
		return cant;
	}

	@Override
	public boolean isEmpty() {
		return cant==0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		V toret= null;
		if(key==null)
			throw new InvalidKeyException();

		int i = key.hashCode() % N;
		Iterator<Entrada<K, V>> iterador= Bucket[i].iterator();

		while(iterador.hasNext() && toret==null) {
			Entrada<K,V> aux= iterador.next();
			if(aux.getKey().equals(key)) {
				toret=aux.getValue();
			}
		}
		return toret;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(fc<cant/N)
			agrandar();
		if(key==null)
			throw new InvalidKeyException();
		int i= key.hashCode() % N;
		V toret =null;
		boolean encontrado=false;

		Iterator<Entrada<K, V>> iterador= Bucket[i].iterator();

		while(iterador.hasNext() && !encontrado) {
			Entrada<K,V> aux= iterador.next();
			if(aux.getKey().equals(key)) {
				toret=aux.getValue();
				aux.setValue(value);
				encontrado=true;
			}
		}

		if(!encontrado) {
			Bucket[i].addLast(new Entrada<K, V>(key,value));
			cant++;
		}
		return toret;
	}

	private void agrandar() {

		@SuppressWarnings("unchecked")
		PositionList<Entrada<K,V>> [] aux = new TDALista[siguientePrimo()];
		Iterator<Entry<K,V>> iterador= this.entries().iterator();

		for(int i=0; i<N;i++) 
			aux[i]= new TDALista<Entrada<K,V>>();
		cant=0;
		Bucket=aux;

		while(iterador.hasNext()) {
			Entry<K,V> pos =iterador.next();

			try {
				put(pos.getKey(),pos.getValue());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}

		// entrar lista por lista añadiendo de nuevo en mi tablahash nueva

	}

	private int siguientePrimo() {
		boolean encontre = false;
		int cont;
		N=N*2;
		while(!encontre) {
			cont = 0;
			for(int i = 1; i <= N; i++) {
				if(N % i == 0)
					cont++;
			}
			if(cont == 2)
				encontre = true;
			else
				N++;
		}
		return N;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException();
		int i= key.hashCode() % N;
		V toret =null;
		boolean borrado=false;
		Iterator<Position<Entrada<K, V>>> iterador= Bucket[i].positions().iterator();
		while(iterador.hasNext() && !borrado) {
			Position<Entrada<K,V>> aux= iterador.next();
			if(key==aux.element().getKey()) {
				toret= aux.element().getValue();
				try {
					Bucket[i].remove(aux);
					cant--;
				} catch (InvalidPositionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return toret;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> retorno= new TDALista<K>();
		Iterator<Entrada<K,V>> aux;
		for(PositionList<Entrada<K,V>> pos :Bucket) {
			aux= pos.iterator();
			while(aux.hasNext()) {
				retorno.addLast(aux.next().getKey());
			}
		}	
		return retorno;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> retorno= new TDALista<V>();
		Iterator<Entrada<K,V>> aux;
		for(PositionList<Entrada<K,V>> pos :Bucket) {
			aux= pos.iterator();
			while(aux.hasNext()) {
				retorno.addLast(aux.next().getValue());
			}
		}
		return retorno;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> retorno= new TDALista<Entry<K,V>>();
		Iterator<Entrada<K,V>> aux;
		for(PositionList<Entrada<K,V>> pos :Bucket) {
			aux=pos.iterator();
			while(aux.hasNext()) {
				retorno.addLast(aux.next());
			}
		}

		return retorno;
	}

}
