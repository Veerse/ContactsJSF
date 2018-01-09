package Beans;

import Model.Item;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class Bean  {

    private List<Item> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<Item>();
    }

    public void add() {
        items.add(new Item());
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public void save() {
        System.out.println("items: " + items);
    }

    public List<Item> getItems() {
        return items;
    }
}
