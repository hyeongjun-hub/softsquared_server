package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 2000 : 요청 성공
     */
    SUCCESS(true, 2000, "요청에 성공하였습니다."),


    /**
     * 4000 : 클라이언트 Request 오류
     */
    // Common
    REQUEST_ERROR(false, 4000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 4001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 4002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,4003,"권한이 없는 유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 4010, "유저 이메일 값을 확인해주세요."),
    USERS_STATUS_NOT_Y(false,4011, "삭제된 유저입이니다."),
    AUTH_KAKAO_EMPTY_TOKEN(false, 4012, "유효하지않은 카카오 토큰입니다,"),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 4015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 4016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,4017,"중복된 이메일입니다."),
    POST_USERS_PASSWORD_MIN(false, 4018, "비밀번호를 8자 이상 입력하세요"),
    POST_USERS_NOT_EXISTS_EMAIL(false, 4019, "없는 이메일입니다. 회원가입이 필요합니다."),

    // [POST] /reviews
    POST_REVIEWS_EMPTY_CONTENT(false, 4030, "리뷰 내용을 입력해주세요."),

    // address
    POST_ADDRESS_STATUS_NOT_Y(false, 4040, "삭제된 주소입니다."),
    POST_ADDRESS_EXISTS_ADDRESS(false,4041,"중복된 주소입니다."),
    POST_ADDRESS_EXISTS_ADDRESS_NAME(false,4042,"중복된 주소이름입니다."),

    // cart
    POST_CARTS_INVALID_MENU(false, 4080, "유효하지 않은 메뉴입니다."),


    /**
     * 4500 : 클라이언트 Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 4500, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 4513, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,4514,"없는 아이디거나 비밀번호가 틀렸습니다."),
    USERS_INAPP_EXISTS(false, 4515, "어플로 가입한 정보가 있습니다."),
    FAILED_TO_KAKAO_AUTH(false, 4519, "카카오 유저 정보 조회에 실패하였습니다."),
    FAILED_TO_KAKAO_EMAIL(false, 4520, "카카오 정보에 등록된 이메일이 없습니다. 이메일을 추가 입력해주세요."),


    /**
     * 5000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 5000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 5001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users
    MODIFY_FAIL_USERNAME(false,5014,"유저네임 수정 실패"),
    EDIT_FAIL_CONTENT(false, 5015, "유저정보 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 5011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 5012, "비밀번호 복호화에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
