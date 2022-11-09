package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Eme_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Objects;

@Repository
public class Eme_SkillRepositoryImp implements Eme_SkillRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countEme_Skill() {
        String sql = "SELECT COUNT(*) FROM eme_habilidad";
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
    public List<Eme_Skill> getAllEme_Skill() {
        String sql = "SELECT * FROM eme_habilidad";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Eme_Skill.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Eme_Skill getEme_SkillById(int id) {
        String sql = "SELECT * FROM eme_habilidad WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Eme_Skill.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Eme_Skill createEme_Skill(Eme_Skill t) {
        String sql = "INSERT INTO eme_habilidad (id, id_emergencia, id_habilidad) " +
                "VALUES (:id, :id_emergencia, :id_habilidad)";
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
    public Eme_Skill updateEme_Skill(Eme_Skill t) {
        String sql = "UPDATE eme_habilidad SET id_emergencia = :id_emergencia, id_habilidad = :id_habilidad " +
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
    public void deleteEme_SkillById(int id) {
        String sql = "DELETE FROM eme_habilidad WHERE id = :id";
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
