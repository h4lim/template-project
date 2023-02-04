package com.template.common.domain.vo;

import java.util.Map;

public class MessageInstance {

    public final Map<String, String> mapEN;
    public final Map<String, String> mapID;

    public MessageInstance(Map<String, String> mapEN, Map<String, String> mapID) {
        this.mapEN = mapEN;
        this.mapID = mapID;
    }


}
