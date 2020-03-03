package br.uefs.ecomp.allconnected.util;
/**
 * 
 * @author Daniel Santa Rosa Santos
 *
 */
public class Queue 
	{
    public LinkList list;
    
    public Queue() 
    	{
    	list = new LinkList();
    	}
    
    /**
     * 
     * @param element
     * @return
     */
    public boolean insert(Link element) 
    	{
    	return list.insert(element);
    	}
    
    /**
     * 
     * @return
     */
    public Link delete() 
    	{
    	return list.deleteFirst();
    	}
    
    /**
     * 
     * @return
     */
    public int size() {
    	return list.size;
    }
    /**
     * 
     * @return
     */
    public Link listAll() 
    	{
    	return list.listAll();
    	}
    
    /**
     * 
     * @param element
     */
    public void insertLast(Link element) 
    	{
    	this.list.insertLast(element);
    	}
}