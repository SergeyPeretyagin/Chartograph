package com.example.chartog.entity;

import java.util.*;

public class Fragment {
    private int x;
    private int y;
    private int width;
    private int height;
    private String path;
    private List<OpenFragment> openFragment;
    /**
     Создал этот лист, чтобы фрагмент имел(знал "Has-a") о своих открытых фрагментах(OpenFragment)
     **/

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

    public Fragment(int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.path = path;
        this.openFragment = new ArrayList<>();
    }

    public Fragment() {
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setOpenFragment(int openX, int openY, int openWidth, int openHeight){
        OpenFragment openFragment = new OpenFragment(openX,openY, openWidth, openHeight);
        this.openFragment.add(openFragment);

    }

    public List<OpenFragment> getOpenFragment() {
        return openFragment;
    }

    @Override
    public String toString() {
        return "Fragment{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fragment fragment = (Fragment) o;
        return x == fragment.x && y == fragment.y
                && width == fragment.width && height == fragment.height
                && Objects.equals(path, fragment.path) && Objects.equals(openFragment, fragment.openFragment);
    }

}
