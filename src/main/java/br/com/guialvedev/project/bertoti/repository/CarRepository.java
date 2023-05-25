package br.com.guialvedev.project.bertoti.repository;

import br.com.guialvedev.project.bertoti.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {


     @Query("SELECT c FROM Car c WHERE c.placa = ?1")
     public List<Car> findByPlaca(String placa);




}
