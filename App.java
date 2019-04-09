
import java.io.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;


public class App
{
	public static void main(String[] args) 
	{
		double inicio = System.currentTimeMillis();
	
		int terras;
		String nome;
		String pai;
	    String dados [];	
	    double terraInicial = 0;
	    Tree arvore = new Tree();
		//ArrayList<Barbaro> barbaros = new ArrayList<>(100);
		HashMap<String, Barbaro> barbaros = new HashMap<>();
		MeuHashMap nodos = new MeuHashMap();
		HashSet<String> pais = new HashSet<>();//guarda o nome de todos os barbaros que possuem filhos
		HashSet<String> filhos = new HashSet<>();//guarda o nome de todos os barbaros que possuem pai
		try
		{
		//leitura do arquivo de entrada
	     BufferedReader br = new BufferedReader(new FileReader(args[0]));
	     String linha = br.readLine();
	    
	    //armazena o numero de terras do primeiro barbaro
	    terraInicial = Double.parseDouble(linha);

	    int count = 0;
	     while(br.ready())
	     {
	     	linha = br.readLine();
	        dados = linha.split(" ");
	        terras = Integer.parseInt(dados[2]);
	        nome = dados[1];
	        pai = dados[0];

	        pais.add(pai);
	        filhos.add(nome);

			barbaros.put(nome, new Barbaro(nome, pai, terras));
			nodos.put(pai, nome);
			//System.out.println("barbaro: " + barbaros.get(count).getNome());
			count ++;
	     }
		
	     br.close();
	  }catch(IOException ioe)
	  {
	     ioe.printStackTrace();
	  }




	  //Encontra o primeiro barbaro da árvore
	    String raiz = ""; 
	    for(String p : pais)
	    {
	    	if(!filhos.contains(p))
	    		raiz = p;
	    }
	    barbaros.put(raiz, new Barbaro(raiz, null, terraInicial));
	    LinkedList<Barbaro> folhas = new LinkedList<>();
	    folhas = nodos.calcula(raiz, barbaros, folhas);

	    Barbaro maior = new Barbaro("","",0);
	    for(Barbaro b : folhas)
	    {
	    	if(b.getTerras()>maior.getTerras())
	    		maior = b;
	    }
	    

	    
	    //adiciona o primeiro barbaro na árvore
	    /*
	    arvore.add(raiz, terraInicial, null);
	    arvore.add(raiz, nodos, barbaros);



	    /*
	    //para todo finho f presente em filhos se o pai de f está na árvore entao f é adicionado na árvore
	    boolean inseriuTodos = false;
	    int tamanhoFilhos = filhos.size();
	    Iterator<String> it = filhos.iterator(); 

	    
		while (!inseriuTodos)
		{
			it = filhos.iterator();
			while(it.hasNext())
			{
				String f = it.next();
				if( arvore.contains(barbaros.get(f).getPai()) && !arvore.contains(barbaros.get(f).getNome()) )
				{
					Barbaro aux = barbaros.get(f);
		     		arvore.add(aux.getNome(), aux.getTerras(), aux.getPai());

		     		//adicina na árvore todos os filhos de "aux"
		     		Iterator<String> itPai = filhos.iterator();
		     		while(itPai.hasNext())
		     		{
		     			String filhoDoFilho = itPai.next();
		     			Barbaro b = barbaros.get(filhoDoFilho);
		     			if(b.getPai().equals(aux.getNome()))
		     			{
		     				arvore.add(b.getNome(), b.getTerras(), b.getPai());
		     				itPai.remove();
		     			}
		     		}

		     		//System.out.println("adicionou: " + aux.getNome() + "\n Tamanho: " + filhos.size() );
		     		it.remove();
		     		tamanhoFilhos--;
		     		if( tamanhoFilhos == 0 )
		     		{
		     			inseriuTodos = true;
		     			break;
		     		}
				}
			} 
		}
		*/
		

     //System.out.println(arvore.calculaTerra());
     double fim = System.currentTimeMillis();
     double total = (fim - inicio)/1000;
     System.out.println(maior.getNome() + " " + maior.getTerras() + " " + total);
     
	}
}

