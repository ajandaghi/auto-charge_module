package ir.mapsa.autochargemodule.services;


import ir.mapsa.autochargemodule.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import java.util.List;
import java.util.Optional;


public abstract class AbstractService<R extends BaseRepository<E, String>, E> {
    @Autowired
    protected R repository;


    public void add(E e) throws Exception {
        repository.save(e);
    }

    public void update(E e) throws Exception {
        repository.save(e);
    }


    public void deleteById(String walletId) throws Exception {
        repository.deleteById(walletId);
    }

    public Optional<E> findById(String walletId) throws Exception {
        return repository.findById(walletId);
    }

    public List<E> getAll() throws Exception {
        return repository.findAll();
    }

    public List<E> findByExample(E e) {
        return repository.findAll(Example.of(e));
    }
}