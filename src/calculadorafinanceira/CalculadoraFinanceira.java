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
public interface CalculadoraFinanceira {

    double calcularMontanteComJuros(double montanteInicial, int periodoMeses, double jurosAoMes);

}
