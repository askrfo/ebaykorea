package examples.boot.mybatis.mapper;

import examples.boot.mybatis.domain.Hotel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotelMapper {

    Hotel selectByCityId(long city_id);
}