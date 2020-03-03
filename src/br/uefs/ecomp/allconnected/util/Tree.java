package br.uefs.ecomp.allconnected.util;

import java.util.ArrayList;

/**
 * 
 * @author Daniel Santa Rosa
 *
 */
public class Tree
	{
	private Node root;  // Raiz da �rvore
	
	public Tree() {}
	
	/**
	 * 
	 * @param key Valor de identifica��o do n� na �rvore.
	 * @param data Informa��o salva no n�
	 */
	public void insert(String key, Object data)
	    {
		// Atualiza a raiz caso tenha mudado.
		this.root = insert(key, data, this.root);
	    }

    /**
     * 
     * @param key Valor de identifica��o do n� na �rvore.
     * @param data Informa��o salva no n�
     * @param root Raiz da �rvore/Sub�rvore
     * @return Refer�ncia para a nova raiz da �rvore dado que esta pode ter sido modificada
     * durante a inser��o.
     */
	public Node insert(String key, Object data, Node root)
		{
		Node newNode = new Node(key, data);
		
		if(root == null) return newNode; // se a �rvore, vazia.
		
		if(compare(key, root.getKey()) == 1) //se a chave menor que a da raiz...
		    {
			root.setLeftChild(insert(key, data, root.getLeftChild())); //insere na sub�rvore a esquerda
			if(hight(root.getLeftChild()) - hight(root.getRightChild()) == 2) //verifica se houve desbalanceamento 
			    {
				if(compare(key, root.getLeftChild().getKey()) == 1) //se uma inser��o externa... 
				    {
					root = right(root); //faz uma rota��o simples a direita
					}
				else //se uma inser��o interna 
				    {
					root = leftRight(root); //faz uma rota��o dupla esquerda direita
					}
				}
			}
		else if(compare(key, root.getKey()) == -1) //se a chave maior que a da raiz... 
			{
			root.setRightChild(insert(key, data, root.getRightChild())); //insere na sub�rvore a direita
			if(hight(root.getRightChild()) - hight(root.getLeftChild()) == 2) //verifica se houve desbalanceamento
				{
				if(compare(key, root.getRightChild().getKey()) == -1) //se externo 
				    {
					root = left(root); //rota��o esquerda
					}
				else // se interno 
				    {
					root = rightLeft(root); //rota��o direita 
					}
				}
			}
		root.setHeight(max(hight(root.getLeftChild()), hight(root.getRightChild())) + 1);
		return root;
		} 
	
	/**
	 * 
	 * @param fKey Valor da chave procurada (que ser� comparada com a chave que identifica o n�)
	 * @param root Raiz da �rvore onde est� sendo procurada o valor
	 * @return Dado armazenado no n� caso encontrado, se n�o existe, ent�o null
	 */
	public static Object search(String fKey, Node root)
		{
		if(root == null) return null; //se vazia
		if(compare(fKey, root.getKey()) == 0) return root.getData();  //se igual a raiz
		if(compare(fKey, root.getKey()) == 1) return search(fKey, root.getLeftChild()); // se menor que a raiz
		if(compare(fKey, root.getKey()) == -1) return search(fKey, root.getRightChild()); //se mair que a raiz
		return null;
		}


	/**
	 * 
	 * @return String com as chaves de todos os elementos da �rvore
	 */
	public String listAll()
		{
		return this.listAll(this.root);
		}
	
	/**
	 * 
	 * @param root Raiz da �rvore/Sub�rvore listada
	 * @return String com as chaves de todos os elementos da �rvore
	 */
	public String listAll(Node root) 
		{
		String s;
		if(root != null)
			{
			
			s = this.listAll(root.getLeftChild());
			s = s + root.getKey() + " - ";
			s = s + this.listAll(root.getRightChild());
			return s;
			}
		return "";
		}
	
	/**
	 * 
	 */
	public void updateHeight()
		{
		this.updateHeight(this.root);
		}
	
	/**
	 * 
	 * @param root 
	 */
	public void updateHeight(Node root) 
		{
		if(root != null)
			{
			
			this.updateHeight(root.getLeftChild());
			root.setHeight(max(hight(root.getLeftChild()), hight(root.getRightChild())) + 1);
			this.updateHeight(root.getRightChild());
			root.setHeight(max(hight(root.getLeftChild()), hight(root.getRightChild())) + 1);
			}
		}
	
	
	/**
	 * 
	 * @param rKey Valor de identifica��o do n� a ser removido
	 * @return true se a remo��o aconteceu, false se o elemento n�o existe na �rvore (elemento n�o removido)
	 */
	public boolean remove(String rKey)
		{
		// root � a raiz da �rvore
		// mother � o pai do no removido
		// node � o no removido
		// p � pai do no removido
		// q � substituto do no removido
		Node mother, node, p, q;
		
		node = (searchForRemove(rKey)).get(1);
		mother = (searchForRemove(rKey)).get(0);
		
		if(node == null) return false;
		
		if(node.getLeftChild() == null || node.getRightChild() == null)
			{
			if(node.getLeftChild() == null)
				{
				q = node.getRightChild();
				}
			else q = node.getLeftChild();
			}
		else
			{
			p = node;
			q = node.getLeftChild();
			while(q.getRightChild() != null)
				{
				p = q;
				q = q.getRightChild();
				}
			if(p != node)
				{
				p.setRightChild(q.getLeftChild());
				q.setLeftChild(node.getLeftChild());
				}
			q.setRightChild(node.getRightChild());
			}
		updateHeight(this.root);
		if(mother == null) // se removendo a raiz
			{
			this.root = balance(q);
			return true;
			}
		if(compare(rKey, mother.getKey()) == 1)
			{
			mother.setLeftChild(balance(q));
			return true;
			}
		else 
			{
			mother.setRightChild(balance(q));
			return true;
			}
		}
	
	/**
	 * 
	 * @param q N�/Sub�rvore possivelmente desbalanceado(a)
	 * @return
	 */
	public Node balance(Node q)
		{
		
		if(q == null) return null;
		
		int z = hight(q.getLeftChild()) - hight(q.getRightChild());
		if(z == 2)
			{
			int m = hight(q.getLeftChild().getLeftChild()) - hight(q.getLeftChild().getRightChild());
			if(m == 1)
				{
				return right(q);
				}
			else if (m == -1)
				{
				return leftRight(q);
				}
			}
		if(z == -2)
			{
			int m = hight(q.getLeftChild().getLeftChild()) - hight(q.getLeftChild().getRightChild());
			if(m == -1)
				{
				return left(q);
				}
			else if (m == 1)
				{
				return rightLeft(q);
				}
			}
		return q;
		}
	
	
	/**
	 * 
	 * @param rKey Valor do n� procurado para remo�o
	 * @return Uma lista com dois n�s, sendo o primeiro a raiz do n� a ser removido e o
	 * segundo, uma refer�ncia para o n� removido. Se existir o elemento procurado, ent�o null
	 */
	public ArrayList<Node> searchForRemove(String rKey)
		{
		Node current = this.root;
		Node father = null;
		ArrayList<Node> pair;
		
		while(current != null)
			{
			if(compare(rKey, current.getKey()) == 0)
				{
				pair = new ArrayList<Node>();
				pair.add(father);
				pair.add(current);
				return pair;
				}
			father = current;
			if(compare(rKey, current.getKey()) == 1) current = current.getLeftChild();
			else current = current.getRightChild();
			}
		return null;
		}
	
	/**
	 * 
	 * @param unbalancedRoot �rvore/Sub�rvore desbalanceada
	 * @return Nova raiz da �rvore/sub�rvore
	 */
	public Node right(Node unbalancedRoot) //Rota��o simples a direita
		{
		Node aux = unbalancedRoot.getLeftChild();
		unbalancedRoot.setLeftChild(aux.getRightChild());
		aux.setRightChild(unbalancedRoot);
		
		unbalancedRoot.setHeight(max(hight(unbalancedRoot.getLeftChild()), hight(unbalancedRoot.getRightChild())) + 1);
		aux.setHeight(max(hight(aux.getLeftChild()), unbalancedRoot.getHeight()) + 1);
		
		return aux;
		}
	
	/**
	 * 
	 * @param unbalancedRoot �rvore/Sub�rvore desbalanceada
	 * @return Nova raiz da �rvore/sub�rvore
	 */
	public Node left(Node unbalancedRoot) //Rota��o simples a esquerda
		{
		Node aux = unbalancedRoot.getRightChild();
		unbalancedRoot.setRightChild(aux.getLeftChild());
		aux.setLeftChild(unbalancedRoot);
		
		unbalancedRoot.setHeight(max(hight(unbalancedRoot.getLeftChild()), hight(unbalancedRoot.getRightChild())) + 1);
		aux.setHeight(max(hight(aux.getRightChild()), unbalancedRoot.getHeight()) + 1);
		
		return aux;
		}
	
	/**
	 * 
	 * @param unbalancedRoot �rvore/Sub�rvore desbalanceada
	 * @return Nova raiz da �rvore/sub�rvore
	 */
	public Node leftRight(Node unbalancedRoot)
		{
		unbalancedRoot.setLeftChild(left(unbalancedRoot.getLeftChild()));
		return right(unbalancedRoot);
		}
	
	/**
	 * 
	 * @param unbalancedRoot �rvore/Sub�rvore desbalanceada
	 * @return Nova raiz da �rvore/sub�rvore
	 */
	public Node rightLeft(Node unbalancedRoot)
		{
		unbalancedRoot.setRightChild(right(unbalancedRoot.getRightChild()));
		return left(unbalancedRoot);
		}
	
	/**
	 * 
	 * @param string1
	 * @param string2
	 * @return
	 */
	public static int compare(String string1, String string2)
		{
		int lenght1 = string1.length();
		int lenght2 = string2.length();
		int min = min(lenght1, lenght2);
		char char1, char2;
		
		//if(lenght1 != lenght2) return (lenght1 - lenght2);
		
		for(int i=0; i < min; i++)
			{
			char1 = Character.toLowerCase((string1.charAt(i)));
			char2 = Character.toLowerCase((string2.charAt(i)));
			 
			if(char1 < char2) return 1; //se a primeira string � menor
			if(char1 > char2) return (-1); //se segunda string for menor
			}
		if(lenght1 == min && min == lenght2) return 0; //se as duas forem iguais
		if(lenght1 == min) return 1; //se a primeira � menor e igual ao inicio da segunda
		return (-1);  // se a segunda � menor e igual ao inicio da primeira
		}
	
	
	/**
	 * 
	 * @param a Um valor inteiro qualquer
	 * @param b Um valor inteiro qualquer
	 * @return O maior (inteiro) dos valores de a e b
	 */
	public static int max(int a, int b)
		{
		if(a > b) return a;
		return b;
		}
	
	/**
	 * 
	 * @param a Um valor inteiro qualquer
	 * @param b Um valor inteiro qualquer
	 * @return O menor (inteiro) dos valores de a e b
	 */
	public static int min(int a, int b)
		{
		if(a < b) return a;
		return b;
		}
	
	/**
	 * 
	 * @param root N�/sub�rvore que se deseja saber a altura
	 * @return O valor (inteiro) da altura da sub�rvore/n�
	 */
	public int hight(Node root) 
		{
		if(root == null) return (-1); // A altura de um n�/filho/sub�rvore vazio � -1
		return root.getHeight();
		}
	
	/**
	 * 
	 * @return O n� raiz da �rvore
	 */
	public Node getRoot()
		{
		return this.root;
		}
	
	}
