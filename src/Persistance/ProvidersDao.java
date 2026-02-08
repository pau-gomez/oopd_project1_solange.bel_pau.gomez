package Persistance;

import Buisness.Entities.Provider;

import java.util.List;

public interface ProvidersDao {
    List<Provider> loadAllProviders();

    Provider getOneProvider(int id);

    void updateFile(List<Provider> providers);

    boolean validateProvidersFile();

}
