package com.app.voluntariosbe.repositories;

import com.app.voluntariosbe.models.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitutionRepositoryImp implements InstitutionRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countInstitution() {
        String sql = "SELECT COUNT(*) FROM institucion";
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
    public List<Institution> getAllInstitution() {
        String sql = "SELECT * FROM institucion";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).executeAndFetch(Institution.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Institution getInstitutionById(int id) {
        String sql = "SELECT * FROM institucion WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            return conn.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Institution.class);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Institution createInstitution(Institution i) {
        String sql = "INSERT INTO institucion (id, nombre, descrip) " +
                "VALUES (:id, :nombre, :descrip)";
        Connection conn = sql2o.open();
        try (conn) {
            int id = (int) conn.createQuery(sql,true)
                    .bind(i)
                    .executeUpdate()
                    .getKey();
            i.setId(id);
            return i;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public Institution updateInstitution(Institution i) {
        String sql = "UPDATE institucion SET nombre = :nombre, descrip = :descrip WHERE id = :id";
        Connection conn = sql2o.open();
        try (conn) {
            conn.createQuery(sql)
                    .bind(i)
                    .executeUpdate();
            return i;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }finally{
            conn.close();
        }
    }

    @Override
    public void deleteInstitutionById(int id) {
        String sql = "DELETE FROM institucion WHERE id = :id";
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
