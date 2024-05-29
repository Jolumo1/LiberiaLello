package Dos;

import java.util.ArrayList;

public abstract class Billete {
    protected static ArrayList<Billete> aforo = new ArrayList<>();
    protected int id; // Campo para el ID del billete

    public Billete(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

