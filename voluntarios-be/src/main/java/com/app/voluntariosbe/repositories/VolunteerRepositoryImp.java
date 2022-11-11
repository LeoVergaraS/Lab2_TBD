package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VolunteerRepositoryImp  implements VolunteerRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countVolunteer() {
        String sql = "SELECT COUNT(*) FROM voluntario";
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
    public List<Volunteer> getAllVolunteer() {
        String sql = "SELECT id, nombre, fnacimiento, st_x(st_astext(geom)) AS longitud, st_y(st_astext(geom)) AS latitud FROM voluntario";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Volunteer.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Volunteer getVolunteerById(int id) {
        String sql = "SELECT id, nombre, fnacimiento, st_x(st_astext(geom)) AS longitud, st_y(st_astext(geom)) AS latitud FROM voluntario WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Volunteer.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }
    
    @Override
    public Volunteer createVolunteer(Volunteer v) {
        String sql = "INSERT INTO voluntario (id, nombre, fnacimiento, geom) " +
                "VALUES (:id, :nombre, :fnacimiento, ST_GeomFromText(:point, 4326))";
        String point = "POINT(" + v.getLongitud() + " " + v.getLatitud() + ")";
        Connection conn = sql2o.open();
        try (conn) {
            int id = (int) conn.createQuery(sql,true)
                    .addParameter("id", v.getId())
                    .addParameter("nombre", v.getNombre())
                    .addParameter("fnacimiento", v.getFnacimiento())
                    .addParameter("point", point)
                    .executeUpdate()
                    .getKey();
            v.setId(id);
            return v;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Volunteer updateVolunteer(Volunteer v) {
        String sql = "UPDATE voluntario SET nombre = :nombre, fnacimiento = :fnacimiento, geom = ST_GeomFromText(:point, 4326)) WHERE id = :id";
        String point = "POINT(" + v.getLongitud() + " " + v.getLatitud() + ")";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .addParameter("id", v.getId())
                    .addParameter("nombre", v.getNombre())
                    .addParameter("fnacimiento", v.getFnacimiento())
                    .addParameter("point", point)
                    .executeUpdate();
            return v;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public void deleteVolunteerById(int id) {
        String sql = "DELETE FROM voluntario WHERE id = :id";
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

    @Override
    public List<Volunteer> getVolunteerByEmergency(int id) {
        String sql = "SELECT vo.id, vo.nombre, vo.fnacimiento, st_x(st_astext(vo.geom)) AS longitud, st_y(st_astext(vo.geom)) AS latitud FROM voluntario vo, (SELECT DISTINCT ra.id_voluntario FROM tarea ta, ranking ra WHERE ta.id_emergencia = :id AND ra.id_tarea = ta.id) t1 WHERE t1.id_voluntario = vo.id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetch(Volunteer.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

}