package com.app.citas.repositories;


import com.app.citas.model.ConsultRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface ConsultRoomRepository  extends JpaRepository<ConsultRoom, Long> {
    ConsultRoom findConsultRoomById(Long id);
    ConsultRoom findConsultRoomByFloorAndName(String floor, String name);
    List<ConsultRoom> findConsultRoomByName(String name);
    List<ConsultRoom> findConsultRoomByFloor(String floor);
}
