package com.example.core.utils;

public class StringUtils {

    static public String createdMsg(String entity){
        return "This "+ entity + " is Created Successfully!";
    }
    static public String updateMsg(String entity){
        return "This "+ entity + " is Updated Successfully!";
    }

    static public String deleteMsg(String entity){
        return "This "+ entity + " is Deleted Successfully!";
    }

    static public String removeMsg(String entity){
        return "This "+ entity + " is Removed Successfully!";
    }
}
