package com.project.MyStickyNotes.Converters;


import com.project.MyStickyNotes.Dto.DetailsDto;
import com.project.MyStickyNotes.Dto.RegisterDto;
import com.project.MyStickyNotes.Entities.DetailsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DetailsEntityDtoMapper {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    registerentitydtomapper registerentitydto;


    public DetailsDto detailsEntityTodetailsDto(DetailsEntity detailsEntity)
    {
        DetailsDto detailsDto = modelMapper.map(detailsEntity,DetailsDto.class);

        return  detailsDto;

    }


    public DetailsEntity detailsDtoTodetailsEntity(DetailsDto detailsDto)
    {
        DetailsEntity detailsEntity = modelMapper.map(detailsDto,DetailsEntity.class);



        return detailsEntity;

    }

    public List<DetailsDto> detailsEntityTodetailsDto(List<DetailsEntity> detailsEntity)
    {
        List<DetailsDto> l  =  new ArrayList<DetailsDto>();

        for(DetailsEntity r : detailsEntity){
            DetailsDto detailsDto = modelMapper.map(r,DetailsDto.class);
            
            detailsDto.setRegisterDto(registerentitydto.registerEntityToregisterDto(r.getRegisterEntity()));
            l.add(detailsDto);

        }
        return l;

    }

}
