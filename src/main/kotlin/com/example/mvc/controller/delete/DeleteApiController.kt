package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {

    @RequestMapping(path = ["/delete-mapping"])
    fun deleteMapping(
        @RequestParam(value = "name") _name : String,
        @NotNull(message = "age 값이 누락되었습니다.")
        @Min(value = 20, message = "age는 20보다 커야합니다.")
        @RequestParam age : Int
    ): String{
        println(_name)
        println(age)
        return _name + " " + age
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(@PathVariable(value = "name") _name:String ,
                          @NotNull(message = "age 값이 누락되었습니다.")
                          @Min(value = 20 , message = "age는 20보다 커야 합니다.")
                          @PathVariable(name = "age") _age:Int): String {
        println(_name)
        println(_age)
        return _name + " " + _age
    }
}