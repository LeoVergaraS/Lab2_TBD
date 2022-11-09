package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Task_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Objects;

@Repository
public class Task_SkillRepositoryImp implements Task_SkillRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countTask_Skill() {
        String sql = "SELECT COUNT(*) FROM tarea_habilidad";
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
    public List<Task_Skill> getAllTask_Skill() {
        String sql = "SELECT * FROM tarea_habilidad";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Task_Skill.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Task_Skill getTask_SkillById(int id) {
        String sql = "SELECT * FROM tarea_habilidad WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Task_Skill.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Task_Skill createTask_Skill(Task_Skill t) {
        String sql = "INSERT INTO tarea_habilidad (id, id_emehab, id_tarea) " +
                "VALUES (:id, :id_emehab, :id_tarea)";
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
    public Task_Skill updateTask_Skill(Task_Skill t) {
        String sql = "UPDATE tarea_habilidad SET id_emehab = :id_emehab, id_tarea = :id_tarea " +
                "WHERE id = :id";
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
    public void deleteTask_SkillById(int id) {
        String sql = "DELETE FROM tarea_habilidad WHERE id = :id";
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
