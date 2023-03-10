package cn.carlsky.nlr.mcsm.System;

import com.alibaba.fastjson2.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class VariableLibrary {
    public class System {
        public static int VERSION = 14;
        public static String VERSION_STR = "Release v3.0";
        public static final Date StartTime = new Date();
    }
    public class Storage {
        public static Boolean UserLoginStatus = false;
        public static String UserName = new String();
        public static String UserLoginToken = new String();
        public static HashMap<String, JSONObject> HashMapServerJSONObject = new HashMap<String, JSONObject>();
        public static HashMap<String, String> HashMapServerOutput = new HashMap<String, String>();
        public static HashMap<String, Process> HashMapServerProcess = new HashMap<String, Process>();
        public static HashMap<String, List> HashMapServerStartCommand = new HashMap<String, List>();
    }
}