package sad.ru.base

class ExceptionInfoMapper {
    fun map(exception: Throwable): ExceptionInfo {
        return when (exception) {
            else -> ExceptionInfo(-1)
        }
    }
}