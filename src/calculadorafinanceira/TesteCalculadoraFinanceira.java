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
public class TesteCalculadoraFinanceira {

    public static void main(String[] args) {
        double montanteInicial = 10000;
        int periodoMeses = 3;
        double jurosAoMes = 0.05;

        CalculadoraFinanceira calculadora = new CalculadoraJurosCompostos();
        System.out.println("************* Calculo de juros  com  calculadora de juros compostos **********************");
        System.out.println("Montante inicial..: " + montanteInicial);
        System.out.println("Periodo em meses...: " + periodoMeses);
        System.out.println("Juros ao mes......:  " + jurosAoMes);
        System.out.println("Objeto calculadora..: " + calculadora);
        System.out.println("Total..: "
                + calculadora.calcularMontanteComJuros(montanteInicial, periodoMeses, jurosAoMes));

        System.out.println("************************************");

        calculadora = new CalculadoraJurosSimples();
        System.out.println("************* Calculo de juros com calculadora de juros simples  **********************");
        System.out.println("Montante inicial..: " + montanteInicial);
        System.out.println("Periodo em meses...: " + periodoMeses);
        System.out.println("Juros ao mes......:  " + jurosAoMes);
        System.out.println("Objeto calculadora..: " + calculadora);
        System.out.printf("Total..: "
                + calculadora.calcularMontanteComJuros(montanteInicial, periodoMeses, jurosAoMes));

        System.out.println("***********************************");
    }
}
