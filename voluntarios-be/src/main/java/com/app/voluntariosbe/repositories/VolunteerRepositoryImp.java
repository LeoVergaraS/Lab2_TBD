package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Task;
import com.app.voluntariosbe.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Objects;

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
        String sql = "SELECT * FROM voluntario";
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
        String sql = "SELECT * FROM voluntario WHERE id = :id";
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
        String sql = "INSERT INTO voluntario (id, nombre, fnacimiento) " +
                "VALUES (:id, :nombre, :fnacimiento)";
        Connection conn = sql2o.open();
        try (conn) {
            int id = (int) conn.createQuery(sql,true)
                    .bind(v)
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
        String sql = "UPDATE voluntario SET nombre = :nombre, fnacimiento = :fnacimiento WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .bind(v)
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

}
