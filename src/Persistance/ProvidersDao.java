package Persistance;

import Buisness.Entities.Provider;

import java.util.List;

public interface ProvidersDao {
    List<Provider> loadAllProviders();
}
