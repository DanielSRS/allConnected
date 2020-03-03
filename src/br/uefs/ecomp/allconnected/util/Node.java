package br.uefs.ecomp.allconnected.util;


/**
 * 
 * @author Daniel Santa Rosa
 *
 */
public class Node
	{
	private String key;  // Valor chave que identifica o n�
	private Object data;  //Dado salvo no n�
	private Node leftChild;  //Sub�rvore da esquerda
	private Node rightChild;  //Sub�rvore da direita
	private int height; //Altura deste n�/sub�rvore
	
	/**
	 * 
	 * @param key Identifica o n�. Necessario para sua cria��o.
	 * @param data Dado que ser� armazenado no n�.
	 */
	public Node(String key, Object data)
		{
		this.key = key;
		this.data = data;
		height = 0;
		}
	
	/**
	 * 
	 * @return O valor da string que identifica esse n�
	 */
	public String getKey()
		{
		return this.key;
		}
	
	/**
	 * 
	 * @return O dado armazenado no n�
	 */
	public Object getData()
		{
		return this.data;
		}
	
	/**
	 * 
	 * @return A sub�rvore [um n�] a esquerda do n� raiz.
	 */
	public Node getLeftChild()
		{
		return this.leftChild;
		}
	
	/**
	 * 
	 * @return A sub�rvore [um n�] a direita do n� raiz.
	 */
	public Node getRightChild()
		{
		return this.rightChild;
		}
	
	/**
	 * 
	 * @return A altura do n�/sub�rvore em quest�o.
	 */
	public int getHeight()
		{
		return this.height;
		}
	
	/**
	 * 
	 * @param newLeftChild Adiciona um n� como filho a esquerda do no atual, passando
	 * esta a ser uma sub�rvore a esquerda do n� em quest�o.
	 */
	public void setLeftChild(Node newLeftChild)
		{
		this.leftChild = newLeftChild;
		}
	
	/**
	 * 
	 * @param newRightChild Adiciona um n� como filho a direita do no atual, passando
	 * esta a ser uma sub�rvore a direita do n� em quest�o.
	 */
	public void setRightChild(Node newRightChild) 
		{
		this.rightChild = newRightChild;
		}
	
	/**
	 * 
	 * @param newHeight Novo o valor da altura do n�/sub�rvore
	 */
	public void setHeight(int newHeight)
		{
		this.height = newHeight;
		}
	}
