package GDSCSCH.cherry.domain.user.service;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.admin.domain.RefreshToken;
import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.admin.domain.repository.RefreshTokenRepository;
import GDSCSCH.cherry.domain.admin.presentation.dto.response.AdminSignInResponse;
import GDSCSCH.cherry.domain.user.presentation.dto.request.UserSignUp;
import GDSCSCH.cherry.domain.user.presentation.dto.response.*;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
import GDSCSCH.cherry.domain.siteInfo.exception.SiteInfoNotFoundException;
import GDSCSCH.cherry.domain.user.domain.User;
import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
import GDSCSCH.cherry.domain.user.presentation.dto.request.ChangeUserInfoRequest;
import GDSCSCH.cherry.global.exception.AdminNotFoundException;
import GDSCSCH.cherry.global.exception.UserNotFoundException;
import GDSCSCH.cherry.global.security.jwt.JwtTokenProvider;
import GDSCSCH.cherry.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static GDSCSCH.cherry.domain.admin.domain.Role.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final SiteInfoRepository siteInfoRepository;
    private final UserUtils userUtils;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    //로그인
    @Transactional
    public UserSignInResponse signIn (String email, HttpServletResponse response) {

        User user = findUser(email);
        createToken(email, response);
        log.info(user.getUserEmail() + " (id : " + user.getId() + ") login");

        return new UserSignInResponse(user.getUserInfo());
    }

    //회원가입 및 로그인
    @Transactional
    public Long signUp (String email, UserSignUp signUp, HttpServletResponse response) {

        if(userRepository.findByUserEmail(email).isEmpty()) {

            User user = User.createUser(
                    signUp.getUserName(),
                    email,
                    signUp.getUserPhoneNum(),
                    signUp.getUserAge()
            );

            userRepository.save(user);

            // 어세스, 리프레시 토큰 발급 및 헤더 설정
            createToken(email, response);
            log.info(user.getUserEmail() + " (id : " + user.getId() + ") login");
            return user.getId();
        }
        return null;
    }

    // 로그아웃
    @Transactional
    public boolean logout(String refreshToken) {
        if (!refreshTokenRepository.existsByRefreshToken(refreshToken))
            return false;

        refreshTokenRepository.deleteByRefreshToken(refreshToken);

        return true;
    }

    //유저 본인 현장 정보 조회
    public Long getSiteInfo(String email) {
        User user = userRepository.findByUserEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return user.getSiteInfo().getId();
    }

    //유저 개인정보 수정
    @Transactional
    public void changeUserInfo(ChangeUserInfoRequest changeUserInfo) {
        User user = userUtils.getUserFromSecurityContext();

        user.changeUserInfo(
                changeUserInfo.getUserName(),
                changeUserInfo.getUserPhoneNum(),
                changeUserInfo.getUserAge()
        );
    }

    //근로자 삭제
    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId);

        SiteInfo site = siteInfoRepository.findById(user.getSiteInfo().getId()).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);
        site.subUser(user);
        user.changeRole(GUEST);
    }

    //유저 개인정보 조회
    public UserProfileResponse getProfile(Long userId) {
        User user = findUserById(userId);

        return new UserProfileResponse(user.getUserInfo());
    }

    //안전모 쓴 근로자 리스트 확인
    public List<UserInfoResponse> getTrueHelmetUserList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        List<User> staffTrueHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheckAndRole(site, true, STAFF);
        List<User> userTrueHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheckAndRole(site, true, USER);
        List<UserInfoResponse> staffTrue = staffTrueHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
        List<UserInfoResponse> userTrue = userTrueHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
        List<UserInfoResponse> checked = new ArrayList<>();
        for (UserInfoResponse u : staffTrue) checked.add(u);
        for (UserInfoResponse u : userTrue) checked.add(u);

        return checked;
    }

    //안전모 안 쓴 근로자 리스트 확인
    public List<UserInfoResponse> getFalseHelmetUserList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        List<User> staffFalseHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheckAndRole(site, false, STAFF);
        List<User> userFalseHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheckAndRole(site, false, USER);
        List<UserInfoResponse> staffFalse = staffFalseHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
        List<UserInfoResponse> userFalse = userFalseHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
        List<UserInfoResponse> unchecked = new ArrayList<>();
        for (UserInfoResponse u : staffFalse) unchecked.add(u);
        for (UserInfoResponse u : userFalse) unchecked.add(u);

        return unchecked;
    }

    //안전모 착용 유무 근로자 리스트
    public UserHelmetListResponse getUserHelmetList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        List<UserInfoResponse> checked = getTrueHelmetUserList(siteId);
        List<UserInfoResponse> unchecked = getFalseHelmetUserList(siteId);

        return new UserHelmetListResponse(checked, unchecked);
    }

    //유저 개인정보 상세 조회
    public UserDetailInfoResponse getDetailInfo(Long userId) {
        User user = findUserById(userId);

        return new UserDetailInfoResponse(user.getUserInfo());
    }

    //승인 요청 리스트 조회
    public List<AcceptUserList> acceptUserList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);
        List<User> userList = userRepository.findAllBySiteInfoAndRole(site, GUEST);

        return userList.stream().map(u -> new AcceptUserList(u.getUserInfo())).collect(Collectors.toList());
    }

    //게스트 권한 근로자로 권한 승인
    @Transactional
    public void acceptRoleToUser(Long userId) {
        User user = findUserById(userId);
        user.changeRole(USER);
    }

    //근로자 권한 변경
    @Transactional
    public void changeUserRole(Long userId, Role role) {
        User user = findUserById(userId);
        user.changeRole(role);
    }

    //근로자 현장 코드 입력
    @Transactional
    public void mappingSite(String siteCode) {
        User user = userUtils.getUserFromSecurityContext();

        SiteInfo site = siteInfoRepository.findBySiteCode(siteCode).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);
        site.addUser(user);
    }

    //근로자 안전모 착용 유무 조회
    public boolean checkHelmet() {
        User user = userUtils.getUserFromSecurityContext();
        return user.isUserHelmetCheck();
    }

    //근로자 안전모 착용 변경
    @Transactional
    public void updateHelmetCheck(boolean helmetCheck) {
        User user = userUtils.getUserFromSecurityContext();
        user.changeHelmetCheck(helmetCheck);
    }

    //현장 승인 대기 중 취소
    @Transactional
    public void cancelAccept(Long userId) {
        User user = findUserById(userId);

        SiteInfo site = siteInfoRepository.findById(user.getSiteInfo().getId()).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);
        site.subUser(user);
    }

    private void createToken(String email, HttpServletResponse response) {
        String accessToken = jwtTokenProvider.createAccessToken(email, Role.ADMIN);
        String refreshToken = jwtTokenProvider.createRefreshToken(email, Role.ADMIN);

        jwtTokenProvider.setHeaderAccessToken(response, accessToken);
        jwtTokenProvider.setHeaderRefreshToken(response, refreshToken);

        refreshTokenRepository.save(new RefreshToken(refreshToken));
    }

    public User findUser(String email) {
        return userRepository
                .findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException(UserNotFoundException.EXCEPTION));
    }

    public User findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(UserNotFoundException.EXCEPTION));
    }
}
