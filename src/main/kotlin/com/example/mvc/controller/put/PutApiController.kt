package com.example.mvc.controller.put;

import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping():String{
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT] , path = ["/request-mapping"])
    fun requestMapping():String{
        return "request-mapping-put method"
    }


    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String> {

        if(bindingResult.hasErrors()){
            //500 error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append(field.field+" | "+message+"\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }
        return ResponseEntity.ok().body(null)

//        //1.result
//        return UserResponse().apply{
//            this.result?.apply {
//                this.resultCode = "OK"
//                this.resultMessage = "성공"
//            }
//        }.apply {
//            //2.description
//            this.description = "~~~~~~~~~"
//        }.apply {
//            //3.user mutable list
//            val userList = mutableListOf<UserRequest>()
//
//            userList.add(userRequest)
//
//            userList.add(UserRequest().apply {
//                this.name = "hwang1"
//                this.age = 20
//                this.email = "alska@naver.com"
//                this.address = "address"
//                this.phoneNumber = "01020003004"
//            })
//
//            userList.add(UserRequest().apply {
//                this.name = "hwang2"
//                this.age = 20
//                this.email = "alska@naver.com"
//                this.address = "address"
//                this.phoneNumber = "01020003004"
//            })
//
//            userList.add(UserRequest().apply {
//                this.name = "hwang3"
//                this.age = 20
//                this.email = "alska@naver.com"
//                this.address = "address"
//                this.phoneNumber = "01020003004"
//            })
//            this.userRequest = userList
//        }

    }


}
