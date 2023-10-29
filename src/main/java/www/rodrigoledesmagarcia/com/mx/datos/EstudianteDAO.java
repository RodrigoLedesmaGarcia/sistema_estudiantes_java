package www.rodrigoledesmagarcia.com.mx.datos;

import www.rodrigoledesmagarcia.com.mx.dominio.Estudiante;
import static www.rodrigoledesmagarcia.com.mx.conexion.Conexion.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO - Data Access Object
public class EstudianteDAO {


    public List<Estudiante> listarEstudiantes() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch(Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch(Exception e){
                System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());
            }
        }
        return estudiantes;
    }
    //findById
    public boolean buscarEstudiantePorId(Estudiante estudiante) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch(Exception e){
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch(Exception e){
                System.out.println("Ocurrio un error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) " +
                " VALUES(?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error al agregar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch(Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarEstudiante(Estudiante estudiante) throws SQLException {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, " +
                " email=? WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch(Exception e){
            System.out.println("Error al modificar estudiante: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean eliminarEstudiante(Estudiante estudiante) throws SQLException {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch(Exception e){
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch(Exception e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        var estudianteDAO = new EstudianteDAO();

        /*System.out.println("Listdo de estudiantes");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println); */

        /*var estudiante1 = new Estudiante(10);
        System.out.println("Estudiante antes de la busqueda: "+ estudiante1);
        var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante1);
        if ( encontrado)
            System.out.print("Estudiante encontrado: "+estudiante1);
        else {
            System.out.println("No se encontro estudiante: "+estudiante1.getIdEstudiante());
        }
         */


    /*var nuevoEstudiante = new Estudiante("Carlos", "Lara", "55117788", "carlos@mail.com");
    var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
    if(agregado)
      System.out.println("Estudiante agregado: " + nuevoEstudiante);
    else
      System.out.println("No se agregaro el estudiante: " + nuevoEstudiante);

     */


   /* var estudianteModificar = new Estudiante(1, "Rodrigo",
            "Juarez", "55443322", "rodrigo@hotmail.com");
    var modificado = estudianteDAO.modificarEstudiante(estudianteModificar);
    if(modificado)
      System.out.println("Estudiante modificado: " + estudianteModificar);
    else
      System.out.println("No se modifico estudiante: " + estudianteModificar);
   */


        var estudianteEliminar = new Estudiante(1);
        var eliminado = estudianteDAO.eliminarEstudiante(estudianteEliminar);
        if(eliminado)
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        else
            System.out.println("No se elimino estudiante: " + estudianteEliminar);


    }
}
