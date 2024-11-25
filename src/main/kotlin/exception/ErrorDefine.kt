package exception

import org.springframework.http.HttpStatus

enum class ErrorDefine(
    val errorCode: String,
    val httpStatus: HttpStatus,
    val message: String
) {






    // Bad Request
    INVALID_ARGUMENT("4000", HttpStatus.BAD_REQUEST, "Bad Request: Invalid Arguments");
}