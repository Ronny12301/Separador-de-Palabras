package com.ronny.dividirdiccionario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronny12301
 */
public class DividirDiccionario {

    static Map<String, FileWriter> writers = new HashMap<>();

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("/home/ronny12301/Documentos/NetBeansProjects/DividirDiccionario/src/main/java/com/ronny/dividirdiccionario/espanol.txt"))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.length()<=12 && linea.length()>2) {  //wordament solo permite palabras de 3 a 12 letras
                    escribir(linea + "\n");
                }
                else {
                    System.out.println(linea);  //palabras no incluidas por ser muy grandes/chicas
                }
            }

            for (FileWriter writer : writers.values()) {
                writer.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(DividirDiccionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void escribir(String linea) {
        String letraInicial = linea.charAt(0) + "";
        letraInicial = letraInicial.toLowerCase();

        FileWriter writer = writers.get(letraInicial);
        if (writer == null) {
            try {
                writer = new FileWriter(letraInicial);
                writers.put(letraInicial, writer);
            } catch (IOException ex) {
                Logger.getLogger(DividirDiccionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            writer.write(linea);
        } catch (IOException ex) {
            Logger.getLogger(DividirDiccionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
