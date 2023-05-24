package br.com.guialvedev.project.bertoti.repository;

import br.com.guialvedev.project.bertoti.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
