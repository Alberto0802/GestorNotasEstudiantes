import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ManejadorArchivos {
    private static final String ARCHIVO =
            "resources/notas_estudiantes.txt";
    public void añadirEstudiante(Estudiante estudiante) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(estudiante.toString());
            writer.newLine();
            System.out.println("Estudiante añadido correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al añadir el estudiante.");
            e.printStackTrace();
        }
    }

    public void mostrarEstudiantes() {
        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante estudiante : estudiantes) {
                System.out.println(estudiante.getNombre() + " - Nota: " + estudiante.getNota());
            }
        }
    }

    public void buscarEstudiante(String nombre) {
        List<Estudiante> estudiantes = leerEstudiantes();
        boolean encontrado = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Estudiante encontrado: " + estudiante.getNombre() + " - Nota: " + estudiante.getNota());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Estudiante no encontrado.");
        }
    }

    public void calcularMedia() {
        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        double sumaNotas = 0;
        for (Estudiante estudiante : estudiantes) {
            sumaNotas += estudiante.getNota();
        }
        double media = sumaNotas / estudiantes.size();
        System.out.println("La nota media es: " + media);
    }

    private List<Estudiante> leerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                double nota = Double.parseDouble(partes[1]);
                estudiantes.add(new Estudiante(nombre, nota));
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Se creará uno nuevo.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
            e.printStackTrace();
        }
        return estudiantes;
    }

}