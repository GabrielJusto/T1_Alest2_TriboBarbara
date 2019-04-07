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

}