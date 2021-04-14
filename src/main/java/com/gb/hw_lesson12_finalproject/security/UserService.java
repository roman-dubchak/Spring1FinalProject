package com.gb.hw_lesson12_finalproject.security;

import com.gb.hw_lesson12_finalproject.entities.Person;
import com.gb.hw_lesson12_finalproject.entities.Role;
import com.gb.hw_lesson12_finalproject.repo.PersonRepo;
import com.gb.hw_lesson12_finalproject.repo.RoleRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final RoleRepo roleRepo;
    private final PersonRepo personRepo;

    public UserService(RoleRepo roleRepo, PersonRepo personRepo) {
        this.roleRepo = roleRepo;
        this.personRepo = personRepo;
    }

//    public Person findByFirstname(String firstname){
//        return personRepo.findOneByFirstname(firstname);
//    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Person person = personRepo.findOneByFirstname(firstname);
        Optional<Person> personOptional = personRepo.findByLogin(login);
        if (personOptional.isEmpty()) throw new UsernameNotFoundException("Invalid username");

        Person person = personOptional.get();
        System.out.println("Нашли персона по логину" + person);
        System.out.printf("У персона логин = %s, пароль = %s, роль = %s.",
                person.getLogin(), person.getPassword(), person.getRole());
        return new User(person.getLogin(), person.getPassword(), mapRolesToAuthorities(person));
    }

    private Collection<? extends GrantedAuthority>
            mapRolesToAuthorities (Person person) { // вариант с ManyToMany (Collection<Role> roleCollection){
//        return roleCollection.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
        return List.of(new SimpleGrantedAuthority(person.getRole().getName()));
    }
}
