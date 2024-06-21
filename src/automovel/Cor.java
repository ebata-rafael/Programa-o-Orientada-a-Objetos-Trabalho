package automovel;

import utilitarios.StringUtils;

public class Cor
{
    private String nomeCor;

    public Cor() {
    }

    public Cor(String nomeCor)
    {
        this.nomeCor = StringUtils.formatarPadraoCapturaDeDados(nomeCor);
    }

    public String getNomeCor() {
        return nomeCor;
    }

    public void setNomeCor(String nomeCor) {
        this.nomeCor = nomeCor;
    }

    public String toString()
    {
        return nomeCor;
    }
}
