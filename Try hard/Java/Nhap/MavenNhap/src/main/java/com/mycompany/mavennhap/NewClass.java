package com.mycompany.mavennhap;

import java.text.Normalizer;

public class NewClass {

    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc="ví dụ 1">
//        String str = "   Hello World!!";
//        System.out.println(StringUtils.trimWhitespace(str)); // => "Hello World!!"
//        System.out.println(StringUtils.trimLeadingCharacter(str, ' ')); // => "Hello World!!"
//        System.out.println(StringUtils.trimTrailingCharacter(str, '!')); // => "  Hello World" (chỉ cắt dấu ở cuối string)
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ví dụ 2">
//        int num = 1;
//        String paddingStr = String.format("%03d", num); // => "001"
//        String suppressStr = paddingStr.replaceFirst("^0+(?!$)", ""); // => "1"
//        System.out.println(paddingStr);
//        System.out.println(suppressStr);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ví dụ 3">
//        String str = "吉田太郎";
//        int len = str.length(); // => 5
//        System.out.println(len);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="ví dụ 4">
//        String str = "吉田太郎";
//        int lenOfChar = str.length(); // => 5
//        int lenOfCodePoint = str.codePointCount(0, lenOfChar); // => 4
//        System.out.println(lenOfChar);
//        System.out.println(lenOfCodePoint);
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="ví dụ 5">
//        String str = "吉田太郎";
//        int startIndex = 0;
//        int endIndex = 2;
//        String subStr = str.substring(startIndex, endIndex);
//        System.out.println(subStr); // => "吉田"
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="ví dụ 6">
//        String str = "吉田太郎";
//        int startIndex = 0;
//        int endIndex = 2;
//        int startIndexSurrogate = str.offsetByCodePoints(0, startIndex); // => 0
//        int endIndexSurrogate = str.offsetByCodePoints(0, endIndex); // => 3
//        String subStrSurrogate = str.substring(startIndexSurrogate, endIndexSurrogate); // => "吉田"
//        System.out.println(startIndexSurrogate);
//        System.out.println(endIndexSurrogate);
//        System.out.println(subStrSurrogate);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="ví dụ 7">
//        String str = "吉田 太郎";
//        String[] x = str.split(" "); // => {"吉田", "太郎"}
//        for (int i = 0; i < x.length; i++) {
//            System.out.println(x[i]);
//        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="note ví dụ 7">
//        String str = "ABC";
//        String[] elems = str.split("");
//        for (int i = 0; i < elems.length; i++) {
//            System.out.println(elems[i]);
//        }
//        // Java SE 7 => {, A, B, C}
//        // Java SE 8 => {A, B, C}
        //</editor-fold>
            
    }

    public int getStrLength(String str) {
        String normalizedStr = Normalizer.normalize(str, Normalizer.Form.NFC);
        int length = normalizedStr.codePointCount(0, normalizedStr.length());

        return length;
    }
}
