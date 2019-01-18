package cn.itrip.service.image;

import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.dao.image.ImageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService{

    @Resource
    private ImageMapper imageMapper;

    @Override
    public List<ItripImage> getImgUrlByMap(Map<String, Object> param) {
        return imageMapper.getImgUrlByMap(param);
    }

    @Override
    public Integer addImgUrl(ItripImage itripImage) {
        return imageMapper.addImgUrl(itripImage);
    }
}
