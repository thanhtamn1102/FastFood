package com.example.fastfoodpos.utils;

import java.text.DecimalFormat;

public class StringUtils {

    private static final DecimalFormat formatter = new DecimalFormat("###,###,###");
    public static String formatCurrency(double d) {
        return formatter.format(d);
    }

}
