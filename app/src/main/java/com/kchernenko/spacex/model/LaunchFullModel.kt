package com.kchernenko.spacex.model

import com.google.gson.annotations.SerializedName

class LaunchFullModel {
    @SerializedName("flight_number") var id:Int = 0
    @SerializedName("mission_name") var name:String = ""
    @SerializedName("launch_date_unix") var timestamp:Long = 0
    @SerializedName("details") var details:String = ""
    @SerializedName("launch_site") var launchSite:LaunchSite? = null
    @SerializedName("rocket") var rocket:Rocket? = null
    @SerializedName("links") var links:Links? = null

    class LaunchSite{
        @SerializedName("site_name") var siteName:String = ""
        @SerializedName("site_name_long") var siteNameLong:String = ""
    }

    class Rocket{
        @SerializedName("rocket_name") var rocketName:String = ""
    }

    class Links{
        @SerializedName("mission_patch") var missionPatch:String = ""
        @SerializedName("wikipedia") var wikipedia:String = ""
        @SerializedName("video_link") var videoLink:String = ""
    }

}