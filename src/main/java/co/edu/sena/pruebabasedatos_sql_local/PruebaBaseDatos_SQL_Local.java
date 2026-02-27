package co.edu.sena.pruebabasedatos_sql_local;

import java.sql.*;

public class PruebaBaseDatos_SQL_Local {

    public static void main(String[] args) {
        try {
            int documento;
            String nombres, apellidos, ciudad;

            //Se carga el driver correspondiente a MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            /* Se obtiene una conexión a la base de datos usando el DriverManager
            Nos conectaremos a un servidor SQL en nuestra máquina (localhost)
            y a una base de datos existente llamada concesionario */
            String url = "jdbc:mysql://localhost:3306/concesionario";
            Connection conexion;
            conexion = DriverManager.getConnection(url, "root", "formacion");
            //Se crea una consulta, en este caso para obtener todos los registros de la tabla vendedores
            String sentencia = "SELECT * FROM vendedores;";
            Statement consulta = conexion.createStatement();
            
            //Se crea un ResultSet con los resultados la consulta y se itera sobre el mismo
            ResultSet resultados = consulta.executeQuery(sentencia);
            
            System.out.println("Listado de Vendedores");
            System.out.println("+-------+----------------+---------------+----------------+");
            System.out.println("| doc   | nombres        | apellidos     | ciudad         |");
            System.out.println("+-------+----------------+---------------+----------------+");
            while (resultados.next()) {
                // Lee cada uno de los datos de la tabla
                documento = resultados.getInt("documento_vendedor");
                nombres = resultados.getString("nombres");
                apellidos = resultados.getString("apellidos");
                ciudad = resultados.getString("ciudad");
                // Validaciones para dar formato a la tabla con los resultados
                if(nombres.length()<7){ nombres = nombres + "\t"; }
                if(apellidos.length()<7){ apellidos = apellidos + "\t"; }
                if(ciudad.length()<7){ ciudad = ciudad + "\t"; }
                // Muestra el cuerpo de la tabla con los resultados ordenadas
                System.out.println("| " + documento + "\t| " +
                                   nombres + "\t| " +
                                   apellidos + "\t| " +
                                   ciudad + "\t| ");
                
            }
            System.out.println("+-------+----------------+---------------+----------------+");
            System.out.println("--> Consulta finalizada...");
            conexion.close();
        
        } catch (ClassNotFoundException e) {
            System.out.println("No fue posible cargar el driver.");
        
        } catch (SQLException e) {
            System.out.println("Hubo un error al acceder a la base de datos: " + e.getMessage());
        }
    }
}
