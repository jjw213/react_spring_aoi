package hello.hellospring.repository;

import hello.hellospring.domain.Animal;

import java.util.List;

public interface DibsRepository {
    boolean save(Animal animal);

    List<Animal> findAllByName(String name);

    boolean cancel(double desertionNo);
}
