package com.miaoshaproject.service;

import com.miaoshaproject.dataobject.ItemDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    public Boolean decreaseStock(Integer itemId, Integer amount);

    public ItemModel getItemById(Integer id);

    public List<ItemModel> listItem();

    public ItemModel createItem(ItemModel itemModel) throws BusinessException;

    void increaseSales(Integer id, Integer amount);
}
