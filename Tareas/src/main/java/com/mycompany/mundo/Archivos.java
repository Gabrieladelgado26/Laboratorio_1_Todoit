
package com.mycompany.mundo;

import com.mycompany.mundo.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;

public class Archivos {

    public static void escribirArchivo(ArrayList <Usuario> misUsuarios, ServletContext context) throws FileNotFoundException
    {
        // Ruta relativa y absoluta del archivo de datos serializados
        String rutaRelativa = "/data/usuariosAgregados.txt";
        String rutaAbsoluta = context.getRealPath(rutaRelativa);
        File archivo = new File(rutaAbsoluta);

        try (PrintWriter pluma = new PrintWriter(archivo)) {
            // Ciclo for que recorre los datos del arrayList llamado misUsuarios
            for (Usuario usuario: misUsuarios)
            {
                    String cadenaCaracteres = usuario.getCedula() + "," + usuario.getNombre() + "," + usuario.getContrasenia();
                    pluma.write(cadenaCaracteres);
                    pluma.write("\n");
            }
            pluma.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }   
}
