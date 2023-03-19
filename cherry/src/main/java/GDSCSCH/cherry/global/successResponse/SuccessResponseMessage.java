package GDSCSCH.cherry.global.successResponse;

public class SuccessResponseMessage {

    //Admin
    public static final String ADMIN_SIGNIN_SUCCESS = "관리자 로그인 성공";
    public static final String ADMIN_SIGNUP_SUCCESS = "관리자 회원가입 성공";
    public static final String ADMIN_LOGOUT_SUCCESS = "관리자 로그아웃 성공";
    public static final String GET_ADMIN_SITEINFO = "관리자 본인 현장 정보 조회 성공";
    public static final String EDIT_ADMIN_INFO = "관리자 개인 정보 수정 성공";
    public static final String GET_ADMIN_INFO = "관리자 개인 정보 조회 성공";

    public static final String ACCEPT_USER_LIST = "승인 요청 리스트 조회 완료";
    public static final String USER_INFO_DETAIL = "유저 개인정보 상세 조회 완료";
    public static final String ACCEPT_GUEST = "게스트 승인 완료";
    public static final String EDIT_USER_ROLE = "근로자 권한 변경 완료";
    public static final String DELETE_USER = "근로자 삭제 완료";

    public static final String USER_HELMET_LIST = "안전모 착용 유무 근로자 리스트 조회 완료";

    //User
    public static final String USER_SIGNIN_SUCCESS = "근로자 로그인 성공";
    public static final String USER_SIGNUP_SUCCESS = "근로자 회원가입 성공";
    public static final String USER_LOGOUT_SUCCESS = "근로자 로그아웃 성공";
    public static final String GET_USER_SITEINFO = "유저 본인 현장 정보 조회 성공";
    public static final String USER_MAPPING_SITE = "근로자 현장 코드 입력 완료";
    public static final String EDIT_USER_INFO = "근로자 개인 정보 수정 완료";
    public static final String USER_INFO = "유저 개인정보 조회 완료";
    public static final String USER_HELMET_CHECK = "근로자 안전모 착용 유무 조회 완료";
    public static final String EDIT_USER_HELMET_CHECK = "근로자 안전모 착용 변경 완료";
    public static final String CANCEL_SITE_ACCEPT = "현장 승인 대기 중 취소 완료";

    //SiteInfo
    public static final String CREATE_SITE = "현장 생성 완료";
    public static final String GET_SITE = "현장 상세 정보 조회 완료";
    public static final String EDIT_SITE = "현장 상세 정보 수정 완료";
    public static final String DELETE_SITE = "현장 상세 정보 삭제 완료";
    public static final String VALID_SITE_CODE = "현장 코드 유효 여부 조회 완료";
    public static final String WORK_START = "모든 체크리스트 상태 초기화";

    //SiteCheck
    public static final String CREATE_CHECK = "질문 생성 완료";
    public static final String GET_CHECKLIST = "질문 리스트 조회 완료";
    public static final String EDIT_CHECKLIST = "질문 수정 완료";
    public static final String DELETE_CHECKLIST = "질문 삭제 완료";
    public static final String EDIT_CHECK_ANSWER = "상태 수정 완료";

    //DefaultSiteCheck
    public static final String CREATE_DEFAULT_CHECK = "기본 질문 생성 완료";
    public static final String EDIT_DEFAULT_CHECKLIST = "기본 질문 수정 완료";
    public static final String DELETE_DEFAULT_CHECKLIST = "기본 질문 삭제 완료";


}
