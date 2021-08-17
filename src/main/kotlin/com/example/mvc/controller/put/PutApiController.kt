package com.example.mvc.controller.put;

import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.*

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
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse{
        //1.result
        return UserResponse().apply{
            this.result?.apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            //2.description
            this.description = "~~~~~~~~~"
        }.apply {
            //3.user mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)

            userList.add(UserRequest().apply {
                this.name = "hwang1"
                this.age = 20
                this.email = "alska@naver.com"
                this.address = "address"
                this.phoneNumber = "01020003004"
            })

            userList.add(UserRequest().apply {
                this.name = "hwang2"
                this.age = 20
                this.email = "alska@naver.com"
                this.address = "address"
                this.phoneNumber = "01020003004"
            })

            userList.add(UserRequest().apply {
                this.name = "hwang3"
                this.age = 20
                this.email = "alska@naver.com"
                this.address = "address"
                this.phoneNumber = "01020003004"
            })
            this.userRequest = userList
        }

    }


}
