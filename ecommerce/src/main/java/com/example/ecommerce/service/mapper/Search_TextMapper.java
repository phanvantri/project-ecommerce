package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.Search_Text;
import com.example.ecommerce.service.dto.Search_TextDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Search_TextMapper {
    ModelMapper modelMapper=new ModelMapper();
    public Search_Text toEntity(Search_TextDTO dto){
        if(dto==null)
            return null;
        Search_Text s=new Search_Text();
        s=modelMapper.map(dto,Search_Text.class);
        return s;
    }
    public Search_TextDTO toDto(Search_Text entity){
        if(entity==null)
            return null;
        Search_TextDTO s=new Search_TextDTO();
        s=modelMapper.map(entity,Search_TextDTO.class);
        return s;
    }
    public List<Search_Text> toEntity(List<Search_TextDTO> lstDto){
        if(lstDto==null)
            return null;
        List<Search_Text> ls=new ArrayList<>(lstDto.size());
        for(Search_TextDTO s:lstDto){
            ls.add(toEntity(s));
        }
        return ls;
    }
    public List<Search_TextDTO> toDto(List<Search_Text> lstEntity){
        if(lstEntity==null)
            return null;
        List<Search_TextDTO> ls=new ArrayList<>(lstEntity.size());
        for(Search_Text s:lstEntity){
            ls.add(toDto(s));
        }
        return ls;
    }
}
