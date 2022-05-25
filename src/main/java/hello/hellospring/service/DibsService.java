package hello.hellospring.service;

import hello.hellospring.domain.Animal;
import hello.hellospring.repository.DibsRepository;
import hello.hellospring.repository.MemberRepsitory;

import java.util.List;

public class DibsService {
    private final DibsRepository dibsRepository;

    public DibsService(DibsRepository dibsRepository) {
        this.dibsRepository = dibsRepository;
    }

    public boolean dibs(Animal animal){
        return dibsRepository.save(animal);
    }

    public List<Animal> findDibs(String name) {return dibsRepository.findAllByName(name);}

    public boolean cancelDibs(int id){return dibsRepository.cancel(id);}
}
