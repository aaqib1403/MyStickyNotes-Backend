package com.project.MyStickyNotes.Services;

import com.project.MyStickyNotes.Converters.MyUserDetailsConverter;
import com.project.MyStickyNotes.Converters.registerentitydtomapper;
import com.project.MyStickyNotes.Dto.RegisterDto;
import com.project.MyStickyNotes.Entities.RegisterEntity;
import com.project.MyStickyNotes.Repositories.RegisterUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    RegisterUsersRepository registerUsersRepository;
    @Autowired
    registerentitydtomapper regentitydtoconverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        RegisterDto registerDto = null;

            RegisterEntity registerEntity = registerUsersRepository.findByUsername(username);
            registerDto = regentitydtoconverter.registerEntityToregisterDto(registerEntity);


        if (registerDto != null) {
            return new MyUserDetailsConverter(registerDto);

        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
}
