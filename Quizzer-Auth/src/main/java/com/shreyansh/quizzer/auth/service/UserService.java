package com.shreyansh.quizzer.auth.service;

import com.shreyansh.quizzer.auth.dto.UserDto;
import com.shreyansh.quizzer.auth.entity.AuditRecord;
import com.shreyansh.quizzer.auth.entity.OneTimePassword;
import com.shreyansh.quizzer.auth.entity.User;
import com.shreyansh.quizzer.auth.entity.UserRoles;
import com.shreyansh.quizzer.auth.model.AuthResponse;
import com.shreyansh.quizzer.auth.model.JwtToken;
import com.shreyansh.quizzer.auth.model.ResponseHandler;
import com.shreyansh.quizzer.auth.repository.OtpRepository;
import com.shreyansh.quizzer.auth.repository.RoleRepository;
import com.shreyansh.quizzer.auth.repository.UserRepository;
import com.shreyansh.quizzer.auth.util.FileUploadMaster;
import com.shreyansh.quizzer.auth.util.JwtUtil;
import com.shreyansh.quizzer.auth.util.OtpGenerator;
import com.shreyansh.quizzer.auth.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private FileUploadMaster fileUploadMaster;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByIdOrEmail(username);
        if(user == null) throw new UsernameNotFoundException("No User found for " + username);

        return user;
    }

    public AuthResponse login(String userName){
        String tokenVal = jwtUtil.generateToken(userName);

        JwtToken jwtToken = JwtToken.builder()
                .token(tokenVal)
                .isTokenExpired(jwtUtil.isTokenExpired(tokenVal))
                .issuedAt(jwtUtil.extractIssuedAt(tokenVal))
                .validTill(jwtUtil.extractExpiration(tokenVal))
                .build();

        User user = userRepository.findByEmail(userName);

        UserVo userVo = UserVo.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .fullName(user.getFullName())
                .imageUrl(user.getImageUrl())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .authorities(user.getAuthorities())
                .build();

        AuthResponse authResponse = AuthResponse.builder()
                .jwtToken(jwtToken)
                .user(userVo)
                .build();

        return authResponse;
    }

    public void createAccountForAnotherUser(String token, UserDto userDto) {
        User currentUser = jwtUtil.currentLoggedInUser(token);
        User userToCreate = new User();
        userToCreate.setFirstName(userDto.getFirstName());
        userToCreate.setMiddleName(userDto.getMiddleName());
        userToCreate.setLastName(userDto.getLastName());
        userToCreate.setEmail(userDto.getEmail());
        userToCreate.setDateOfBirth(userDto.getDateOfBirth());
        userToCreate.setPassword(userDto.getPassword());
        userToCreate.setMobile(userDto.getMobile());
        userToCreate.setIsLocked(false);
        userToCreate.setActive(true);
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setLastModifiedDateTime(LocalDateTime.now());
        auditRecord.setCreatedDateTime(LocalDateTime.now());
        auditRecord.setCreatedById(currentUser.getUserId());
        auditRecord.setCreatedBy(currentUser.getFullName());
        auditRecord.setLastModifiedBy(currentUser.getFullName());
        auditRecord.setLastModifiedById(currentUser.getUserId());
        userToCreate.setAuditRecord(auditRecord);
        userToCreate.setUserRoles(Set.of(roleRepository.findRoleByRoleName("End User")));
        userRepository.save(userToCreate);

//        EmailRequest emailRequest = EmailRequest.builder()
//                .recipientName(userVO.getFirstName() + " " + userVO.getLastName())
//                .recipientsTo(userVO.getEmail())
//                .senderName("Team Quizzer!!!")
//                .emailContent("Thank you for registering to Quizzer!!! Your account has been successfully created.")
//                .build();

//        emailService.prepareAndSendEmail(emailRequest, null);
    }

    public void createAccount(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassword(userDto.getPassword());
        user.setMobile(userDto.getMobile());
        user.setIsLocked(false);
        user.setActive(true);
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setLastModifiedDateTime(LocalDateTime.now());
        auditRecord.setCreatedDateTime(LocalDateTime.now());
        user.setAuditRecord(auditRecord);
        user.setUserRoles(Set.of(roleRepository.findRoleByRoleName("End User")));
        User u = userRepository.save(user);
        userRepository.updateUserCreatedByLastModBy(u.getUserId(), u.getUserId(), user.getUserId());
    }

    public void setImageUrl(Integer id, MultipartFile multipartFile) {
        userRepository.updateUserImage(fileUploadMaster.uploadFile(multipartFile), id);
    }

    public List<User> findUsersByRole(UserRoles role) {
        return userRepository.findUsersByRole(role);
    }

    public void updateAccount(String token, UserDto userDto) {
        User currentUser = jwtUtil.currentLoggedInUser(token);
        User userToUpdate = userRepository.findById(userDto.getId()).get();

        if (!userDto.getFirstName().isEmpty() && userDto.getFirstName() != null)
            userToUpdate.setFirstName(userDto.getFirstName());
        if (!userDto.getLastName().isEmpty() && userDto.getLastName() != null)
            userToUpdate.setMiddleName(userDto.getMiddleName());
        if (!userDto.getMiddleName().isEmpty() && userDto.getMiddleName() != null)
            userToUpdate.setMiddleName(userDto.getMiddleName());
        if (!userDto.getEmail().isEmpty() && userDto.getEmail() != null) userToUpdate.setEmail(userDto.getEmail());
        if (userDto.getDateOfBirth() != null) userToUpdate.setDateOfBirth(userDto.getDateOfBirth());
        if (!userDto.getPassword().isEmpty() && userDto.getPassword() != null)
            userToUpdate.setPassword(userDto.getPassword());
        // if(!userVO.getMobile().isEmpty() && userVO.getMobile() != null) userToUpdate.setMobile(userVO.getMobile());

        AuditRecord auditRecord = userToUpdate.getAuditRecord();
        auditRecord.setLastModifiedDateTime(LocalDateTime.now());
        auditRecord.setLastModifiedBy(currentUser.getFullName());
        auditRecord.setLastModifiedById(currentUser.getUserId());
        userToUpdate.setAuditRecord(auditRecord);
        userRepository.save(userToUpdate);
    }

    public boolean accountExists(String email) {return userRepository.existsByEmail(email);}

    public User getUserByIdOrEmail(String idEmail) {
        return userRepository.findByUserIdOrEmail(idEmail, idEmail);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getActiveUsers() {return userRepository.findByActive(true);}

    public ResponseEntity<Object> sendOTPEmail(String email) {
        String response = null;
        try {
            if (!accountExists(email))
                return ResponseHandler.generateResponse("Email doesn't belong to a account", HttpStatus.NOT_FOUND, null);
            String otp = OtpGenerator.generateOtp();
            response = emailService.sendOTPEmail(email, otp);
            String userId = userRepository.findUsersIdByEmail(email);
            OneTimePassword password = OneTimePassword.builder()
                    .userId(userId)
                    .expiresOn(LocalDateTime.now().plusMinutes(20))
                    .otp(otp)
                    .build();
            otpRepository.save(password);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
        return ResponseHandler.generateResponse(response, HttpStatus.OK,null);
    }

    public ResponseEntity<?> validateOtp(String email, String otp) {
        String userId = userRepository.findUsersIdByEmail(email);
        OneTimePassword oneTimePassword = otpRepository.findByUserId(userId);
        if(otp.length() == 6)
            if(oneTimePassword.getExpiresOn().isAfter(LocalDateTime.now())){
                deleteCorrespondingRecord(oneTimePassword);
                return ResponseHandler.generateResponse("OTP is validated Successfully", HttpStatus.OK, null);
            } else return ResponseHandler.generateResponse("OTP is Expired", HttpStatus.BAD_REQUEST, null);
         else return ResponseHandler.generateResponse("OTP is not valid", HttpStatus.BAD_REQUEST, null);
    }

    private void deleteCorrespondingRecord(OneTimePassword oneTimePassword) {
        otpRepository.delete(oneTimePassword);
    }

    public ResponseEntity<?> changePassword(String email, String password){
        int i = userRepository.updateUserPassword(password,email);
        if(i == 0) return ResponseHandler.generateResponse("There's an issue while changing your password!!! Please try again after some time",HttpStatus.INTERNAL_SERVER_ERROR,null);
        return ResponseHandler.generateResponse("Password has been changed successfully",HttpStatus.OK,null);
    }
}
