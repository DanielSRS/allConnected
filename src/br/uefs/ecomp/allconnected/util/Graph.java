package br.uefs.ecomp.allconnected.util;
import java.util.*; 

//Java program to implement Graph 
//with the help of Generics 

public class Graph<T> {


	// We use Hashmap to store the edges in the graph 
	private Map<T, List<Object> > map = new HashMap<>(); 

	// This function adds a new vertex to the graph 
	public void addVertex(T s) 
	{ 
		map.put(s, new LinkedList<Object>()); 
	} 

	// This function adds the edge 
	// between source to destination 
	public void addEdge(T source, 
						T destination, 
						int weight, 
						boolean bidirectional) 
	{ 
		Edge<T> aresta1 = new Edge<T>(source, weight);
		Edge<T> aresta2 = new Edge<T>(destination, weight);

		if (!map.containsKey(source)) 
			addVertex(source); 

		if (!map.containsKey(destination)) 
			addVertex(destination); 

		map.get(source).add(aresta2); 
		if (bidirectional == true) { 
			map.get(destination).add(aresta1); 
		} 
	} 

	/*
	public String getVertexCount() 
	{ 
		return ("The graph has "
						+ map.keySet().size() 
						+ " vertex"); 
	}
	*/

	/*
	public String getEdgesCount(boolean bidirection) 
	{ 
		int count = 0; 
		for (T v : map.keySet()) { 
			count += map.get(v).size(); 
		} 
		if (bidirection == true) { 
			count = count / 2; 
		} 
		return ("The graph has "
						+ count 
						+ " edges."); 
	} 
	
	*/

	// This function gives whether 
	// a vertex is present or not. 
	public String hasVertex(T s) 
	{ 
		if (map.containsKey(s)) { 
			return ("The graph contains "
							+ s + " as a vertex."); 
		} 
		else { 
			return ("The graph does not contain "
							+ s + " as a vertex."); 
		} 
	} 

	// This function gives whether an edge is present or not. 
	@SuppressWarnings("unchecked")
	public double hasEdge(T s, T d) 
	{
		List<Object> v = map.get(s);
		for (Object h: v) {
			if((((Edge<T>) h).getDestiny() == d)) {
				return ( (Edge<T>) h).getWeight();
			}
		}
		return -1;  
	} 

	// Prints the adjancency list of each vertex. 
	@Override
	public String toString() 
	{ 
		StringBuilder builder = new StringBuilder(); 

		for (T v : map.keySet()) { 
			builder.append(v.toString() + ": "); 
			for (Object w : map.get(v)) { 
				builder.append(w.toString() + " "); 
			} 
			builder.append("\n"); 
		} 

		return (builder.toString()); 
	}
	
	@SuppressWarnings("unchecked")
	public void smp() {
		int keySetSize = this.map.keySet().size();				// quantidade de vertices no grafo
		Object[] keyList = this.map.keySet().toArray();			// transforma em um array
		
		for(Object theKey: keyList) { 							// para cada vertice do grafo
		

			Object[] shortestPath = new Object[keySetSize - 1];	
			
			int j = 0;											//pervocorre o arrray com os vertices (chaves) do grafo
			for(int indexForShortestPath = 0;
					indexForShortestPath < keySetSize -1;
					indexForShortestPath++, j++) 
			{
				if(keyList[indexForShortestPath] == theKey) j++;			// ignora o vertice selecionado anteriormente (k)
				dj<T> a = new dj<T>(( (T) keyList[j]), -1, false, null); //
				shortestPath[indexForShortestPath] = a;			// adiciona todos os outros nos no vetor
			}
			
			int unmarked = keySetSize - 1;						// quantos menores caminhos ainda não foram encontrados;
			int shortestIndex = 0;								// ultimoo menor caminho encontrado; indice do vetor;
			T selected = (T) theKey;							// vertice selecionado atual. diferente do vertice selecionado anres; muda a cada iteração do loop a seguir
			
			double weight = 0;
			
			while(unmarked > 0) {
			
				for(int l = 0; l < keySetSize -1; l++) {			//percorre o vetor verificando os menores caminhos entre os vertices
					double peso = this.hasEdge(selected, ((dj<T>) shortestPath[l]).key);			//distancia entre o no selecionado e um dos nos no array
					if(peso == -1) {}							//não é vizinho;
					
					
					//se encontrado um caminho até o vertice
					//se encontrado um outro camiho para o vertice que ja não seja o menor
					else if(( (dj<T>) shortestPath[l]).peso == -1 || 
							( ( (dj<T>) shortestPath[l]).peso > (peso + weight) & 
							!( (dj<T>) shortestPath[l]).marked)) 
					{
					
						( (dj<T>) shortestPath[l]).peso = weight + peso;
						( (dj<T>) shortestPath[l]).origin = selected;			//nao é selected ou é?
					}
				}
				
				shortestIndex = 0;
				//selecionar menor caminho encontrado na ultima execussão
				for(int y = 0; y < keySetSize - 1; y++) {
					
					//selecionando primeiro novo (não marcado) caminho encontrado para comparar com o restante
					if(( (dj<T>) shortestPath[shortestIndex]).marked || 
						 ( (dj<T>) shortestPath[shortestIndex]).peso == -1 & 
						 ( (dj<T>) shortestPath[y]).peso != -1 & 
						 !( ( (dj<T>) shortestPath[y]).marked)) 
					{
						shortestIndex = y;
					}
					
					//se oncontrado um novo menor caminho no vetor
					else if(( (dj<T>) shortestPath[y]).peso != -1 & 
							( (dj<T>) shortestPath[y]).peso < ( (dj<T>) shortestPath[shortestIndex]).peso & 
							!(( (dj<T>) shortestPath[y]).marked)) 
					{
						shortestIndex = y;
					}
				}
				
				( (dj<T>) shortestPath[shortestIndex]).marked = true;			//marca o menor vertice / caminho
				weight = ( (dj<T>) shortestPath[shortestIndex]).peso;
				selected = ( (dj<T>) shortestPath[shortestIndex]).key;
				unmarked = unmarked - 1;
			}
			
			// Resultado
			for(int w = 0; w < keySetSize - 1; w++) {
				System.out.print("De " + theKey.toString() + " para: ");
				System.out.print(( (dj<T>) shortestPath[w]).key.toString());
				System.out.print(", com peso: ");
				System.out.print(( (dj<T>) shortestPath[w]).peso);
				System.out.print(", e vindo de: ");
				if(( (dj<T>) shortestPath[w]).origin != null)
					System.out.print(( (dj<T>) shortestPath[w]).origin.toString());
				else System.out.print("null");
				System.out.print("\n\n");
			}
			
		}
	}
	

	class dj<L>{
		public L key;
		public double peso;
		public boolean marked;
		public L origin;
		
		public dj(L k, double p, boolean m, L o) {
			this.key = k;
			this.marked = m;
			this.origin = o;
			this.peso = p;
		}
	}
}

