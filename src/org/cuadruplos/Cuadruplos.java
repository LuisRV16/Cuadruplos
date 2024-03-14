package org.cuadruplos;

import java.util.ArrayList;

public class Cuadruplos {

    private int contRes = 1;
    private int index;
    private String expresion;
    private String arg1;
    private String arg2;
    private final ArrayList<String> operadores = new ArrayList<>();
    private final ArrayList<String> argumentos1 = new ArrayList<>();
    private final ArrayList<String> argumentos2 = new ArrayList<>();
    private final ArrayList<String> resultados = new ArrayList<>();
    private String[][] matrizCuadruplos;

    public Cuadruplos(String expresion) {setExpresion(expresion);}

    public void setExpresion(String expresion) {this.expresion = expresion;} // Recibe una expresion prefija

    public void resolverCuadruplos() {
        char c;
        int i = 0;

        while (i < expresion.length()) {

            c = expresion.charAt(i);

            if (esOperador(c)) {

                arg1 = expresion.charAt(i + 1) + "";
                index = 0;

                if(esOperando(arg1)) {

                    if (arg1.equals("T")) {

                        index = expresion.indexOf("T") + 1;
                        arg1 = getVariable(arg1);
                        arg2 = expresion.charAt(index) + "";

                        if (esOperando(arg2)) {
                            if (arg2.equals("T")) {
                                index++;
                                arg2 = getVariable(arg2);
                            }
                            ingresoDeTupla(c);
                        }

                    } else {

                        arg2 = expresion.charAt(i + 2) + "";

                        if (esOperando(arg2)) {
                            if (arg2.equals("T")) {
                                index = expresion.indexOf("T") + 1;
                                arg2 = getVariable(arg2);
                            }
                            ingresoDeTupla(c);
                        }
                    }
                }
            }

            if (i == expresion.length() - 1 && contieneOperadores(expresion)) i = 0;
            else i++;

        }
        llenarMatrizCuadruplos();
    }

    private void llenarMatrizCuadruplos() {
        matrizCuadruplos = new String[resultados.size()][4];

        for (int j = 0; j < matrizCuadruplos[0].length; j++) {
            for (int k = 0; k < matrizCuadruplos.length; k++) {
                switch (j) {
                    case 0 -> matrizCuadruplos[k][j] = operadores.get(k);
                    case 1 -> matrizCuadruplos[k][j] = argumentos1.get(k);
                    case 2 -> matrizCuadruplos[k][j] = argumentos2.get(k);
                    case 3 -> matrizCuadruplos[k][j] = resultados.get(k);
                }
            }
        }
    }

    private String getVariable(String arg) {
        char temp = expresion.charAt(index);

        StringBuilder argBuilder = new StringBuilder(arg);
        while(esNumero(temp + "")) {
            argBuilder.append(temp);
            if (index != expresion.length() - 1)
                index++;
            else
                break;
            temp = expresion.charAt(index);
        }
        arg = argBuilder.toString();

        return arg;
    }
    private void ingresoDeTupla(char c) {
        operadores.add(c + "");
        if (c != '=') {
            argumentos1.add(arg1);
            argumentos2.add(arg2);
            resultados.add("T" + contRes);

            expresion = expresion.replace(c + arg1 + arg2, "T" + contRes);
        } else {
            argumentos1.add(arg2);
            argumentos2.add("");
            resultados.add(arg1);

            expresion = arg1;
        }
        contRes++;
    }
    public String[][] getMatrizCuadruplos() {return matrizCuadruplos;}

    private boolean esOperador(char c) {return c == '+' || c == '-' || c == '*' || c == '/' || c == '=';}

    private boolean esOperando(String s) {
        boolean val = false;
        if (esNumero(s)) return true;

        String letra;

        for (int i = 65; i <= 90; i++) {
            letra = String.valueOf((char) i);
            if (s.equals(letra)) return true;
        }
        for (int i = 97; i <= 122; i++) {
            letra = String.valueOf((char) i);
            if (s.equals(letra)) return true;
        }

        return val;
    }

    private boolean esNumero(String s) {
        boolean val = false;
        for (int i = 0; i < 10; i++) if (s.equals(String.valueOf(i)))  return true;
        return val;
    }

    private boolean contieneOperadores(String expresion) {
        return expresion.contains("=") || expresion.contains("+") || expresion.contains("-") || expresion.contains("*")
                || expresion.contains("/");
    }

}
