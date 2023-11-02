package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Role;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.AccountRepository;
import com.example.eindopdracht.repositories.RoleRepository;
import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    private final AccountRepository accountRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
//        this.accountRepository = accountRepository;
    }

    public UserDto getUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User u = user.get();
            UserDto uDto = new UserDto();
            userToUserDto(u, uDto);
            return (uDto);
        } else {
            throw new IdNotFoundException("User not found with ID: " + userId);
        }
    }

    // TODO: Wil ik hier wel alle users op kunnen vragen?
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User u : users) {
            UserDto uDto = new UserDto();
            userToUserDto(u, uDto);

            userDtos.add(uDto);
        }
        return userDtos;
    }

    public String createUser(UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        List<Role> userRoles = newUser.getRoles();
        for (String rolename : userDto.getRoles()) {
            Optional<Role> or = roleRepository.findById("ROLE_" + rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }
        }
        userRepository.save(newUser);

        return "User successfully created";
    }


    // TODO: CONTINUE WITH OVERZETTEN INFO NAAR ACCOUNT EN USER
//    public UserDto createUserWithAccount(AccountDto accountDto) {
//
//        // User gedeelte van de AccountDTO
//        UserDto userDto = new UserDto();
//        userDto.setUsername(accountDto.getUsername());
//        userDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//
//        User user = new User();
//        if (accountDto.getRoles() != null) {
//            List<Role> userRoles = new ArrayList<>();
//            for (String rolename : accountDto.getRoles()) {
//                Optional<Role> or = roleRepository.findById("ROLE_" + rolename);
//                if (or.isPresent()) {
//                    userRoles.add(or.get());
//                }
//            }
//
//            // Aanmaken User
//            userDtoToUser(user, userDto);
//            user.setRoles(userRoles);
//        }
//        // Aanmaken Profile
//        Account account = new Account();
//        accountDtoToAccount(accountDto, account);
//
//        // OneToOne relatie tussen User en Account
//        user.setAccount(account);
//
//        // Beide opslaan in Repository
//        accountRepository.save(account);
//        userRepository.save(user);
//
//        // User -> UserDTO om terug te geven naar de controller
//        UserDto savedUserDto = new UserDto();
//        userToUserDto(user, savedUserDto);
//
//        return savedUserDto;
//    }
//
//    private void accountDtoToAccount(AccountDto aDto, Account a) {
//        a.setUsername(aDto.getUsername()); // TODO: TOEVOEGEN AAN ACCOUNT ALLES, DTO, SERVICE, CONTROLLER ETC
//        a.setPassword(aDto.getPassword()); // TODO: TOEVOEGEN AAN ACCOUNT ALLES, DTO, SERVICE, CONTROLLER ETC
//        a.setRole
//        a.setFirstname(aDto.getFirstname());
//        a.setLastname(aDto.getLastname());
//        a.setPhonenumber(aDto.getPhonenumber());
//        a.setEmailaddress(aDto.getEmailaddress());
//    }


    private static void userToUserDto(User u, UserDto uDto) {
        uDto.setUsername(u.getUsername());
        uDto.setPassword(u.getPassword());
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : u.getRoles()){
            roles.add(role.getRolename());
        }
        uDto.setRoles(roles.toArray(new String [0]));
    }
    private static void userDtoToUser(User u, UserDto uDto) {
        u.setUsername(uDto.getUsername());
        u.setPassword(uDto.getPassword());
    }
}







