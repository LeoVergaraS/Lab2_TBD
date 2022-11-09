package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Objects;

@Repository
public class SkillRepositoryImp implements SkillRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countSkill() {
        String sql = "SELECT COUNT(*) FROM habilidad";
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
    public List<Skill> getAllSkill() {
        String sql = "SELECT * FROM habilidad";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Skill.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Skill getSkillById(int id) {
        String sql = "SELECT * FROM habilidad WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Skill.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Skill createSkill(Skill s) {
        String sql = "INSERT INTO habilidad (id, descrip) " +
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
    public Skill updateSkill(Skill s) {
        String sql = "UPDATE habilidad SET descrip = :descrip WHERE id = :id";
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
    public void deleteSkillById(int id) {
        String sql = "DELETE FROM habilidad WHERE id = :id";
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
    public List<Skill> getSkillByEmergency(int id) {
        String sql = "SELECT * FROM habilidad WHERE id IN (SELECT id_habilidad FROM eme_habilidad WHERE id_emergencia = :id)";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetch(Skill.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

}
