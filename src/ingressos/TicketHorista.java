package ingressos;

import automovel.Veiculo;
import cliente.estacionabem.Cliente;
import modelagem.Vaga;
import tarifacao.TarifaHorista;
import utilitarios.CalculoUtils;
import utilitarios.DataUtils;

import java.time.LocalDateTime;

public class TicketHorista extends TicketEstacionamento {


    public TicketHorista(Cliente cliente, Vaga vaga, Veiculo veiculo, TarifaHorista tarifa){
        super(cliente, vaga, veiculo, tarifa);
    }

    public void encerrarTicket() {
        this.dataFim = LocalDateTime.now();
        this.totalPagar = calcularTotalPagar();
    }

    public double calcularTotalPagar(){

        double valorTotal = 0;
        long tempoEstacionadoEmHoras = 0;
        LocalDateTime inicioDoDia;

        if(!DataUtils.equalsData(this.getDataInicio(), this.getDataFim())) {

            inicioDoDia = this.getDataInicio().plusDays(1); //acrescenta um dia
            inicioDoDia = inicioDoDia.toLocalDate().atStartOfDay(); //inicializa com a virada do dia(00:00)

            // calculo da multa
            long tempoEstacionadoDaMulta = CalculoUtils.calcularTempoEstacionado(inicioDoDia, this.getDataFim());
            double valorMulta = calcularMulta(tempoEstacionadoDaMulta);

            tempoEstacionadoEmHoras -= tempoEstacionadoDaMulta;
            valorTotal += valorMulta;
        }

        tempoEstacionadoEmHoras += CalculoUtils.calcularTempoEstacionado(this.getDataInicio(), this.getDataFim());
        valorTotal += calcularValorDoTempoEstacionado(tempoEstacionadoEmHoras);

        return valorTotal;
    }

    public double calcularMulta(long tempoEstacionado) {
        return ((((TarifaHorista) this.getTarifa()).getValorPrimeiraHora() * this.getVeiculo().getTipoVeiculo().getPercentualTarifa()) * 2) * tempoEstacionado;
    }

    public double calcularValorDoTempoEstacionado(long tempoEstacionado) {


        double valorPrimeiraHora = ((TarifaHorista) this.getTarifa()).getValorPrimeiraHora() * this.getVeiculo().getTipoVeiculo().getPercentualTarifa();
        double valorHoraSubsequente = ((TarifaHorista) this.getTarifa()).getValorHoraSubsequente() * this.getVeiculo().getTipoVeiculo().getPercentualTarifa();

        valorHoraSubsequente *= (tempoEstacionado - 1); // -1 hora pra descontar o valor da primeira hora

        return valorPrimeiraHora += valorHoraSubsequente;
    }


}
