package com.dicka.jpaentitymanager.service;

import com.dicka.jpaentitymanager.entity.Pengguna;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Repository
@Transactional
public class PenggunaServiceImpl implements PenggunaService {

    private static final Logger log = LoggerFactory.getLogger(PenggunaServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Pengguna> findPenggunaByGoldarah(String goldarah) {
        try{
            log.info("service : //findPenggunaByGoldarah()");
            String jpql = "select p from "+Pengguna.class.getName()+
                    " p where p.goldarah =:goldarah";
            Query query = this.entityManager.createQuery(jpql);
            query.setParameter("goldarah", goldarah);
            return query.getResultList();
        }catch (NoResultException io){
            return null;
        }
    }

    @Override
    public List<Pengguna> findAllPengguna() {
        try{
            log.info("service : //findAllPengguna()");
            String jpql = "select p from "+Pengguna.class.getName()+
                    " as p";
            Query query = this.entityManager.createQuery(jpql);
            return query.getResultList();
        }catch (NoResultException io){
            return null;
        }
    }

    @Override
    public Pengguna createPengguna(Pengguna pengguna) {
        pengguna.setPenggunaId(UUID.randomUUID().toString());
        Pengguna data = new Pengguna();
        data.setPenggunaId(UUID.randomUUID().toString());
        data.setGoldarah(pengguna.getGoldarah());
        data.setTelepon(pengguna.getTelepon());
        data.setEmail(pengguna.getEmail());
        data.setAlamat(pengguna.getAlamat());
        data.setNama(pengguna.getNama());
        this.entityManager.persist(data);
        this.entityManager.flush();
        return pengguna;
    }

    public Pengguna findByEmail(String email){
        try{
            String jpql = "select p from "+Pengguna.class.getName()+
                    " p where p.email = :email";
            Query query = entityManager.createQuery(jpql, Pengguna.class);
            query.setParameter("email", email);
            return (Pengguna) query.getSingleResult();
        }catch (NoResultException io){
            return null;
        }
    }
}
