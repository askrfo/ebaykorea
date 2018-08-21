package examples.boot.mybatis;

import examples.boot.mybatis.dao.CityDao;
import examples.boot.mybatis.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    private final CityDao cityDao;
    @Autowired
    private HotelMapper hotelMapper;

    public MybatisApplication(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------");
        System.out.println(this.cityDao.selectCityById(1));
        System.out.println(hotelMapper.selectByCityId(1));
        System.out.println("---------------------");
    }
}
