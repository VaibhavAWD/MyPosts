package com.vaibhavdhunde.practice.myposts.api

import java.io.IOException

class ApiException(message: String) : IOException(message)

class NetworkException(message: String) : IOException(message)