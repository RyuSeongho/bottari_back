package software.bottari.exception

import org.springframework.http.HttpStatus

enum class ErrorDefine(
    val errorCode: String,
    val httpStatus: HttpStatus,
    val message: String
) {



    // NOT FOUND
    USER_NOT_FOUND("4040",HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),
    INVALID_CREDENTIALS("4041",HttpStatus.NOT_FOUND, "비밀번호가 틀렸습니다."),
    // Bad Request
    DUPLICATE_USER_EMAIL("4001", HttpStatus.BAD_REQUEST, "이메일이 중복됩니다."),
    INVALID_ARGUMENT("4000", HttpStatus.BAD_REQUEST, "요청이 잘못되었습니다. ");
}