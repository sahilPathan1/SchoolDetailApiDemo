package com.example.schooldetailapidemo.ui.utils


const val BaseUrl = "http://3.110.41.106/"
private const val baseFolderPath = "kodris/api/"

const val auth = "auth/"

const val checkMail = "${baseFolderPath}${auth}check-email"
const val school_list = "${baseFolderPath}home/school-list"
const val teacher_list = "${baseFolderPath}home/teacher-list"
const val school_detail = "${baseFolderPath}home/school-detail"
const val teacher_detail = "${baseFolderPath}home/teacher-detail"
const val chat_history = "${baseFolderPath}home/chat-history"
const val login = "${baseFolderPath}auth/login"
const val logout = "${baseFolderPath}auth/logout"
const val chat_list = "${baseFolderPath}home/chat-list"



/////////////socket



const val SOCKET_URL = "http://3.110.41.106:8080/"
const val userConnect = "connect_user"
const val receiveMessage = "receive_message"
const val sendMessage = "send_message"

