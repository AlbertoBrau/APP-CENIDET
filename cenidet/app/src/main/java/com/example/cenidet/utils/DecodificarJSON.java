package com.example.cenidet.utils;

import com.example.cenidet.models.ModelJSON;

import java.util.ArrayList;
import java.util.List;

public class DecodificarJSON {

    private List<ModelJSON> mList;

    public List<ModelJSON> decodifica(String json) {
        mList = new ArrayList<ModelJSON>();

        json = json.substring(0, json.length() - 1);

        String arr[] = json.split(",");

        for (int i = 0; i < arr.length; i++) {
            String txt = "";
            for (int j = 0; j < arr[i].length(); j++) {
                if (arr[i].charAt(j) != '"') {
                    txt += arr[i].charAt(j);
                }
            }
            txt = txt.substring(1, txt.length());
            String af[] = txt.split(":");

            mList.add(new ModelJSON(limpiar(af[0]), limpiar(af[1])));
        }

        return mList;
    }

    public String limpiar(String txt) {

        if (txt.charAt(0) == ' ') {
            txt = txt.substring(1,txt.length());
        }

        return txt;
    }

}
