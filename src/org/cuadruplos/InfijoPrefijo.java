package org.cuadruplos;
import java.util.Stack;

public class InfijoPrefijo {

    private String expresionEntrada;
    private String expresionSalida = "";
    private final Stack<String> operadores = new Stack<>();
    private final Stack<String> salida = new Stack<>();

    public InfijoPrefijo() {}

    public InfijoPrefijo(String expresion) {setExpresionEntrada(expresion);}

    public void setExpresionEntrada(String expresion) {
        this.expresionEntrada = expresion;
        expresionEntrada = expresionEntrada.replace(" ", "");
    }

    public String getExpresionEntrada() {return expresionEntrada;}

    public void infijoAprefijo() {
        String expresion = expresionEntrada;
        String expresion2 = expresion;
        int index = expresion.indexOf("=");

        if (index != -1) {
            expresion2 = expresion.substring(0, index + 1);
            expresion = expresion.substring(index + 1);
        }

        infijoAprefijo(expresion);

        if (!expresion2.equals(expresion)) infijoAprefijo(expresion2);

    }

    private void infijoAprefijo(String expresion) {

        for (int i = expresion.length() - 1; i >= 0; i--) {

            String c = expresion.charAt(i)+"";

            switch (c) {
                case ")", "*", "+", "-", "/", "=" -> {
                    String cAnt;
                    if (!operadores.isEmpty()) {
                        cAnt = operadores.pop();
                        if (menorJerarquia(c, cAnt))
                            salida.add(cAnt);
                        else
                            operadores.push(cAnt);
                    }
                    operadores.push(c);
                }
                case "(" -> {
                    String s = "";
                    while (!s.equals(")")) {
                        salida.push(s);
                        s = operadores.pop();
                    }
                }
                default -> salida.push(c);
            }
        }
        if (!operadores.isEmpty())
            while (!operadores.isEmpty()) salida.push(operadores.pop());
    }

    private boolean menorJerarquia(String c, String cAnt) {
        return (cAnt.equals("*") || cAnt.equals("/")) && (c.equals("+") || c.equals("-"));
    }

    public String getExpresionSalida() {
        while (!salida.isEmpty()) expresionSalida += salida.pop();
        return expresionSalida;
    }
}
