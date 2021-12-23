package com.example.admin.entity

import com.google.firebase.database.Exclude

data class User (
    @get:Exclude
    var login:String?=null,
    var password:String?=null
)