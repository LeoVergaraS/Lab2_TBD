package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergencyRepositoryImp implements EmergencyRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countEmergency() {
        String sql = "SELECT COUNT(*) FROM emergencia";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeScalar(Integer.class);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }finally{
            conn.close();
        }
    }

    @Override
    public List<Emergency> getAllEmergency() {
        String sql = "SELECT id, nombre,descrip,finicio,ffin,id_institucion, st_x(st_astext( geom)) AS longitud, st_y(st_astext(geom)) AS latitud, estado FROM emergencia;";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Emergency.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Emergency getEmergencyById(int id) {
        String sql = "SELECT * FROM emergencia WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Emergency.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Emergency createEmergency(Emergency t) {
        String sql = "INSERT INTO emergencia (id, nombre, descrip, finicio, " +
                "ffin, id_institucion, geom) " +
                "VALUES (:id, :nombre, :descrip, :finicio, :ffin, :id_institucion, ST_GeomFromText(POINT(\":longitud :latitud\"), 4326)), estado = :estado";
                //String point = "POINT("+t.getLongitud()+" "+t.getLatitud()+")";

        Connection conn = sql2o.open();
        try (conn) {
            int id = (int) conn.createQuery(sql,true)
                    .bind(t)
                    //.addParameter("geom", point)
                    .executeUpdate()
                    .getKey();
            t.setId(id);
            return t;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Emergency updateEmergency(Emergency t) {
        String sql = "UPDATE emergencia SET nombre = :nombre, descrip = :descrip, " +
                "finicio = :finicio, ffin = :ffin, id_institucion = :id_institucion , ST_GeomFromText(POINT(\":longitud :latitud\"), 4326)), estado = :estado" +
                "WHERE id = :id";
                //String point = "POINT("+t.getLongitud()+" "+t.getLatitud()+")";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .bind(t)
                    //.addParameter("geom", point)
                    .executeUpdate();
            return t;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public void deleteEmergencyById(int id) {
        String sql = "DELETE FROM emergencia WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            conn.close();
        }
    }
    
}
