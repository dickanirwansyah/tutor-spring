package com.dicka.junittestexample.repository;

import com.dicka.junittestexample.entity.Pengguna;
import com.dicka.junittestexample.exception.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class PenggunaServiceImpl implements PenggunaService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Pengguna> listPengguna() {
        try{
            String jpql = "select p from "+Pengguna.class.getName()+" p";
            Query query = entityManager.createQuery(jpql);
            return query.getResultList();
        }catch (NoResultException io){
            return null;
        }
    }

    @Override
    public SuccessResponse createPengguna(Pengguna pengguna) {
        Pengguna p = Pengguna
                .builder()
                .penggunaId(UUID.randomUUID().toString())
                .nama(pengguna.getNama())
                .alamat(pengguna.getAlamat())
                .email(pengguna.getEmail())
                .goldarah(pengguna.getGoldarah())
                .telepon(pengguna.getTelepon())
                .build();

        this.entityManager.persist(p);
        this.entityManager.flush();

        return new SuccessResponse(200, "success insert", p);
    }

    @Override
    public Pengguna findPenggunaByEmail(String email) {
        try{
            String jpql = "select p from "+Pengguna.class.getName()+
                    " p where p.email = :email";
            Query query = this.entityManager.createQuery(jpql, Pengguna.class);
            query.setParameter("email", email);
            return (Pengguna) query.getSingleResult();
        }catch (NoResultException io){
            return null;
        }
    }

    @Override
    public List<Pengguna> searchPenggunaByNama(String nama) {
        try{
            String jpql = "SELECT p FROM "+Pengguna.class.getName()+
                    " p WHERE p.nama LIKE CONCAT ('%',:nama,'%')";
            Query query = this.entityManager.createQuery(jpql);
            query.setParameter("nama", nama);
            return query.getResultList();
        }catch (NoResultException io){
            return null;
        }
    }

    @Override
    public List<Pengguna> findPenggunaByGoldarah(String goldarah) {
        try{
            String jpql = "select p from "+Pengguna.class.getName()+
                    " p where p.goldarah = :goldarah";
            Query query = this.entityManager.createQuery(jpql);
            query.setParameter("goldarah", goldarah);
            return query.getResultList();
        }catch (NoResultException io){
            return null;
        }
    }
}
