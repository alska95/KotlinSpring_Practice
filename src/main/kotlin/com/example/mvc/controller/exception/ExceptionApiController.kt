package com.example.mvc.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {
    @GetMapping("/hello")
    fun hello(){

        val list = mutableListOf<String>()
        val temp = list[0]
//        if(true){
//            throw RuntimeException("강제 exception 발생")
//        }
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutofBoundException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        println("controller exception handler")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}