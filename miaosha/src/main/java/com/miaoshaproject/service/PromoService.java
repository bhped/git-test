package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.PromoModel;

public interface PromoService {
    public PromoModel getPromoByItemId(Integer itemId) throws BusinessException;
}
