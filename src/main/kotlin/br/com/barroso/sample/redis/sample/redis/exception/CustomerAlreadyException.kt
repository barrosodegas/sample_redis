package br.com.barroso.sample.redis.sample.redis.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.Serializable
import java.lang.RuntimeException

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
class CustomerAlreadyException(message: String): RuntimeException(message), Serializable