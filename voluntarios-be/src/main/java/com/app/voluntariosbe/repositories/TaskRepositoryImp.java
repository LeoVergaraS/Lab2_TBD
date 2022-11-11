package com.app.voluntariosbe.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.app.voluntariosbe.models.Task;

@Repository
public class TaskRepositoryImp implements TaskRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countTask() {
        String sql = "SELECT COUNT(*) FROM tarea";
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
    public List<Task> getAllTask() {
        String sql = "SELECT * FROM tarea";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Task.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Task getTaskById(int id) {
        String sql = "SELECT * FROM tarea WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Task.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Task createTask(Task t) {
        String sql = "INSERT INTO tarea (id, nombre, descrip, cant_vol_requeridos, " +
                "cant_vol_inscritos, id_emergencia, finicio, ffin, id_estado) " +
                "VALUES (:id, :nombre, :descrip, :cant_vol_requeridos, :cant_vol_inscritos," +
                " :id_emergencia, :finicio, :ffin, :id_estado)";
        Connection conn = sql2o.open();
        try (conn) {
            int id = (int) conn.createQuery(sql,true)
                    .bind(t)
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
    public Task updateTask(Task t) {
        String sql = "UPDATE tarea SET nombre = :nombre, descrip = :descrip, " +
        "cant_vol_requeridos = :cant_vol_requeridos, cant_vol_inscritos = :cant_vol_inscritos, " +
                "id_emergencia = :id_emergencia, finicio = :finicio, ffin = :ffin, " +
                "id_estado = :id_estado WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .bind(t)
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
    public void deleteTaskById(int id) {
        String sql = "DELETE FROM ranking ra WHERE ra.id_tarea = :id; " +
                    "DELETE FROM tarea_habilidad th WHERE th.id_tarea = :id; " +
                    "DELETE FROM tarea ta WHERE ta.id = :id;";
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
    // task from emergency
    public List<Task> getTaskByEmergency(int id) {
        String sql = "SELECT  ta.* FROM tarea ta, emergencia em " + 
                    "WHERE em.id= :id and ta.id_emergencia = em.id " +
                    "ORDER BY id_estado ASC, nombre ASC;";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetch(Task.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }
 
}
