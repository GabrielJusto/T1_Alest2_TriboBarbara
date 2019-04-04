public class Barbaro
{

    private String nome;
    private String pai;
    int terras;

    public Barbaro(String nome, String pai, int terras)
    {
        this.nome = nome;
        this.pai = pai;
        this.terras = terras;
    }

    public String getNome(){return nome;}
    public String getPai(){return pai;}
    public int getTerras(){return terras;}

}