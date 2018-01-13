package DAO.models;

import java.util.ArrayList;

public interface DAOLayer_ContactItems <T> {

    ArrayList<T> getAll(int id);
    void create(T element, int id);

}
