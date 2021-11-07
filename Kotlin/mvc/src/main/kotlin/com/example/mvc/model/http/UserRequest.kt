package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserRequest (
    var name : String? = null,
    var age : Int? = null,
    var email : String? = null,
    var address : String? = null,
    var phoneNumber:String?=null
)

// data class, ?의 의미, var의 디폴트트