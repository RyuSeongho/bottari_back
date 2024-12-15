package software.bottari.exception

import org.springframework.http.HttpStatus

enum class ErrorDefine(
    val errorCode: String,
    val httpStatus: HttpStatus,
    val message: String
) {

    // NOT FOUND
    USER_NOT_FOUND("4040", HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    INVALID_CREDENTIALS("4041", HttpStatus.NOT_FOUND, "비밀번호가 올바르지 않습니다."),
    INQUIRY_NOT_FOUND("4042", HttpStatus.NOT_FOUND, "존재하지 않는 문의사항입니다."),
    CONTRACT_NOT_FOUND("4043",HttpStatus.NOT_FOUND, "존재하지 않는 예약입니다."),

    // BAD REQUEST
    INVALID_FORMAT("4003", HttpStatus.BAD_REQUEST, "입력 값의 형식이 잘못되었습니다."),
    MISSING_DOCUMENT("4004", HttpStatus.BAD_REQUEST, "필수 서류가 누락되었습니다."),
    INVALID_SHIP_SCHEDULE_ID("4002", HttpStatus.BAD_REQUEST, "존재하지 않는 ShipScheduleId입니다."),
    DUPLICATE_USER_EMAIL("4001", HttpStatus.BAD_REQUEST, "이미 사용 중인 이메일입니다."),
    INVALID_ARGUMENT("4000", HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");

}

