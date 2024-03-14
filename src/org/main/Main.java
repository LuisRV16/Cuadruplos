package org.main;

import java.util.Scanner;
import org.cuadruplos.Cuadruplos;
import org.cuadruplos.InfijoPrefijo;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la expresion a evaluar: ");
        String exp = sc.nextLine();
        
        InfijoPrefijo ip = new InfijoPrefijo(exp);
        
        ip.infijoAprefijo();
        
        Cuadruplos cuad = new Cuadruplos(ip.getExpresionSalida());
        
        cuad.resolverCuadruplos();
        
        String[][] cuadruplos = cuad.getMatrizCuadruplos();

        String[] encabezadosCol = {"op", "arg1", "arg2", "Resultado"};

        for (String s: encabezadosCol) System.out.printf("%-5s", s);

        System.out.println();

        for (int i = 0; i < cuadruplos.length; i++) {
            for (int j = 0; j < cuadruplos[0].length; j++)
                System.out.printf("%-5s", cuadruplos[i][j]);
            System.out.println();
        }
    }
}
