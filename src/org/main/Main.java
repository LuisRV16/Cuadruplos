package org.main;

import java.util.Scanner;
import org.cuadruplos.Cuadruplos;
import org.cuadruplos.InfijoPrefijo;

public class Main {
    public static void main(String[] args) {
        String[] encabezadosCol = {"op", "arg1", "arg2", "Resultado"};
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la expresion a evaluar: ");
        String exp = sc.nextLine();
        
        InfijoPrefijo ip = new InfijoPrefijo(exp);
        
        ip.infijoAprefijo();
        
        Cuadruplos cuad = new Cuadruplos(ip.getExpresionSalida());
        
        cuad.resolverCuadruplos();
        
        String[][] cuadruplos = cuad.getMatrizCuadruplos();

        for (String s: encabezadosCol) System.out.printf("%-5s", s);

        System.out.println();

        for (String[] cuadruplo : cuadruplos) {
            for (int j = 0; j < cuadruplos[0].length; j++)
                System.out.printf("%-5s", cuadruplo[j]);
            System.out.println();
        }
    }
}
