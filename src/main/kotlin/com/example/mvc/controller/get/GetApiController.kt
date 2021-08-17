package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping(path = ["/hello" , "/abcd"])
    fun hello() : String{
        return "hello kotlin";
    }

    @RequestMapping(path = ["/request-mapping"] , method = [RequestMethod.GET])
    fun requestMapping():String{
        return "request-mapping";
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String ,@PathVariable age:String): String {
        println("${name} , ${age}")
        return name;
    }


    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value = "name") _name: String ,@PathVariable age:String): String {
        val name = "kotlin";

        println("${name} , ${age}")
        return name;
    }

    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam name: String,
        @RequestParam(value = "age") age:Int
    ): String {
        println("$name, $age")
        return "$name $age";
    }

    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(@ModelAttribute userRequest: UserRequest) : UserRequest{
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map : Map<String,Any>): Map<String, Any> {
        print(map)
        val phoneNumber = map.get("phone-number")
        print(phoneNumber);
        return map
    }
}