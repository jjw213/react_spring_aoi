package hello.hellospring.repository;

import hello.hellospring.domain.Animal;

import java.util.List;

public interface DibsRepository {
    Animal save(Animal animal);

    List<Animal> findAllByName(String name);
}
