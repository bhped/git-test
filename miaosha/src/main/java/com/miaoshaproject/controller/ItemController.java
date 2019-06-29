package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/item")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;



    @RequestMapping(value = "/create",method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                 @RequestParam(name = "description") String description,
                                 @RequestParam(name = "price") BigDecimal price,
                                 @RequestParam(name = "stock") Integer stock,
                                 @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);
        ItemModel itemModelForReturn  = itemService.createItem(itemModel);

        ItemVO itemVO = convertFromItemModel(itemModelForReturn );
        return CommonReturnType.create(itemVO);
    }



    private ItemVO convertFromItemModel(ItemModel itemModel){
        ItemVO itemVO = new ItemVO();
        if (itemModel == null) {
            return null;
        }
        BeanUtils.copyProperties(itemModel,itemVO);
        if (itemModel.getPromoModel() != null) {
			itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
			itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            
            
        } else {
            itemVO.setPromoStatus(0);
        }
        return itemVO;
    }
    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam("id") Integer id) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = convertFromItemModel(itemModel);
        return CommonReturnType.create(itemVO);
    }
    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem(){
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = convertFromItemModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVOList);
    }
}
