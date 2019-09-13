/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorafinanceira;

/**
 *
 * @author Fellipe
 */
public class CalculadoraJurosSimples implements CalculadoraFinanceira {

    @Override
    public double calcularMontanteComJuros(double montanteInicial, int periodoMeses, double jurosAoMes) {
        double totalJuros = montanteInicial * periodoMeses
                * (jurosAoMes * 0.01);
        double novoMontante = montanteInicial + totalJuros;
        return novoMontante;
    }

    @Override
    public String toString() {
        return "Calculadora de juros simples";
    }
}
