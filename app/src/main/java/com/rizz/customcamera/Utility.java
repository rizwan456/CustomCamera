package com.rizz.customcamera;

import com.rizz.customcamera.model.Filter;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static List<Filter> generateFilter() {
        List<Filter> list = new ArrayList<>();
        list.add(new Filter(1, "Normal"));
        list.add(new Filter(2, "H1"));
        list.add(new Filter(3, "O2"));
        list.add(new Filter(4, "B3"));
        list.add(new Filter(5, "H2"));
        list.add(new Filter(6, "J6"));
        list.add(new Filter(7, "R1"));
        list.add(new Filter(8, "j9"));
        list.add(new Filter(9, "D1"));
        return list;
    }
}
