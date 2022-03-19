package com.example.chartog.entity;

import java.util.Objects;

public class Papyrus {
    private int width;
    private int height;

    public Papyrus() {
    }

    public Papyrus(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public String toString() {
        return "Figura{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj)return true;
        if(obj==null || getClass() != obj.getClass())return false;
        Papyrus papyrus = (Papyrus) obj;
        return Objects.equals(width,papyrus.width)
                && Objects.equals(height,papyrus.height);
    }
}
