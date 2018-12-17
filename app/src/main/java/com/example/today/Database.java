package com.example.today;

import android.provider.BaseColumns;

public class Database {
    public static final class CreateDB implements BaseColumns {
        public static final String _TABLENAME1 = "todaytable";


        public static final String _CREATE1 =
                " create table "+_TABLENAME1+" ( "
                        +"_ID integer primary key autoincrement, "
                        +"hour text  , "
                        +"minute text  , "
                        +"memo text);";

    }
}
