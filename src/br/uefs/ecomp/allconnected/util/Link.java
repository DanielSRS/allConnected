package br.uefs.ecomp.allconnected.util;

/**
 * 
 * @author Daniel Santa Rosa Santos
 *
 */
public class Link {
	public Object data;
	public Object data2;
	public int priority;
	public Link next;
	
	/**
	 * 
	 * @param information
	 * @param urgency
	 */
	public Link(Object information, int urgency)
		{
		this.data = information;
		this.priority = urgency;
		}
	
	/**
	 * 
	 * @param information
	 * @param information2
	 * @param urgency
	 */
	public Link(Object information, Object information2, int urgency)
		{
		this.data = information;
		this.data2 = information2;
		this.priority = urgency;
		}
}