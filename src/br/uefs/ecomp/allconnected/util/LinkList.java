package br.uefs.ecomp.allconnected.util;
/**
 * 
 * @author Daniel Santa Rosa Santos
 *
 */
public class LinkList
    {
    private Link first;
    private Link last;
    public int size;
    
    /**
     * 
     */
    public LinkList()
        {
        size = 0;
        }
    
    /**
     * 
     * @param element
     * @return
     */
    public boolean insert(Link element)
        {
        int priority = element.priority;
        Link previous = this.first;
        Link current = this.first;
        
        do
            {
            if(size == 0)
                {
                first = element;
                last = element;
                size++;
                return true;
                }
            else if(size == 1)
                {
                if(priority < current.priority)
                    {
                    element.next = first;
                    first = element;
                    size++;
                    return true;
                    }
                else
                    {
                    first.next = element;
                    last = element;
                    size++;
                    return true;
                    }
                }
            else
                {
                if(current != previous && priority >= previous.priority && priority < current.priority)
                    {
                    element.next = current;
                    previous.next = element;
                    size++;
                    return true;
                    }
                
                }
            previous = current;
            current = current.next;
            }while(current != null);
        if(priority >= previous.priority && current == null)
        	{
	        last.next = element;
	        last = element;
	        size++;
	        return true;
        	}
        return false;
        }
        
    public Link deleteFirst()
        {
        Link temp = this.first;
        if(temp == null)
            {
            return temp;
            }
        first = first.next;
        if(first == null)
            {
            last = null;
            }
        size--;
        return temp;
        }
    
    public boolean insertLast(Link element) 
    	{
    	if(first == null) 
    		{
    		first = element;
    		last =  element;
    		size++;
    		return true;
    		}
    	else
    		{
    		last.next = element;
    		last = element;
    		size++;
    		return true;
    		}
    	}
    
    /**
     * 
     * @return
     */
    public Link listAll()
    	{
    	return this.first;
    	}
    
    /**
     * 
     * @return 
     */
    public Link getFirst()
        {
        return this.first;
        }
    
    /**
     * 
     * @return
     */
    public Link getLast()
        {
        return this.last;
        }
    }