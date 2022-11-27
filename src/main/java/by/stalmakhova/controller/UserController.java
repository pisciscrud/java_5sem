package by.stalmakhova.controller;

import by.stalmakhova.dto.RegistrateUser;
import by.stalmakhova.dto.UserWithJwtToken;
import by.stalmakhova.entity.User;
import by.stalmakhova.exception.NonExistedUserException;
import by.stalmakhova.security.JWTUtil;
import by.stalmakhova.services.UserServiceImpl;
import by.stalmakhova.validator.UserValidator;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")

@CrossOrigin("*")
public class UserController {

    //private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserServiceImpl userService, UserValidator userValidator, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        if (id == null)
            return ResponseEntity.badRequest().build();

        var user = userService.getUserById(id);

        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);

    }
//    @GetMapping("/current")
//    public ResponseEntity<UserDto> getUser(){
//       // User user = userService.getUserByLogin(getName());
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWithJwtToken> RegisterUser(@RequestBody @Valid RegistrateUser unauthorizedUser, BindingResult bindingResult) {

        User user = this.modelMapper.map(unauthorizedUser, User.class);

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        userService.register(user);

        String token = jwtUtil.generateToken(user.getLogin());

        var userWithJwtToken = new UserWithJwtToken(user.getLogin(), user.getIdRole(), token);

        return new ResponseEntity<>(userWithJwtToken, HttpStatus.OK);
    }
    @ApiResponse(responseCode = "200", description = "User logged in")
    @ApiResponse(responseCode = "400", description = "Invalid user data")
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserWithJwtToken> loginUser(@RequestBody @Valid RegistrateUser unauthorizedUser) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(unauthorizedUser.getLogin(), unauthorizedUser.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new NonExistedUserException("User with login " + unauthorizedUser.getLogin() + " doesn't exist");
        }

        String token = jwtUtil.generateToken(unauthorizedUser.getLogin());
        Long RoleId = userService.GetRoleId(unauthorizedUser.getLogin());
        var userWithJwtToken = new UserWithJwtToken(unauthorizedUser.getLogin(),RoleId, token);
        return new ResponseEntity<>(userWithJwtToken, HttpStatus.OK);

    }


}
