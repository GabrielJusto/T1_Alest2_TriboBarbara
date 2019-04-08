public class Barbaro
{

    private String nome;
    private String pai;
    double terras;

    public Barbaro(String nome, String pai, double terras)
    {
        this.nome = nome;
        this.pai = pai;
        this.terras = terras;
    }

    public String getNome(){return nome;}
    public String getPai(){return pai;}
    public double getTerras(){return terras;}

}