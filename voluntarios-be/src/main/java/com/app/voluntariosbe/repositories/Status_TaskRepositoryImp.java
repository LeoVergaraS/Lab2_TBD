package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Status_Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Objects;

@Repository
public class Status_TaskRepositoryImp implements Status_TaskRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countStatus_Task() {
        String sql = "SELECT COUNT(*) FROM estado_tarea";
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
    public List<Status_Task> getAllStatus_Task() {
        String sql = "SELECT * FROM estado_tarea";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Status_Task.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Status_Task getStatus_TaskById(int id) {
        String sql = "SELECT * FROM estado_tarea WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Status_Task.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Status_Task createStatus_Task(Status_Task s) {
        String sql = "INSERT INTO estado_tarea (id, descrip) " +
                "VALUES (:id, :descrip)";
        Connection conn = sql2o.open();
        try (conn) {
            int id = (int) conn.createQuery(sql,true)
                    .bind(s)
                    .executeUpdate()
                    .getKey();
            s.setId(id);
            return s;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Status_Task updateStatus_Task(Status_Task s) {
        String sql = "UPDATE estado_tarea SET descrip = :descrip WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .bind(s)
                    .executeUpdate();
            return s;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }


    @Override
    public void deleteStatus_TaskById(int id) {
        String sql = "DELETE FROM estado_tarea WHERE id = :id";
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
