package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

public class ItemInfo extends TbItem {

    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            return image.split(",");
        }
        return null;
    }
}
