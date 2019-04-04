import java.util.LinkedList;
import java.util.HashMap;

public class Tree 
{
	private class Node 
	{

	    public Node pai;
	    public String nome;
	    public double terras;
	    public LinkedList<Node> filhos;

	    public Node(String nome, double terras)
	    {
	        pai = null;
	        this.terras = terras;
	        this.nome = nome;
	        filhos = new LinkedList<>();
	    }

	    public void addFilho(Node n) 
	    {
	        n.pai = this;
	        filhos.add(n);
	    }

	    public Node getFilho(int i) 
	    {
	        if ((i < 0) || (i >= filhos.size())) 
	        {
	            throw new IndexOutOfBoundsException();
	        }
	        return filhos.get(i);
	    }

	    public int getQuantFilhos() 
	    {
	        return filhos.size();
        }
        public boolean isRoot()
        {
            if(pai == null)
                return true;
            
            return false;   
        }
	}

	Node raiz;
	int count;

	public Tree()
	{
		raiz = null;
		count = 0;
	}


	public boolean add(String nome, double terras, String pai) 
	{
        Node n = new Node(nome, terras);
        Node nAux = null;
        boolean res = false;
        if (pai == null) 
        {	
            if (raiz != null) 
            {
                n.addFilho(raiz);
                raiz.pai = n;
            }
            raiz = n;
            res = true;
            count++;
        } else 
        {     
            nAux = searchNodeRef(pai, raiz);
            if (nAux != null) 
            {
                nAux.addFilho(n);
                n.pai = nAux;
                res = true;
                count++;
            }
        }  
        
        //System.out.println(n.nome);
        return res;
    }


    private double calculaTerraRec(Node n, double terras)
    {
        if(n.isRoot())
            return n.terras;
        else
            return calculaTerraRec(n.pai, (terras + (n.pai.terras/n.pai.getQuantFilhos()))); 
    }
    public double calculaTerra()
    {
        double maior = 0;
        LinkedList<Node> folhas = getFolhas();
        /*
        for( int i=0; i<folhas.size(); i++ )
            System.out.println(folhas.get(i).nome + " " + folhas.get(i).terras);
            */



        for(Node n: folhas)
        {
            double terras = n.terras;
            terras = calculaTerraRec(n, terras);
            System.out.println( n.nome + " " + terras );
            if(terras > maior)
                maior = terras;
        }
        return maior;
    }

    private LinkedList<Node> getFolhas()
    {
        LinkedList<Node> folhas = new LinkedList<Node>();
        getFolhasRec(raiz, folhas);
        return folhas;
    }
    private void getFolhasRec(Node n, LinkedList<Node> folhas)
    {
        if(n != null )
        {
            if (n.getQuantFilhos() == 0)
                folhas.add(n);
            else
            {
                for(int i=0; i<n.getQuantFilhos(); i++)
                {
                    //System.out.println("Nodo: " + n.nome + "Filho : " + n.getFilho(i).nome);
                    getFolhasRec(n.getFilho(i), folhas);
                }
                    
            }
            
        }
    }




    public boolean folha(String nome) 
    {
        Node n = searchNodeRef(nome, raiz);
        if (n != null) {
            return n.getQuantFilhos() == 0;
        }
        return false;
    }



     private Node searchNodeRef(String pai, Node target) {
        Node res = null;
        if (target != null) {
            if (pai.equals(target.nome)) 
            {
                res = target;
            } else {
                Node aux = null;
                int i = 0;
                while ((aux == null) && (i < target.getQuantFilhos())) {
                    aux = searchNodeRef(pai, target.getFilho(i));
                    i++;
                }
                res = aux;
            }
        }
        return res;
    }



    public boolean contains(String nome) 
    {
        Node nAux = searchNodeRef(nome, raiz);
        return (nAux != null);
    }
}