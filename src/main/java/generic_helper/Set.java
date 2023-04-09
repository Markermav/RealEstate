package generic_helper;

import java.util.ArrayList;
import java.util.List;

public class Set<T extends Identifiable> {

    private List<T> items;

    public Set() {
        items = new ArrayList<>();
    }

    public void addElement(T element) {
        if (!peek(element.getID())) {
            items.add(element);
        }
    }

    public T removeElement(int ID) {
        for (int i = 0; i < items.size(); i++) {
            T element = items.get(i);
            if (element.getID() == ID) {
                return items.remove(i);
            }
        }
        return null;
    }

    public boolean peek(int ID) {
        for (T element : items) {
            if (element.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return items.size();
    }

    public boolean equals(Set<T> other) {
        if (size() != other.size()) {
            return false;
        }
        for (T element : items) {
            if (!other.peek(element.getID())) {
                return false;
            }
        }
        return true;
    }

    public void display() {
        for (T element : items) {
            System.out.println("ID: " + element.getID() + "  "+ "Name :"+ element.getName());
        }
        System.out.println();
    }
}




