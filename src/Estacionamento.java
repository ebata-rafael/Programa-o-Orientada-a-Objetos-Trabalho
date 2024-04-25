import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import kitmenu.MenuGerenciaCliente;
import kitmenu.MenuGerenciaVagas;
import kitmenu.UI;
import modelagem.Vaga;
import tarifacao.ValorHora;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Estacionamento
{
    public static void main(String[] args)
    {
        UI terminal = UI.getInstance();
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        MenuGerenciaCliente menuGerenciaCliente = new MenuGerenciaCliente(terminal);
        ValorHora[] valorHoras = new ValorHora[7];
        ArrayList<Vaga> vagas = new ArrayList<>();
        MenuGerenciaVagas menuGerenciaVagas = new MenuGerenciaVagas(terminal);

        byte opcaoPrincipal;

        terminal.menuPrincipal();
        opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");

        do {
            switch (opcaoPrincipal) {
                case 1:
                    menuGerenciaCliente.gerenciaCliente(clientes, veiculos);
                    break;
                case 2:
                    menuGerenciaVagas.GerenciaVagas(vagas);
                    break;
                case 3:
                    terminal.menuGerenciaEstacionamento();
                    opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");
                    //menuEstacionaBem.menuGerenciaCliente(opcaoPrincipal, clientes);
                    break;
            }

            terminal.menuPrincipal();
            opcaoPrincipal = terminal.selecionarByte("Digite a opção desejada: ");
        } while (opcaoPrincipal != 6);
    }
}