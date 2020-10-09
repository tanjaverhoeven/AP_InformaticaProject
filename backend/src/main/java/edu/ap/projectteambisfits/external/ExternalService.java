package edu.ap.projectteambisfits.external;

import java.util.List;

public interface ExternalService {
    List<External> findAll();

    External findById(String id);

    void deleteExternal(External external);

    External saveExternal(External external);
}