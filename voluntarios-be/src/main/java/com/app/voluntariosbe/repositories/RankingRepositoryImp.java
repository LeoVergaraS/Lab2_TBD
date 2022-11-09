package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Objects;

@Repository
public class RankingRepositoryImp implements RankingRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countRanking() {
        String sql = "SELECT COUNT(*) FROM ranking";
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
    public List<Ranking> getAllRanking() {
        String sql = "SELECT * FROM ranking";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Ranking.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Ranking getRankingById(int id) {
        String sql = "SELECT * FROM ranking WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Ranking.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Ranking createRanking(Ranking r) {
        String sql = "INSERT INTO ranking (id, id_voluntario, id_tarea, puntaje, " +
                "flg_invitado, flg_participa) " +
                "VALUES (:id, :id_voluntario, :id_tarea, :puntaje, :flg_invitado," +
                " :flg_participa)";
        Connection conn = sql2o.open();
        try (conn) {
            int id = (int) conn.createQuery(sql,true)
                    .bind(r)
                    .executeUpdate()
                    .getKey();
            r.setId(id);
            return r;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Ranking updateRanking(Ranking r) {
        String sql = "UPDATE ranking SET id_voluntario = :id_voluntario, " +
                "id_tarea = :id_tarea, puntaje = :puntaje, flg_invitado = :flg_invitado, " +
                "flg_participa = :flg_participa WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .bind(r)
                    .executeUpdate();
            return r;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public void deleteRankingById(int id) {
        String sql = "DELETE FROM ranking WHERE id = :id";
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
