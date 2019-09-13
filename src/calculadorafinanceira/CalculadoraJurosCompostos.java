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
public class CalculadoraJurosCompostos implements CalculadoraFinanceira {

    @Override
    public double calcularMontanteComJuros(double montanteInicial, int periodoMeses, double jurosAoMes) {
        double novoMontante = montanteInicial * Math.pow((1
                + jurosAoMes), periodoMeses);
        return novoMontante;
    }

    @Override
    public String toString() {
        return "Calculadora de juros compostos";
    }
}
