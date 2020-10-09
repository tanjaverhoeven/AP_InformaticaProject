package edu.ap.projectteambisfits.defect;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefectServiceImpl implements DefectService {

    @Autowired
    private DefectRepository defectRepository;

    @Override
    public List<Defect> findAll() {
        return defectRepository.findAll();
    }

    @Override
    public Defect getDefect(String id) {
        return defectRepository.findById(id).get();
    }

    @Override
    public void deleteDefect(String id) {
        defectRepository.deleteById(id);
    }

    @Override
    public Defect saveDefect(Defect defect) {
        return defectRepository.save(defect);
    }

    @Override
    public List<Defect> findByStatus(String status) {
        return defectRepository.findByStatus(status);
    }
}