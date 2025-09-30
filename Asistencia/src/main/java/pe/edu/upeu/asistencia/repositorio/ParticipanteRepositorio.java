package pe.edu.upeu.asistencia.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.conexion.ConDB;
import pe.edu.upeu.asistencia.enums.Carrera;
import pe.edu.upeu.asistencia.enums.TipoParticipante;
import pe.edu.upeu.asistencia.modelo.Participante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ParticipanteRepositorio {
   public List<Participante> listaParticipantes =new ArrayList<>();
    Connection con = ConDB.getConexion();
    PreparedStatement pst;
    ResultSet rs;
    String sql;
   public List<Participante> findAll(){
      listaParticipantes=new ArrayList<>();
      try {
         pst=con.prepareStatement("SELECT * FROM participante");
         rs=pst.executeQuery();
         while(rs.next()){
            Participante p=new Participante();
            p.setDni(new SimpleStringProperty(rs.getString("dni")));
            p.setNombre(new SimpleStringProperty(rs.getString("nombre")));
            p.setApellidos(new SimpleStringProperty(rs.getString("apellidos")));
            p.setCarrera(Carrera.valueOf(rs.getString("carrera")));
            p.setTipoParticipante(TipoParticipante.valueOf(rs.getString("tipo_participante")));
            p.setEstado(new SimpleBooleanProperty(rs.getBoolean("estado")));
            listaParticipantes.add(p);

         }
      }
         catch (SQLException e) {
            throw new RuntimeException(e);
         }
      return listaParticipantes;
   }
   public void save(Participante p){
      sql ="INSERT INTO participante\n" +
              "(dni, nombre, apellidos, carrera, tipo_participante, estado)\n" +
              "VALUES(?,?,?,?,?,?) ";
      int i=0;

      try {
         pst=con.prepareStatement(sql);
         pst.setString(++i,p.getDni().getValue());
         pst.setString(++i,p.getNombre().getValue());
         pst.setString(++i,p.getApellidos().getValue());
         pst.setString(++i,p.getCarrera().name());
         pst.setString(++i,p.getTipoParticipante().name());
         pst.setBoolean(++i,p.getEstado().getValue());
         pst.executeUpdate();

      }  catch (SQLException e){
         throw new RuntimeException(e);
      }
   }
   public Participante update(Participante p){
      sql="UPDATE participante\n" +
              "SET nombre=?, apellidos=?, carrera=?, tipo_participante=?, estado=?\n" +
              "WHERE dni=?";
      int i=0;
      try {
         pst=con.prepareStatement(sql);
         pst.setString(++i,p.getNombre().getValue());
         pst.setString(++i,p.getApellidos().getValue());
         pst.setString(++i,p.getCarrera().name());
         pst.setString(++i,p.getTipoParticipante().name());
         pst.setBoolean(++i,p.getEstado().getValue());
         pst.setString(++i,p.getDni().getValue());
         pst.executeUpdate();

      }catch (SQLException e){
         throw new RuntimeException(e);
      }
      return p;
   }
   public void delete(String dni){
      try {
         pst=con.prepareStatement("DELETE FROM participante WHERE dni=?");
         pst.setString(1, dni);
         pst.executeUpdate();
      }catch (SQLException e){
         throw new RuntimeException(e);
      }
   }
}
