package br.uefs.ecomp.allconnected.util;

public class Edge<T>
	{
	private T destiny;
	private int weight;
	
	public Edge(T d, int weight) 
		{
		this.destiny = d;
		this.weight = weight;
		}
	
	public T getDestiny()
		{
		return destiny;
		}
	
	public void setDestiny(T destiny)
		{
		this.destiny = destiny;
		}


	/**
	 * @return the weight
	 */
	public int getWeight() 
		{
		return weight;
		}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) 
		{
		this.weight = weight;
		}
	
	@Override
	public String toString() 
		{
		return this.destiny + "-" + this.weight;
		}
	
	}
