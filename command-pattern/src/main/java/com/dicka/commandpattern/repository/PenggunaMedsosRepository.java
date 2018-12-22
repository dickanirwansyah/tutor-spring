package com.dicka.commandpattern.repository;

import com.dicka.commandpattern.entity.PenggunaMedsos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PenggunaMedsosRepository extends JpaRepository<PenggunaMedsos, PenggunaMedsos> {

    /** DML (Data Manipulation Language ) PERSISTENCE QUERY **/

     @Transactional //--> harus make ini
     @Modifying //--> harus make ini
     @Query("DELETE FROM PenggunaMedsos as pm " +
             "WHERE pm.pk.email=:email AND " +
             "pm.pk.medsosId=:medsosId")
     void deleteByEmail(@Param("email") String email,
                        @Param("medsosId") String medsosId);


}
