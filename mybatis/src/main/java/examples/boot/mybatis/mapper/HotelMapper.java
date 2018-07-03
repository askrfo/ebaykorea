package examples.boot.mybatis.mapper;

import examples.boot.mybatis.domain.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HotelMapper {

    @Select("select * from hotel where city = #{city_id}")
    Hotel selectByCityId(long city_id);
}