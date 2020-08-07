package cn.tedu.sp04.order.feign;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
@Component
public class ItemFeignClientFB implements ItemFeignClient{
    @Override
    public JsonResult<List<Item>> getItems(String orderId) {
        if(Math.random() < 0.5) {
            ArrayList<Item> items = new ArrayList<>();
            items.add(new Item(1,"asd",2));
            items.add(new Item(2,"4757",54687));
            items.add(new Item(3,"asd",54));
            return JsonResult.ok().data(items);
        }
        return JsonResult.err().msg("获取底单的商品列表失败");
    }

    @Override
    public JsonResult<?> decreaseNumbers(List<Item> items) {
        return JsonResult.err().msg("减少商品库存失败");
    }
}
