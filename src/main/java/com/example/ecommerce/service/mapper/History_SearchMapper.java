package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.History_Search;
import com.example.ecommerce.service.dto.History_SearchDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class History_SearchMapper {
    ModelMapper modelMapper=new ModelMapper();
    public History_Search toEntity(History_SearchDTO dto){
        if(dto==null){
            return null;
        }
        History_Search h=new History_Search();
        h=modelMapper.map(dto,History_Search.class);
        return h;
    }
    public History_SearchDTO toDto(History_Search entity){
        if(entity==null)
            return null;
        History_SearchDTO h=new History_SearchDTO();
        h=modelMapper.map(entity,History_SearchDTO.class);
        return h;
    }
    public List<History_Search> toEntity(List<History_SearchDTO> lstDTO){
        if(lstDTO ==null)
            return null;
        List<History_Search> h=new ArrayList<>(lstDTO.size());
        for(History_SearchDTO s:lstDTO){
            h.add(toEntity(s));
        }
        return h;
    }
    public List<History_SearchDTO> toDto(List<History_Search> lstEntity){
        if(lstEntity==null)
            return null;
        List<History_SearchDTO> h=new ArrayList<>(lstEntity.size());
        for(History_Search s:lstEntity){
            h.add(toDto(s));
        }
        return h;
    }
}
