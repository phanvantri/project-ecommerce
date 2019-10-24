package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.service.dto.UserDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    ModelMapper modelMapper=new ModelMapper();
    public User toEntity(UserDTO dto){
        if(dto==null)
            return null;
        User u=new User();
        u=modelMapper.map(dto,User.class);
        return u;
    }
    public UserDTO toDto(User entity){
        if(entity==null)
            return null;
        UserDTO u=new UserDTO();
        u=modelMapper.map(entity,UserDTO.class);
        return u;
    }
    public List<User> toEntity(List<UserDTO> lstDto){
        if(lstDto==null)
            return null;
        List<User> u=new ArrayList<>(lstDto.size());
        for(UserDTO s:lstDto){
            u.add(toEntity(s));
        }
        return u;
    }
    public List<UserDTO> toDto(List<User> lstEntity){
        if(lstEntity==null)
            return null;
        List<UserDTO> u=new ArrayList<>(lstEntity.size());
        for(User s:lstEntity){
            u.add(toDto(s));
        }
        return u;
    }
}
