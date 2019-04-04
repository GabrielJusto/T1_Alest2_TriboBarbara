
import java.io.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;


public class App
{
	public static void main(String[] args) 
	{
	
		int terras;
		String nome;
		String pai;
	    String dados [];	
	    double terraInicial = 0;
	    Tree arvore = new Tree();
		//ArrayList<Barbaro> barbaros = new ArrayList<>(100);
		HashMap<String, Barbaro> barbaros = new HashMap<>();
		HashSet<String> pais = new HashSet<>();
		HashSet<String> filhos = new HashSet<>();

		try
		{
		//leitura do arquivo de entrada
	     BufferedReader br = new BufferedReader(new FileReader("casoJB4a"));
	     String linha = br.readLine();
	    
	    System.out.println(linha);
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
			//System.out.println("barbaro: " + barbaros.get(count).getNome());
			count ++;
	     }
		
	     br.close();
	     System.out.println(arvore.calculaTerra());
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

	     //adiciona o primeiro barbaro na árvore
	     arvore.add(raiz, terraInicial, null);

	     //para todo filho f presente em "filhos" se barbaros.get(f).getPai() está na árvore
	     //então f é adicionado na árvore
	     boolean inseriuTodos = false;
	     int tamanhoFilhos = filhos.size();
	     while( !inseriuTodos )
	     {
		     for( String f : filhos )
		     {
		     	if( arvore.contains(barbaros.get(f).getPai()) )
		     	{
		     		Barbaro aux = barbaros.get(f);
		     		arvore.add(aux.getNome(), aux.getTerras(), aux.getPai());
		     		tamanhoFilhos--;
		     		System.out.println("adicionou: " + aux.getNome() + "\n" + tamanhoFilhos);
		     		if( tamanhoFilhos == 0 )
		     		{
		     			inseriuTodos = true;
		     			break;
		     		}
		     	}
		     }
		 }

     System.out.println(arvore.calculaTerra());
	}
}