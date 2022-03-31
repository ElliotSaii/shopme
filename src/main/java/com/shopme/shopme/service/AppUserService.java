package com.shopme.shopme.service;

import com.shopme.shopme.dto.ResponseDto;
import com.shopme.shopme.dto.user.LoginDto;
import com.shopme.shopme.dto.user.LoginResponseDto;
import com.shopme.shopme.dto.user.RegisterationDto;
import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.AuthenticationToken;
import com.shopme.shopme.exception.AuthenticationExecption;
import com.shopme.shopme.exception.CustomError;
import com.shopme.shopme.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.rmi.ConnectIOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    AppUser appUser;

    @Transactional(rollbackFor = Exception.class)
    public ResponseDto SignUp(RegisterationDto registeration) {
        ResponseDto responseDto = new ResponseDto("success", "New user registered successful");
        // check user already exits

        AppUser userByEmail = appUserRepository.findUserByEmail(registeration.getEmail());

        if (Objects.nonNull(userByEmail)) {
            throw new CustomError("User already exits");
        }


        // hash the password
        String encryptedPass = registeration.getPassword();
        try {
            encryptedPass = hashPassword(registeration.getPassword());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // save the user
        AppUser user = new AppUser(registeration.getFirstName(), registeration.getLastName(), registeration.getEmail(), encryptedPass);
        appUserRepository.save(user);

        // create token
        AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationTokenService.saveComfirmationToken(authenticationToken);

        return responseDto;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase(Locale.ROOT);
        return hash;
    }

    public LoginResponseDto Login(LoginDto loginDto) {
        LoginResponseDto loginResponseDto= new LoginResponseDto();
        // Check user exits or not
        AppUser user = appUserRepository.findUserByEmail(loginDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationExecption("User not exits");
        }
        AuthenticationToken token = authenticationTokenService.getToken(user);
        // retrieve token
        if (Objects.isNull(token)) {
            throw new CustomError("token is not exits");
        }
        // compare to hash password
        try {
            if (!user.getPassword().equals(hashPassword(loginDto.getPassword())) || Objects.isNull(token)) {
                throw new AuthenticationExecption("password or email not exits");

            }
           else if(user.getPassword().equals(hashPassword(loginDto.getPassword())) && Objects.nonNull(user))
            {
                loginResponseDto = new LoginResponseDto("success",token.getToken());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return loginResponseDto;
    }
}
