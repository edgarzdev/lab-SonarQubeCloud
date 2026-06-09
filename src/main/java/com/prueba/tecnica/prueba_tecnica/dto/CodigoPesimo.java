package com.prueba.tecnica.prueba_tecnica.dto;

public class CodigoPesimo {

    public static String nombreGlobal = "";
    public static int contador = 0;
    public static int contador2 = 0;

    public static void main(String[] args) {

        String nombre = null;

        if (nombre == null) {
            System.out.println("Nombre nulo");
        }

        if (nombre == null) {
            System.out.println("Nombre sigue siendo nulo");
        }

        int x = 0;
        int y = 0;
        int z = 0;

        x = 10;
        y = 20;

        if (x == 10) {
            z = x + y;
        } else {
            z = x + y;
        }

        System.out.println(z);

        String password = "123456";

        if (password.equals("123456")) {
            System.out.println("Acceso permitido");
        }

        for (int i = 0; i < 100; i++) {
            contador++;
        }

        metodoGigante();
    }

    public static void metodoGigante() {

        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int e = 5;
        int f = 6;
        int g = 7;
        int h = 8;
        int i = 9;
        int j = 10;

        if (a > 0) {
            if (b > 0) {
                if (c > 0) {
                    if (d > 0) {
                        if (e > 0) {
                            if (f > 0) {
                                if (g > 0) {
                                    if (h > 0) {
                                        if (i > 0) {
                                            if (j > 0) {
                                                System.out.println("Demasiada anidación");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int resultado = a + b + c + d + e + f + g + h + i + j;

        System.out.println(resultado);

        String texto = "";

        texto = texto + "A";
        texto = texto + "B";
        texto = texto + "C";
        texto = texto + "D";
        texto = texto + "E";
        texto = texto + "F";

        System.out.println(texto);
    }
}
