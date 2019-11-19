package com.kchernenko.spacex.model

import com.google.gson.annotations.SerializedName

class LaunchModel {
    @SerializedName("flight_number") var id:Int = 0
    @SerializedName("mission_name") var name:String = "Launch"
    @SerializedName("launch_date_unix") var timestamp:Long = 0
}