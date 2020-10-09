package edu.ap.projectteambisfits.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private ExternalRepository repo;

    @Override
    public List<External> findAll() {
        return repo.findAll();
    }

    @Override
    public External findById(String id) {
        return repo.findById(id).get();
    }

    @Override
    public void deleteExternal(External external) {
        repo.delete(external);

    }

    @Override
    public External saveExternal(External external) {
        return repo.save(external);
    }

}