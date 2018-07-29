package com.Thorn.dao;

import com.Thorn.model.city;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface cityMapper {
    List<city> findAllCity();

    city findOneCity(int cityId);

    int insertCity(city city);

    int updateCity(city city);

    int deleteCity(int cityId);

}