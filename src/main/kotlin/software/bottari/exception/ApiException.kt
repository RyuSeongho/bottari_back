package software.bottari.exception

import lombok.Getter

@Getter
class ApiException(val error: ErrorDefine) : RuntimeException()
