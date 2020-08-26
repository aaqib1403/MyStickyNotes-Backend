package com.project.MyStickyNotes.Converters;


import com.project.MyStickyNotes.Dto.RegisterDto;
import com.project.MyStickyNotes.Entities.DetailsEntity;
import com.project.MyStickyNotes.Entities.RegisterEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class registerentitydtomapper {


    @Autowired
    ModelMapper modelMapper;



    public RegisterDto registerEntityToregisterDto(RegisterEntity registerEntity)
    {
        RegisterDto registerDto = modelMapper.map(registerEntity,RegisterDto.class);

        return  registerDto;

    }


    public RegisterEntity registerDtoToregisterEntity(RegisterDto registerDto)
    {
        RegisterEntity registerEntity = modelMapper.map(registerDto,RegisterEntity.class);

        return registerEntity;

    }
    //list of register entity to convert to list of details entity

    public List<DetailsEntity> registerEntityToregisterDto(List<RegisterEntity> registerEntity)
    {
         List<DetailsEntity> l  =  new ArrayList<DetailsEntity>();

        for(RegisterEntity r : registerEntity){
            DetailsEntity detailsEntity = modelMapper.map(r,DetailsEntity.class);

        l.add(detailsEntity);

    }
        return l;

}





}
