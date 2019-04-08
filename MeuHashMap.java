import java.util.HashMap;
import java.util.LinkedList;

public class MeuHashMap
{
	private HashMap<String, LinkedList<String>> dados;

	public MeuHashMap()
	{
		dados = new HashMap<>();
	}

	public void put (String chave, String valor)
	{
		if(dados.containsKey(chave))
		{
			LinkedList<String> aux = dados.get(chave);
			aux.add(valor);
			dados.remove(chave);
			//System.out.println("pai : " + chave + "  filhos: " + aux + "\n");
			dados.put(chave, aux);
		}
		else
		{
			LinkedList<String> valores = new LinkedList<>();
			valores.add(valor);
			dados.put(chave, valores);
		}
	}

	public LinkedList<String> get (String chave)
	{
		return dados.get(chave);
	}



	public LinkedList<Barbaro> calcula(String pai, HashMap<String, Barbaro> barbaros, LinkedList<Barbaro> folhas)
	{
		Barbaro maior = new Barbaro("","",0);
		LinkedList<String> filhos = dados.get(pai);
		if(filhos == null)
		{
			folhas.add(barbaros.get(pai));
		}
		else
		{
			for(String s : filhos)
			{
				double terrasFilho = barbaros.get(s).getTerras();
				double terrasPai = barbaros.get(pai).getTerras();
				Barbaro aux = new Barbaro(s, pai, terrasFilho + (terrasPai/filhos.size()));
				if(aux.getTerras() > maior.getTerras())
					maior = aux;
				barbaros.remove(s);
				barbaros.put(s, aux);
				calcula(s, barbaros, folhas);
			}
		}
		
		return folhas;
	}

}