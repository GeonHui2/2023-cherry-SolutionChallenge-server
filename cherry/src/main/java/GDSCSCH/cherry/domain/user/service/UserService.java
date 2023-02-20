package GDSCSCH.cherry.domain.user.service;

import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.user.presentation.dto.response.AcceptUserList;
import GDSCSCH.cherry.domain.siteInfo.domain.SiteInfo;
import GDSCSCH.cherry.domain.siteInfo.domain.repository.SiteInfoRepository;
import GDSCSCH.cherry.domain.siteInfo.exception.SiteInfoNotFoundException;
import GDSCSCH.cherry.domain.user.domain.User;
import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
import GDSCSCH.cherry.domain.user.presentation.dto.request.ChangeUserInfoRequest;
import GDSCSCH.cherry.domain.user.presentation.dto.request.UserSignUp;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserDetailInfoResponse;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserHelmetListResponse;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserInfoResponse;
import GDSCSCH.cherry.domain.user.presentation.dto.response.UserProfileResponse;
import GDSCSCH.cherry.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //유저 회원가입
    @Transactional
    public Long signUpUser(UserSignUp userSignUp) {
        User user = User.createUser(
                userSignUp.getUserName(),
                userSignUp.getUserEmail(),
                userSignUp.getUserPhoneNum(),
                userSignUp.getUserAge()
        );

        userRepository.save(user);

        return user.getId();
    }

    //유저 개인정보 수정
    @Transactional
    public void changeUserInfo(ChangeUserInfoRequest changeUserInfo, Long userId) {
        User user = userUtils.getUserById(userId);

        user.changeUserInfo(
                changeUserInfo.getUserName(),
                changeUserInfo.getUserPhoneNum(),
                changeUserInfo.getUserAge()
        );
    }

    //근로자 삭제
    @Transactional
    public void deleteUser(Long userId) {
        User user = userUtils.getUserById(userId);

        SiteInfo site = siteInfoRepository.findById(user.getSiteInfo().getId()).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);
        site.subUser(user);
        userRepository.delete(user);
    }

    //유저 개인정보 조회
    public UserProfileResponse getProfile(Long userId) {
        User user = userUtils.getUserById(userId);

        return new UserProfileResponse(user.getUserInfo());
    }

    //안전모 쓴 근로자 리스트 확인
    public List<UserInfoResponse> getTrueHelmetUserList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        List<User> trueHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, true);

        return trueHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
    }

    //안전모 안 쓴 근로자 리스트 확인
    public List<UserInfoResponse> getFalseHelmetUserList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        List<User> flaseHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, false);

        return flaseHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());
    }

    //안전모 착용 유무 근로자 리스트
    public UserHelmetListResponse getUserHelmetList(Long siteId) {
        SiteInfo site = siteInfoRepository.findById(siteId).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);

        List<User> trueHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, true);
        List<UserInfoResponse> checked = trueHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());

        List<User> flaseHelmetUserList = userRepository.findAllBySiteInfoAndUserHelmetCheck(site, false);
        List<UserInfoResponse> unchecked = flaseHelmetUserList.stream().map(t -> new UserInfoResponse(t.getUserInfo())).collect(Collectors.toList());

        return new UserHelmetListResponse(checked, unchecked);
    }

    //유저 개인정보 상세 조회
    public UserDetailInfoResponse getDetailInfo(Long userId) {
        User user = userUtils.getUserById(userId);

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
        User user = userUtils.getUserById(userId);
        user.changeRole(USER);
    }

    //근로자 권한 변경
    @Transactional
    public void changeUserRole(Long userId, Role role) {
        User user = userUtils.getUserById(userId);
        user.changeRole(role);
    }

    //근로자 현장 코드 입력
    @Transactional
    public void mappingSite(String siteCode, Long userId) {
        User user = userUtils.getUserById(userId);

        SiteInfo site = siteInfoRepository.findBySiteCode(siteCode).orElseThrow(() -> SiteInfoNotFoundException.EXCEPTION);
        site.addUser(user);
    }

    //근로자 안전모 착용 유무 조회
    public boolean checkHelmet(Long userId) {
        User user = userUtils.getUserById(userId);
        return user.isUserHelmetCheck();
    }

    //근로자 안전모 착용 변경
    @Transactional
    public void updateHelmetCheck(boolean helmetCheck) {
        User user = userUtils.getUserFromSecurityContext();
        user.changeHelmetCheck(helmetCheck);
    }
}
