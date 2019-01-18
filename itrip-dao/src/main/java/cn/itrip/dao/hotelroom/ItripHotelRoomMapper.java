package cn.itrip.dao.hotelroom;

import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelRoomMapper {
    public abstract ItripHotelRoom getItripHotelRoomById(@Param("id") Long paramLong)
            throws Exception;

    public abstract List<ItripHotelRoomVO> getItripHotelRoomListByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer getItripHotelRoomCountByMap(Map<String, Object> paramMap)
            throws Exception;

    public abstract Integer insertItripHotelRoom(ItripHotelRoom paramItripHotelRoom)
            throws Exception;

    public abstract Integer updateItripHotelRoom(ItripHotelRoom paramItripHotelRoom)
            throws Exception;

    public abstract Integer deleteItripHotelRoomById(@Param("id") Long paramLong)
            throws Exception;
}
