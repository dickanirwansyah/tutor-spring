package com.dicka.commandpattern.repository;

import com.dicka.commandpattern.entity.Medsos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedsosRepository extends JpaRepository<Medsos, String> {

    /** PERSISTENCE QUERY **/
   @Query("SELECT m FROM Medsos m, PenggunaMedsos pm WHERE " +
           "m.medsosId=pm.pk.medsosId and pm.pk.email=:email")
    List<Medsos> findMedsosByEmail(@Param("email") String email);

    /** PERSISTENCE QUERY
    @Query("DELETE FROM PenggunaMedsos as pm WHERE pm.pk.email=:email AND " +
            "pm.pk.medsosId=:medsosId")
    void deleteByEmail(@Param("email") String email,
                       @Param("medsosId") String medsosId);
                       **/
}
