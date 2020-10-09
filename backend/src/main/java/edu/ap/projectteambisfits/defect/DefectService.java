package edu.ap.projectteambisfits.defect;

import java.util.List;

public interface DefectService {

    List<Defect> findAll();

    List<Defect> findByStatus(String status);

    Defect getDefect(String id);

    void deleteDefect(String id);

    Defect saveDefect(Defect defect);

}