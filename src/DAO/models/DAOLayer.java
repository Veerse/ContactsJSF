package DAO.models;

import java.util.ArrayList;

public interface DAOLayer <T> {

    ArrayList<T> getAll();

    void create(T element);
    T read(int id);
    void update(int id, T element);
    void delete(int id);
    
}
