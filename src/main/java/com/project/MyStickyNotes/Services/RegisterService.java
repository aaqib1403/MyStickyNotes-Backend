package com.project.MyStickyNotes.Services;


import com.project.MyStickyNotes.Converters.registerentitydtomapper;
import com.project.MyStickyNotes.Dto.RegisterDto;
import com.project.MyStickyNotes.Entities.RegisterEntity;
import com.project.MyStickyNotes.Repositories.RegisterUsersRepository;
import com.project.MyStickyNotes.Util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegisterService {

    @Autowired
    registerentitydtomapper registerentitydto;
    @Autowired
    RegisterUsersRepository registerUsersRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public JsonResponse register(RegisterDto registerDto) throws Exception {

        RegisterEntity r = registerentitydto.registerDtoToregisterEntity(registerDto);
        int count = registerUsersRepository.countByUsername(registerDto.getUsername());
        if(count>=1){
            return new JsonResponse("Username already exists", false);
        }
        try{
            r.setPassword(bCryptPasswordEncoder.encode(r.getPassword()));
            registerUsersRepository.save(r);
            return new JsonResponse("Successfully Registered", true);
        }
        catch (Exception e){
            throw new Exception("Entity is null");

        }
    }

    public RegisterEntity fetchUser(String username){


       return registerUsersRepository.findByUsername(username);

    }




}
