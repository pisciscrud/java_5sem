package by.stalmakhova;

import by.stalmakhova.dto.RegistrateUser;
import by.stalmakhova.entity.User;
import by.stalmakhova.exception.NonExistedUserException;
import by.stalmakhova.repositories.UserRepository;
import by.stalmakhova.security.JWTUtil;
import by.stalmakhova.services.Interfaces.UserService;
import by.stalmakhova.services.UserServiceImpl;
import by.stalmakhova.validator.UserValidator;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class UserServiceTest {

    private final UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
    private final PasswordEncoder passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
    private final ModelMapper modelMapper;
    private final UserValidator userValidator;
    private final JWTUtil jwtUtilMock =  Mockito.mock(JWTUtil.class);
    private final UserService userService;
   RegistrateUser unauthorizedUser = new RegistrateUser();
    User userFromRepository = new User();
    private final AuthenticationManager authenticationManager;
    private final AuthenticationManager authenticationManagerMock = Mockito.mock(AuthenticationManager.class);
    private  BindingResult bindingResult= Mockito.mock(BindingResult.class);

    @Autowired
    public UserServiceTest(ModelMapper modelMapper, UserValidator userValidator, AuthenticationManager authenticationManager) {
        this.modelMapper = modelMapper;
        this.userValidator = userValidator;
        this.authenticationManager = authenticationManager;
        userService= new UserServiceImpl(  passwordEncoderMock, userRepositoryMock);
    }

    @Test
    public void whenNoUserWithCurrentLogin_AndTryToLoginWithIt_ThenIllegalArgumentException() {

        //Arrange.
        Mockito.when(userRepositoryMock.findByLogin(unauthorizedUser.getLogin())).thenReturn(Optional.empty());

        //Act.
        ThrowableAssert.ThrowingCallable callable = () -> userService.login(unauthorizedUser);

        //Assert.
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void whenUserWithCurrentLoginExists_AndTryToLoginWithValidData_ThenReturnsUserWithJwtToken()
    {
        //Arrange.
        var userService= new UserServiceImpl(  passwordEncoderMock, userRepositoryMock);
        String token = jwtUtilMock.generateToken(userFromRepository.getLogin());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(unauthorizedUser.getLogin(), unauthorizedUser.getPassword());

        Mockito.when(userRepositoryMock.findByLogin(unauthorizedUser.getLogin())).thenReturn(Optional.of(userFromRepository));
        Mockito.when(this.authenticationManagerMock.authenticate(authenticationToken)).thenReturn(any());
        Mockito.when(jwtUtilMock.generateToken(userFromRepository.getLogin())).thenReturn(token);
        Mockito.when(passwordEncoderMock.matches(any(),any())).thenReturn(true);

        //Act.
        User User = userService.login(unauthorizedUser);



        //Assert.
        assertThat(User.getLogin()).isEqualTo(null);
    }


    @Test
    public void whenUserWithCurrentLoginExists_AndTryToLoginWithInvalidPassword_ThenIllegalArgumentException()
    {
        //Arrange.
        Mockito.when(userRepositoryMock.findByLogin(unauthorizedUser.getLogin())).thenReturn(Optional.of(userFromRepository));
        Mockito.when(this.authenticationManagerMock.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(NonExistedUserException.class);

        //Act.
        ThrowableAssert.ThrowingCallable callable = () -> userService.login(unauthorizedUser);

        //Assert.
        assertThatThrownBy(callable)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    public void WhenNoUserWithCurrentData_AndTryToRegisterWithIt_ThenNoExceptions()
    {
        //Arrange.
        String token = jwtUtilMock.generateToken(userFromRepository.getLogin());
        Mockito.when(userRepositoryMock.findByLogin(unauthorizedUser.getLogin())).thenReturn(Optional.empty());
        Mockito.when(userRepositoryMock.save(any(User.class))).thenReturn(userFromRepository);
        Mockito.when(jwtUtilMock.generateToken(userFromRepository.getLogin())).thenReturn(token);


        //Act.

         userService.register(userFromRepository);



    }



}
