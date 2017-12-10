package com.example.adds6.verite.List;

/**
 * Created by adds6 on 2017-12-10.
 */

public class ColumnInfoStruct {

    // 칼럼 정보를 담는 변수들
    public static int Column_ID;
    public static int Column_Agency;
    public static String Column_TItle;
    public static String Column_Image;
    public static String Column_Date;
    public static int Column_TrueNum;
    public static int Column_FalseNum;
    public static int Column_Theme;
    public static int Column_ViewNum;
    public static String Column_URL;
    public static String Column_Writer;

    public ColumnInfoStruct(int a, int b, String c, String d, String e, int f, int g, int h, int i, String j, String k){

        this.Column_ID = a;
        this.Column_Agency = b;
        this.Column_TItle = c;
        this.Column_Image = d;
        this.Column_Date = e;
        this.Column_TrueNum = f;
        this.Column_FalseNum = g;
        this.Column_Theme = h;
        this.Column_ViewNum = i;
        this.Column_URL = j;
        this.Column_Writer = k;
    }
}
