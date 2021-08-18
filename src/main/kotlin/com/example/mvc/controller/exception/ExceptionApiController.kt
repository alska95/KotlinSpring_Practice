package com.example.mvc.controller.exception

import com.example.mvc.model.Error
import com.example.mvc.model.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {
    @GetMapping("/hello")
    fun hello(){

        val list = mutableListOf<String>()
        val temp = list[0]
//        if(true){
//            throw RuntimeException("강제 exception 발생")
//        }
    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min = 2, max = 6)
        @RequestParam name:String,

        @Min(10)
        @RequestParam age:Int
    ): String {
        println(name + " " + age)
        return name+" "+age
    }
    @PostMapping("")
    fun post(@Validated @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException , request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        var errors = mutableListOf<Error>()
        e.bindingResult.allErrors.forEach{
            val error = Error().apply {
                val field = it as FieldError
                this.field = field.field
                this.message = it.defaultMessage
                this.value = it.rejectedValue
            }

            errors.add(error)

        }
        //2.에러 response
        val errorResponse = ErrorResponse().apply {
            this.resultCode = ""
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.path=request.requestURI.toString()
            this.message ="요청 에러가 발생"
            this.timestamp = LocalDateTime.now()
            this.errors = errors
            this.httpMethod = request.method
        }

        //3.ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)

    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(e: ConstraintViolationException, request:HttpServletRequest):ResponseEntity<ErrorResponse>{
        //1.에러 분석
        var errors = mutableListOf<Error>()

        e.constraintViolations.forEach{
            val field = it.propertyPath.last().name
            val message = it.message
            val error = Error().apply{
                this.value= it.invalidValue
                this.field = field
                this.message = message
            }
            errors.add(error)
        }

        //2.에러 response
        val errorResponse = ErrorResponse().apply {
            this.resultCode = ""
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.path=request.requestURI.toString()
            this.message ="요청 에러가 발생"
            this.timestamp = LocalDateTime.now()
            this.errors = errors
            this.httpMethod = request.method
        }

        //3.ResponseEntity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)


    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutofBoundException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        println("controller exception handler")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}