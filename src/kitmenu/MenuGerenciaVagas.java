package kitmenu;

import dados.Repositorio;
import enums.TipoVeiculo;
import excecoes.EstacionamentoException;
import ingressos.TicketEstacionamento;
import interfaces.InterfaceUsuario;
import interfaces.Terminal;
import modelagem.Vaga;
import operacoes.FuncionalidadesVaga;

import java.util.ArrayList;

public class MenuGerenciaVagas {

    public static void GerenciarVagas() {
        InterfaceUsuario interfaceUsuario = Terminal.getInstance();
        FuncionalidadesVaga funcVagas = Repositorio.getInstance().getFuncVaga();
        ArrayList<Vaga> vagas = Repositorio.getInstance().getVagas();
        ArrayList<TicketEstacionamento> tickets = Repositorio.getInstance().getTickets();
        byte opcao;
        int numeroVaga;
        String rua;
        Vaga vaga;


        do {

            interfaceUsuario.exibirMenuGerenciaVagas();
            opcao = interfaceUsuario.selecionarByte("Digite a opção desejada: ");

            switch (opcao) {
                case 1: //cadastrar vaga

                    vaga = funcVagas.cadastrarVaga(vagas);

                    Vaga vagaExiste = funcVagas.consultarVaga(vagas, vaga.getNumeroVaga());

                    if(vagaExiste != null)
                        throw new EstacionamentoException("Esta vaga já existe!");

                    vagas.add(vaga);
                    interfaceUsuario.exibir("Vaga cadastrada com sucesso!");

                    break;
                case 2: // consultar vaga
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = funcVagas.consultarVaga(vagas, numeroVaga);
                    if (vaga == null)
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);

                    interfaceUsuario.exibir(vaga.toString());

                    break;
                case 3: // excluir vaga

                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = funcVagas.consultarVaga(vagas, numeroVaga);
                    if (vaga != null) {
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);
                    }
                    if (funcVagas.verificaSeAVagaTemTicket(vaga, tickets)) {
                        throw new EstacionamentoException("Não é possivel exluir uma vaga que possui um carro estacionado");
                    }
                    vagas.remove(vaga);
                    interfaceUsuario.exibir("Vaga excluida com sucesso!");

                    break;
                case 4: // atualizar dados da vaga
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = funcVagas.consultarVaga(vagas, numeroVaga);

                    if (vaga == null) {
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);
                    }

                    interfaceUsuario.exibir("Rua atual: " + vaga.getRua());
                    rua = interfaceUsuario.selecionarString("Rua nova: ");

                    interfaceUsuario.exibir("Numero atual: " + vaga.getNumeroVaga());
                    int numeroVagaNovo = interfaceUsuario.selecionarInt("Numero novo: ");

                    Vaga verificaVaga = funcVagas.consultarVaga(vagas, numeroVagaNovo);

                    if (verificaVaga != null) {
                        throw new EstacionamentoException("Ja existe vaga cadastrada com o numero: " + numeroVaga);
                    }

                    vaga.setNumeroVaga(numeroVagaNovo);
                    vaga.setRua(rua);

                    break;
                case 5: // alterar disponibilidade da vaga
                    numeroVaga = interfaceUsuario.selecionarInt("Digite o numero da vaga: ");
                    vaga = funcVagas.consultarVaga(vagas, numeroVaga);

                    if (vaga == null)
                        throw new EstacionamentoException("Não existe vaga cadastrada com o numero: " + numeroVaga);

                    funcVagas.alterarDisponibilidade(vaga);
                    break;
                case 6: //voltar
                    break;
                default:
                    throw new EstacionamentoException("Opção inválida de menu");
            }
        } while (opcao != 6);
    }
}
